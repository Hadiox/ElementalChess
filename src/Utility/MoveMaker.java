package Utility;

import Game.Game;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MoveMaker {
    public void moveFromBackpack(Game game, Player[]players, Field fieldCreated, TextField amountOfUnits,BattlefieldManager manager)
    {
        if (game.getEnabledSlotID() == -1) {
            return;
        } else {
            Player p = players[game.getPlayerTurn()-1];
            for (Slot s : p.getBackpack()) {
                if (s.getSlotID() == game.getEnabledSlotID()) {
                    if ((s.getPlayer().getPlayerNumber()==1&&fieldCreated.getFieldID() > 131 && fieldCreated.getFieldID() < 144 && !s.isDroppedOnField())||(s.getPlayer().getPlayerNumber()==2&&fieldCreated.getFieldID() >=0 && fieldCreated.getFieldID() < 12 && !s.isDroppedOnField())) {
                        String numberOfUnitsText = amountOfUnits.getText();
                        int numberOfUnits;
                        try {
                            numberOfUnits = Integer.parseInt(numberOfUnitsText);
                            if (numberOfUnits > 0) {
                                if (fieldCreated.getFieldSlot() == null) {
                                    manager.checkAmountOfChosenUnits(fieldCreated, s, numberOfUnits);
                                    game.cleanUpAfterTurn();
                                } else {
                                    if (fieldCreated.getFieldSlot().getPlayer().equals(s.getPlayer())&&fieldCreated.getFieldSlot().getUnitName().getType().equals(s.getUnitName().getType()) && fieldCreated.getFieldSlot().getUnitName().getElement().equals(s.getUnitName().getElement())) {
                                        manager.checkAmountOfChosenUnits(fieldCreated, s, numberOfUnits);
                                        game.cleanUpAfterTurn();
                                    }
                                }
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("Typed wrong number!");
                        }
                    }
                }
            }
        }
    }

    void move (Game game,Field fieldCreated,BattlefieldManager manager, double x, double y)
    {
        if(game.getEnabledFieldID()!= -1)
        {
            Field source = null;
            for(Field f1:game.getFields())
            {
                if(f1.getFieldID()==game.getEnabledFieldID())
                {
                    source = f1;
                }
            }
            try {
                if (Field.checkAvailability(source.getFieldSlot().getUnitName().getSpeed(), source.getFieldID(), fieldCreated.getFieldID(), game)) {
                    if (fieldCreated.getFieldSlot() != null && source.getFieldSlot() != null && fieldCreated.getFieldSlot().getPlayer().equals(source.getFieldSlot().getPlayer()) && !source.equals(fieldCreated) && source.getFieldSlot().getUnitName().getElement().getElementName().equals(fieldCreated.getFieldSlot().getUnitName().getElement().getElementName()) && source.getFieldSlot().getUnitName().getType().getTypeName().equals(fieldCreated.getFieldSlot().getUnitName().getType().getTypeName())) {
                        manager.checkAmountOfChosenUnits(fieldCreated, source.getFieldSlot(), source.getFieldSlot().getNumberOfUnits());
                        source.setFieldSlot(null);
                        source.getText().setText("");
                        game.cleanUpAfterTurn();
                    } else {
                        if (source.getFieldSlot() != null && fieldCreated.getFieldSlot() == null && !source.equals(fieldCreated)) {
                            fieldCreated.setFieldSlot(source.getFieldSlot());
                            source.setFieldSlot(null);
                            source.getText().setText("");
                            fieldCreated.getText().setX(x + 5);
                            fieldCreated.getText().setY(y + 30);
                            fieldCreated.getText().setText(fieldCreated.getFieldSlot().getFieldRepresentation().getText());
                            fieldCreated.getText().setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
                            if (fieldCreated.getFieldSlot().getPlayer().getPlayerNumber() == 1) {
                                fieldCreated.getText().setFill(Color.DARKBLUE);
                            } else {
                                fieldCreated.getText().setFill(Color.DARKRED);
                            }
                            game.cleanUpAfterTurn();
                        }
                    }
                }
            }
            catch(NullPointerException nullex)
            {
            }
        }
    }
    public void attack(Game game,Field fieldCreated)
    {
        Field source = null;
        for (Field f1 : game.getFields()) {
            if (f1.getFieldID() == game.getEnabledFieldID()) {
                source = f1;
            }
        }
        try
        {
            if (source.getFieldSlot() != null && fieldCreated.getFieldSlot() != null && !source.equals(fieldCreated) && Field.checkAvailability(source.getFieldSlot().getUnitName().getSight(), source.getFieldID(), fieldCreated.getFieldID(), game) && (!source.getFieldSlot().getPlayer().equals(fieldCreated.getFieldSlot().getPlayer()))) {
                int sourceAttack = AttackCounter.countAttack(source.getFieldSlot(), fieldCreated.getFieldSlot());
                int fAttack = AttackCounter.countAttack(fieldCreated.getFieldSlot(), source.getFieldSlot());
                fAttack += source.getFieldSlot().getLifeLost();
                sourceAttack += fieldCreated.getFieldSlot().getLifeLost();
                int sourceUnitsLeft = source.getFieldSlot().getNumberOfUnits() - (fAttack / source.getFieldSlot().getUnitName().getLife());
                int fUnitsLeft = fieldCreated.getFieldSlot().getNumberOfUnits() - (sourceAttack / fieldCreated.getFieldSlot().getUnitName().getLife());
                int sourceAdditionalLifeLost = fAttack % source.getFieldSlot().getUnitName().getLife();
                int fAdditionalLifeLost = sourceAttack % fieldCreated.getFieldSlot().getUnitName().getLife();
                BattlefieldManager.reduceNumberOfUnits(source, sourceUnitsLeft, sourceAdditionalLifeLost);
                BattlefieldManager.reduceNumberOfUnits(fieldCreated, fUnitsLeft, fAdditionalLifeLost);
                BoardSetter.createGlow(0, source.getText());
                game.cleanUpAfterTurn();
            }
        }
        catch(NullPointerException nullex)
        {

        }
    }
}
