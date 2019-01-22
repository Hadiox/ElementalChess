package Utility;

import Game.Game;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

class MoveMaker {
    void moveFromBackpack(Game game, Player[]players, Field fieldCreated, TextField amountOfUnits,BattlefieldManager manager)
    {
        if(game.getEnabledSlotID() != -1)
        {
            Player ownerOfUnits = players[game.getPlayerTurn()-1];
            for (Slot processedSlot : ownerOfUnits.getBackpack()) {
                if (processedSlot.getSlotID() == game.getEnabledSlotID()) {
                    checkIfMoveFromBackpackAvailable(processedSlot,fieldCreated,amountOfUnits,manager,game);
                }
            }
        }
    }

    void move (Game game,Field fieldCreated,BattlefieldManager manager, double x, double y)
    {
        if(game.getEnabledFieldID()!= -1)
        {
            Field source = getEnabledField(game);
            if (Field.checkAvailability(source.getSpeedOfUnit(), source.getFieldID(), fieldCreated.getFieldID(), game)) {
                makeAMove(fieldCreated,manager,source,game,x,y);
            }
        }
    }
    void attack(Game game,Field fieldCreated)
    {
        Field source = getEnabledField(game);
        if (source.getFieldSlot() != null && fieldCreated.getFieldSlot() != null && !source.equals(fieldCreated) && Field.checkAvailability(source.getSightOfUnit(), source.getFieldID(), fieldCreated.getFieldID(), game) && (!source.getFieldPlayer().equals(fieldCreated.getFieldPlayer()))) {
            int sourceAttack = AttackCounter.countAttack(source.getFieldSlot(), fieldCreated.getFieldSlot());
            int fAttack = AttackCounter.countAttack(fieldCreated.getFieldSlot(), source.getFieldSlot());
            fAttack += source.getLifeLost();
            sourceAttack += fieldCreated.getLifeLost();
            int sourceUnitsLeft = source.getNumberOfUnits() - (fAttack / source.getLife());
            int fUnitsLeft = fieldCreated.getNumberOfUnits() - (sourceAttack / fieldCreated.getLife());
            int sourceAdditionalLifeLost = fAttack % source.getLife();
            int fAdditionalLifeLost = sourceAttack % fieldCreated.getLife();
            BattlefieldManager.reduceNumberOfUnits(source, sourceUnitsLeft, sourceAdditionalLifeLost);
            BattlefieldManager.reduceNumberOfUnits(fieldCreated, fUnitsLeft, fAdditionalLifeLost);
            BoardSetter.createGlow(0, source.getText());
            game.cleanUpAfterTurn();
        }
    }
    private void checkIfMoveFromBackpackAvailable(Slot processedSlot, Field fieldCreated, TextField amountOfUnits, BattlefieldManager manager, Game game){

        if ((processedSlot.getPlayerNumber()==1&&fieldCreated.getFieldID() > 131 && fieldCreated.getFieldID() < 144 && !processedSlot.isDroppedOnField())||(processedSlot.getPlayerNumber()==2&&fieldCreated.getFieldID() >=0 && fieldCreated.getFieldID() < 12 && !processedSlot.isDroppedOnField())) {
            String numberOfUnitsText = amountOfUnits.getText();
            try {
                    makeAMoveFromBackpack(numberOfUnitsText,fieldCreated,manager,processedSlot,game);
                }

            catch (NumberFormatException ex) {
                System.out.println("Typed wrong number!");
            }
        }
    }
    private void makeAMoveFromBackpack(String numberOfUnitsText, Field fieldCreated, BattlefieldManager manager, Slot processedSlot, Game game)throws NumberFormatException {
        int numberOfUnits = Integer.parseInt(numberOfUnitsText);
        if (numberOfUnits > 0) {
            if (fieldCreated.getFieldSlot() == null) {
                manager.checkAmountOfChosenUnits(fieldCreated, processedSlot, numberOfUnits);
                game.cleanUpAfterTurn();
            } else {
                if (fieldCreated.getFieldPlayer().equals(processedSlot.getPlayer()) && fieldCreated.getFieldType().equals(processedSlot.getSlotType()) && fieldCreated.getElement().equals(processedSlot.getSlotElement())) {
                    manager.checkAmountOfChosenUnits(fieldCreated, processedSlot, numberOfUnits);
                    game.cleanUpAfterTurn();
                }
            }
        }
    }
    private Field getEnabledField(Game game)
    {
        Field enabled = null;
        for (Field f : game.getFields()) {
            if (f.getFieldID() == game.getEnabledFieldID()) {
                enabled = f;
            }
        }
        return enabled;
    }
    private void makeAMove(Field fieldCreated,BattlefieldManager manager,Field source,Game game,double x, double y)
    {
        if (fieldCreated.getFieldSlot() != null && source.getFieldSlot() != null && fieldCreated.getFieldPlayer().equals(source.getFieldPlayer()) && !source.equals(fieldCreated) && source.getElement().equals(fieldCreated.getElement()) && source.getFieldType().equals(fieldCreated.getFieldType())) {
            manager.checkAmountOfChosenUnits(fieldCreated, source.getFieldSlot(), source.getNumberOfUnits());
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
                fieldCreated.getText().setText(fieldCreated.getFieldRepresentation().getText());
                fieldCreated.getText().setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
                if (fieldCreated.getFieldPlayerNumber() == 1) {
                    fieldCreated.getText().setFill(Color.DARKBLUE);
                } else {
                    fieldCreated.getText().setFill(Color.DARKRED);
                }
                game.cleanUpAfterTurn();
            }
        }
    }
}