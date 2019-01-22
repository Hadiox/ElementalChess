package Utility;

import Game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BattlefieldManager {
    private Game game;
    private Player[]players;

    public BattlefieldManager(Game game, Player[] players) {
        this.game = game;
        this.players = players;
    }

    public TextField createAmountOfUnitsTextField(double x, double y)
    {
        TextField amountOfUnitsText = new TextField();
        amountOfUnitsText.setLayoutX(x);
        amountOfUnitsText.setLayoutY(y);
        amountOfUnitsText.setPrefWidth(60);
        amountOfUnitsText.setPrefHeight(10);
        return amountOfUnitsText;
    }
    public Field createField(double x, double y,int ID,TextField amountOfUnits, CheckBox moveFromBackpackCheckbox, CheckBox moveCheckBox, CheckBox attackCheckBox)
    {
        Field fieldCreated = new Field(ID,game,moveCheckBox,attackCheckBox);
        Rectangle rectangleOfFieldCreated = new Rectangle(x,y,48,48);
        rectangleOfFieldCreated.setFill(Color.GREEN);
        MoveMaker moveMaker = new MoveMaker();
        EventHandler<MouseEvent> eventHandlerDrag = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                if(rectangleOfFieldCreated.getFill()==Color.DARKORANGE)
                {
                    rectangleOfFieldCreated.setFill(Color.ORANGE);
                }
                if(rectangleOfFieldCreated.getFill()==Color.GREEN)
                {
                    rectangleOfFieldCreated.setFill(Color.LIGHTGREEN);
                }
                if(rectangleOfFieldCreated.getFill()==Color.YELLOW)
                {
                    rectangleOfFieldCreated.setFill(Color.LIGHTYELLOW);
                }
            }
        };
        EventHandler<MouseEvent> eventHandlerUndrag = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                if(rectangleOfFieldCreated.getFill()==Color.ORANGE)
                {
                    rectangleOfFieldCreated.setFill(Color.DARKORANGE);
                }
                if(rectangleOfFieldCreated.getFill()==Color.LIGHTGREEN)
                {
                    rectangleOfFieldCreated.setFill(Color.GREEN);
                }
                if(rectangleOfFieldCreated.getFill()==Color.LIGHTYELLOW)
                {
                    rectangleOfFieldCreated.setFill(Color.YELLOW);
                }
            }
        };
        EventHandler<MouseEvent> eventHandlerClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                BattlefieldManager manager = new BattlefieldManager(game,players);
                if(moveFromBackpackCheckbox.isSelected()) {
                moveMaker.moveFromBackpack(game,players,fieldCreated,amountOfUnits,manager);
                }
                if(moveCheckBox.isSelected())
                {
                    moveMaker.move(game,fieldCreated,manager,x,y);
                }
                if(attackCheckBox.isSelected()&&game.getEnabledFieldID()!=-1) {
                    moveMaker.attack(game,fieldCreated);
                }
            }
        };
        rectangleOfFieldCreated.addEventFilter(MouseEvent.MOUSE_CLICKED,eventHandlerClick);
        rectangleOfFieldCreated.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET,eventHandlerDrag);
        rectangleOfFieldCreated.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET,eventHandlerUndrag);
        fieldCreated.setFieldSquare(rectangleOfFieldCreated);
        return fieldCreated;
    }
    public Text createAmountOfUnitsText(double x, double y)
    {
        Text amountOfUnitsText = new Text(x,y,"Number of units");
        amountOfUnitsText.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
        amountOfUnitsText.setFill(Color.ORANGE);
        return amountOfUnitsText;
    }
    static void reduceNumberOfUnits(Field fieldReduced,int numberOfUnits,int additionalLifeLost)
    {
        if(numberOfUnits<=0)
        {
            fieldReduced.setFieldSlot(null);
            fieldReduced.getText().setText("");
        }
        else
        {
            fieldReduced.setNumberOfUnits(numberOfUnits);
            fieldReduced.getFieldRepresentation().setText(fieldReduced.getElementSymbol()+fieldReduced.getTypeSymbol()+fieldReduced.getNumberOfUnits());
            fieldReduced.getText().setText(fieldReduced.getFieldRepresentation().getText());
            fieldReduced.getFieldSlot().setLifeLost(additionalLifeLost);
        }
    }
    public CheckBox createCheckBox(String text, double x, double y)
    {
        CheckBox checkbox = new CheckBox(text);
        checkbox.setSelected(false);
        checkbox.setLayoutX(x);
        checkbox.setLayoutY(y);
        checkbox.setFont(Font.font("arial",FontWeight.BOLD,FontPosture.REGULAR,12));
        checkbox.setTextFill(Color.ORANGE);
        return checkbox;
    }
    public static void setBoardColor(Color color, Game game)
    {
        for(Field f:game.getFields())
        {
            f.getFieldSquare().setFill(color);
        }
    }
    public void setDisablingCheckBoxes(CheckBox move, CheckBox moveFromBackpack, CheckBox attack,Game game)
    {
        EventHandler disablingEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() instanceof CheckBox) {
                    CheckBox chk = (CheckBox) event.getSource();
                    if ("Move".equals(chk.getText())) {
                        moveFromBackpack.setSelected(false);
                        attack.setSelected(false);
                        if(game.getEnabledFieldID()!=-1)
                        {
                            BattlefieldManager.setBoardColor(Color.GREEN,game);
                            Field enabled=chooseEnabledField(game);
                            Field.colorFieldsInRange(game, enabled.getFieldID(), Color.DARKORANGE, enabled.getFieldSlot().getUnitName().getSpeed());

                        }

                    } else {
                        if ("Attack".equals(chk.getText())) {
                            move.setSelected(false);
                            moveFromBackpack.setSelected(false);
                            if(game.getEnabledFieldID()!=-1)
                            {
                                BattlefieldManager.setBoardColor(Color.GREEN,game);
                                Field enabled=chooseEnabledField(game);
                                Field.colorFieldsInRange(game,enabled.getFieldID(),Color.YELLOW,enabled.getFieldSlot().getUnitName().getSight());
                            }

                        } else {
                            move.setSelected(false);
                            attack.setSelected(false);
                            if(game.getEnabledFieldID()!=-1)
                            {
                                BattlefieldManager.setBoardColor(Color.GREEN,game);
                            }

                        }
                    }
                }
            }
        };
        moveFromBackpack.setOnAction(disablingEventHandler);
        move.setOnAction(disablingEventHandler);
        attack.setOnAction(disablingEventHandler);
    }
     void checkAmountOfChosenUnits(Field targetField, Slot sourceSlot, int numberOfUnits)
    {
        if(numberOfUnits< sourceSlot.getNumberOfUnits())
        {
            sourceSlot.setNumberOfUnits(sourceSlot.getNumberOfUnits()-numberOfUnits);
            sourceSlot.getRepresentation().setText(sourceSlot.getSlotElementName().element+ " "+ sourceSlot.getSlotTypeName().type+" "+ sourceSlot.getNumberOfUnits());
            this.moveUnitsFromBackpack(targetField, sourceSlot,numberOfUnits);
        }
        else
        {
            sourceSlot.getRepresentation().setVisible(false);
            this.moveUnitsFromBackpack(targetField, sourceSlot, sourceSlot.getNumberOfUnits());
            sourceSlot.setDroppedOnField();
        }
    }
    private void moveUnitsFromBackpack(Field targetField, Slot sourceSlot, int numberOfUnits)
    {
        Player p = sourceSlot.getPlayer();
        if(targetField.getFieldSlot()==null)
        {
            targetField.setFieldSlot(new Slot(numberOfUnits, sourceSlot.getUnitName(),game,-2,p));
            targetField.getFieldRepresentation().setX(targetField.getFieldSquare().getX());
            targetField.getFieldRepresentation().setY(targetField.getFieldSquare().getY());
            targetField.getFieldRepresentation().setVisible(true);
            targetField.getFieldSlot().setDroppedOnField();
            sourceSlot.getFieldRepresentation().setText(sourceSlot.getElementSymbol()+ sourceSlot.getTypeSymbol()+numberOfUnits);
            targetField.getText().setText(sourceSlot.getFieldRepresentation().getText());
            targetField.getText().setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
            if(p.getPlayerNumber()==1) {
                targetField.getText().setFill(Color.DARKBLUE);
            }
            else
            {
                targetField.getText().setFill(Color.DARKRED);
            }
            targetField.getText().setX(targetField.getFieldSquare().getX()+5);
            targetField.getText().setY(targetField.getFieldSquare().getY()+30);
        }
        else
        {
            targetField.setNumberOfUnits(targetField.getNumberOfUnits()+numberOfUnits);
            targetField.getFieldRepresentation().setText(targetField.getElementSymbol()+ targetField.getTypeSymbol()+ targetField.getNumberOfUnits());
            targetField.getText().setText(targetField.getFieldRepresentation().getText());
        }
    }
    private Field chooseEnabledField(Game game)
    {
        Field enabled=null;
        for(Field f: game.getFields())
        {
            if(f.getFieldID()==game.getEnabledFieldID())
            {
                enabled=f;
                break;
            }
        }
        return enabled;
    }
}
