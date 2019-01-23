package Utility;

import Game.Game;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

class MoveMaker {
    void moveFromBackpack(Game game, Player[]players, Field targetField, TextField amountOfUnits, BattlefieldManager manager)
    {
        if(game.getEnabledSlotID() != -1)
        {
            Player ownerOfUnits = players[game.getPlayerTurn()-1];
            for (Slot processedSlot : ownerOfUnits.getBackpack()) {
                if (processedSlot.getSlotID() == game.getEnabledSlotID()) {
                    checkIfMoveFromBackpackAvailable(processedSlot, targetField,amountOfUnits,manager,game);
                }
            }
        }
    }

    void move (Game game, Field targetField, BattlefieldManager manager, double x, double y)
    {
        if(game.getEnabledFieldID()!= -1)
        {
            Field source = getEnabledField(game);
            if (Field.checkAvailability(source.getSpeedOfUnit(), source.getFieldID(), targetField.getFieldID(), game)) {
                makeAMove(targetField,manager,source,game,x,y);
            }
        }
    }
    void attack(Game game,Field targetField)
    {
        Field source = getEnabledField(game);
        if (source.getFieldSlot() != null && targetField.getFieldSlot() != null && !source.equals(targetField) && Field.checkAvailability(source.getSightOfUnit(), source.getFieldID(), targetField.getFieldID(), game) && (!source.getFieldPlayer().equals(targetField.getFieldPlayer()))) {
            int sourceAttack = AttackCounter.countAttack(source.getFieldSlot(), targetField.getFieldSlot());
            int fAttack = AttackCounter.countAttack(targetField.getFieldSlot(), source.getFieldSlot());
            fAttack += source.getLifeLost();
            sourceAttack += targetField.getLifeLost();
            int sourceUnitsLeft = source.getNumberOfUnits() - (fAttack / source.getLife());
            int fUnitsLeft = targetField.getNumberOfUnits() - (sourceAttack / targetField.getLife());
            int sourceAdditionalLifeLost = fAttack % source.getLife();
            int fAdditionalLifeLost = sourceAttack % targetField.getLife();
            BattlefieldManager.reduceNumberOfUnits(source, sourceUnitsLeft, sourceAdditionalLifeLost);
            BattlefieldManager.reduceNumberOfUnits(targetField, fUnitsLeft, fAdditionalLifeLost);
            BoardSetter.createGlow(0, source.getText());
            game.cleanUpAfterTurn();
        }
    }
    private void checkIfMoveFromBackpackAvailable(Slot processedSlot, Field targetField, TextField amountOfUnits, BattlefieldManager manager, Game game){

        if ((processedSlot.getPlayerNumber()==1&& targetField.getFieldID() > 131 && targetField.getFieldID() < 144 && !processedSlot.isDroppedOnField())||(processedSlot.getPlayerNumber()==2&& targetField.getFieldID() >=0 && targetField.getFieldID() < 12 && !processedSlot.isDroppedOnField())) {
            String numberOfUnitsText = amountOfUnits.getText();
            try {
                    makeAMoveFromBackpack(numberOfUnitsText, targetField,manager,processedSlot,game);
                }

            catch (NumberFormatException ex) {
                System.out.println("Typed wrong number!");
            }
        }
    }
    private void makeAMoveFromBackpack(String numberOfUnitsText, Field targetField, BattlefieldManager manager, Slot processedSlot, Game game)throws NumberFormatException {
        int numberOfUnits = Integer.parseInt(numberOfUnitsText);
        if (numberOfUnits > 0) {
            if (targetField.getFieldSlot() == null) {
                manager.checkAmountOfChosenUnits(targetField, processedSlot, numberOfUnits);
                game.cleanUpAfterTurn();
            } else {
                if (targetField.getFieldPlayer().equals(processedSlot.getPlayer()) && targetField.getFieldType().equals(processedSlot.getSlotType()) && targetField.getElement().equals(processedSlot.getSlotElement())) {
                    manager.checkAmountOfChosenUnits(targetField, processedSlot, numberOfUnits);
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
    private void makeAMove(Field targetField, BattlefieldManager manager, Field source, Game game, double x, double y)
    {
        if (targetField.getFieldSlot() != null && source.getFieldSlot() != null && targetField.getFieldPlayer().equals(source.getFieldPlayer()) && !source.equals(targetField) && source.getElement().equals(targetField.getElement()) && source.getFieldType().equals(targetField.getFieldType())) {
            manager.checkAmountOfChosenUnits(targetField, source.getFieldSlot(), source.getNumberOfUnits());
            source.setFieldSlot(null);
            source.getText().setText("");
            game.cleanUpAfterTurn();
        } else {
            if (source.getFieldSlot() != null && targetField.getFieldSlot() == null && !source.equals(targetField)) {
                targetField.setFieldSlot(source.getFieldSlot());
                source.setFieldSlot(null);
                source.getText().setText("");
                targetField.getText().setX(x + 5);
                targetField.getText().setY(y + 30);
                targetField.getText().setText(targetField.getFieldRepresentation().getText());
                targetField.getText().setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
                if (targetField.getFieldPlayerNumber() == 1) {
                    targetField.getText().setFill(Color.DARKBLUE);
                } else {
                    targetField.getText().setFill(Color.DARKRED);
                }
                game.cleanUpAfterTurn();
            }
        }
    }
}