package Utility;

import Game.Game;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BattlefieldManager {
    Game game;
    Player[]players;

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
    public Field createField(double x, double y,int ID,TextField amountOfUnits)
    {
        Field f = new Field(ID);
        Rectangle r = new Rectangle(x,y,48,48);
        r.setFill(Color.GREEN);
        EventHandler<MouseEvent> eventHandlerDrag = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                r.setFill(Color.LIGHTGREEN);
            }
        };
        EventHandler<MouseEvent> eventHandlerUndrag = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                r.setFill(Color.GREEN);
            }
        };
        EventHandler<MouseEvent> eventHandlerClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                BattlefieldManager manager = new BattlefieldManager(game,players);
                if(game.getEnabledSlotID()==-1)
                {
                    return;
                }
                else
                {
                    Player p = players[0];//TURNS
                    for(Slot s:p.getBackpack())
                    {
                        if(s.getSlotID()==game.getEnabledSlotID())
                        {
                            if(f.getFieldID()>131 && f.getFieldID()<144 && !s.isDroppedOnField()) {
                                String numberOfUnitsText = amountOfUnits.getText();
                                int numberOfUnits;
                                try{
                                    numberOfUnits = Integer.parseInt(numberOfUnitsText);
                                    if(numberOfUnits>0)
                                    {
                                        if(f.getFieldSlot()==null)
                                        {
                                            manager.checkAmountOfChosenUnits(f,s,numberOfUnits);
                                        }
                                        else
                                        {
                                            if(f.getFieldSlot().getUnitName().getType().equals(s.getUnitName().getType()) && f.getFieldSlot().getUnitName().getElement().equals(s.getUnitName().getElement()))
                                            {
                                                manager.checkAmountOfChosenUnits(f,s,numberOfUnits);
                                            }
                                        }
                                    }
                                }
                                catch(NumberFormatException ex)
                                {
                                    System.out.println("Typed wrong number!");
                                }
                            }
                        }
                    }
                }
            }
        };
        r.addEventFilter(MouseEvent.MOUSE_CLICKED,eventHandlerClick);
        r.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET,eventHandlerDrag);
        r.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET,eventHandlerUndrag);
        f.setFieldSquare(r);
        return f;
    }
    public Text createAmountOfUnitsText(double x, double y)
    {
        Text t = new Text(x,y,"Liczba jednostek");
        t.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
        t.setFill(Color.BLACK);
        return t;
    }
    public void checkAmountOfChosenUnits(Field f,Slot s,int numberOfUnits)
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
            this.moveUnitsFromBackpack(f,s,numberOfUnits);
            s.setDroppedOnField(true);
        }
    }
    public void moveUnitsFromBackpack(Field f, Slot s,int numberOfUnits)
    {
        Player p = players[0];
        if(f.getFieldSlot()==null)
        {
            f.setFieldSlot(new Slot(numberOfUnits,s.getUnitName(),game,-2,p));
            f.getFieldSlot().getFieldRepresentation().setX(f.getFieldSquare().getX());
            f.getFieldSlot().getFieldRepresentation().setY(f.getFieldSquare().getY());
            f.getFieldSlot().getFieldRepresentation().setVisible(true);
            f.getFieldSlot().setDroppedOnField(true);
            s.getFieldRepresentation().setText(s.getUnitName().getElement().getSymbol()+s.getUnitName().getType().getSymbol()+numberOfUnits);
            f.getText().setText(s.getFieldRepresentation().getText());
            f.getText().setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
            f.getText().setFill(Color.DARKBLUE);
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
