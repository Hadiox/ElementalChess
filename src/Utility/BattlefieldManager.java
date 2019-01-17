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

    public TextField createTextFieldForAmountOfUnits(double x, double y)
    {
        TextField tf = new TextField();
        tf.setLayoutX(x);
        tf.setLayoutY(y);
        tf.setPrefWidth(60);
        tf.setPrefHeight(10);
        return tf;
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
        Text t = new Text(x,y,"Number of units");
        t.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
        t.setFill(Color.ORANGE);
        return t;
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
            fieldReduced.getFieldSlot().setNumberOfUnits(numberOfUnits);
            fieldReduced.getFieldSlot().getFieldRepresentation().setText(fieldReduced.getFieldSlot().getUnitName().getElement().getSymbol()+fieldReduced.getFieldSlot().getUnitName().getType().getSymbol()+fieldReduced.getFieldSlot().getNumberOfUnits());
            fieldReduced.getText().setText(fieldReduced.getFieldSlot().getFieldRepresentation().getText());
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
        for(Field f1:game.getFields())
        {
            f1.getFieldSquare().setFill(color);
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
                            Field enabled=null;
                            for(Field f: game.getFields())
                            {
                                if(f.getFieldID()==game.getEnabledFieldID())
                                {
                                    enabled=f;
                                    break;
                                }
                            }
                            try {
                                Field.colorFieldsInRange(game, enabled.getFieldID(), Color.DARKORANGE, enabled.getFieldSlot().getUnitName().getSpeed());
                            }
                            catch(NullPointerException e)
                            {

                            }
                        }

                    } else {
                        if ("Attack".equals(chk.getText())) {
                            move.setSelected(false);
                            moveFromBackpack.setSelected(false);
                            if(game.getEnabledFieldID()!=-1)
                            {
                                BattlefieldManager.setBoardColor(Color.GREEN,game);
                                Field enabled=null;
                                for(Field f: game.getFields())
                                {
                                    if(f.getFieldID()==game.getEnabledFieldID())
                                    {
                                        enabled=f;
                                        break;
                                    }
                                }
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
     void checkAmountOfChosenUnits(Field f,Slot s,int numberOfUnits)
    {
        if(numberOfUnits<s.getNumberOfUnits())
        {
            s.setNumberOfUnits(s.getNumberOfUnits()-numberOfUnits);
            s.getRepresentation().setText(s.getUnitName().getElement().getElementName().element+ " "+s.getUnitName().getType().getTypeName().type+" "+s.getNumberOfUnits());
            this.moveUnitsFromBackpack(f,s,numberOfUnits);
        }
        else
        {
            s.getRepresentation().setVisible(false);
            this.moveUnitsFromBackpack(f,s,s.getNumberOfUnits());
            s.setDroppedOnField();
        }
    }
    private void moveUnitsFromBackpack(Field f, Slot s,int numberOfUnits)
    {
        Player p = s.getPlayer();
        if(f.getFieldSlot()==null)
        {
            f.setFieldSlot(new Slot(numberOfUnits,s.getUnitName(),game,-2,p));
            f.getFieldSlot().getFieldRepresentation().setX(f.getFieldSquare().getX());
            f.getFieldSlot().getFieldRepresentation().setY(f.getFieldSquare().getY());
            f.getFieldSlot().getFieldRepresentation().setVisible(true);
            f.getFieldSlot().setDroppedOnField();
            s.getFieldRepresentation().setText(s.getUnitName().getElement().getSymbol()+s.getUnitName().getType().getSymbol()+numberOfUnits);
            f.getText().setText(s.getFieldRepresentation().getText());
            f.getText().setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
            if(p.getPlayerNumber()==1) {
                f.getText().setFill(Color.DARKBLUE);
            }
            else
            {
                f.getText().setFill(Color.DARKRED);
            }
            f.getText().setX(f.getFieldSquare().getX()+5);
            f.getText().setY(f.getFieldSquare().getY()+30);
        }
        else
        {
            f.getFieldSlot().setNumberOfUnits(f.getFieldSlot().getNumberOfUnits()+numberOfUnits);
            f.getFieldSlot().getFieldRepresentation().setText(f.getFieldSlot().getUnitName().getElement().getSymbol()+f.getFieldSlot().getUnitName().getType().getSymbol()+f.getFieldSlot().getNumberOfUnits());
            f.getText().setText(f.getFieldSlot().getFieldRepresentation().getText());
        }
    }
}
