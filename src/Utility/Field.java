package Utility;

import Game.Game;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field {
    private Text text;
    private Rectangle fieldSquare;
    private Slot fieldSlot;
    private int fieldID;

    public Field(int fieldID,Game game) {
        this.fieldSquare = null;
        this.fieldSlot = null;
        this.fieldID = fieldID;
        this.text = new Text(50,50,"");
        EventHandler<MouseEvent> eventMouseClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(fieldID==game.getEnabledFieldID())
                {
                    Glow g = new Glow();
                    g.setLevel(0);
                    text.setEffect(g);
                    game.setEnabledFieldID(-1);
                    BattlefieldManager.setBoardColor(Color.GREEN,game);
                }
                else {
                    for (Field f : game.getFields()) {
                        Glow g = new Glow();
                        g.setLevel(0);
                        f.getText().setEffect(g);
                        f.getFieldSquare().setFill(Color.GREEN);
                    }
                    game.setEnabledFieldID(fieldID);
                    Glow g = new Glow();
                    g.setLevel(4);
                    text.setEffect(g);
                    for(Field f:game.getFields())
                    {
                        if(Field.checkAvailability(fieldSlot.getUnitName().getSpeed(),fieldID,f.getFieldID(),game))
                        {
                            f.getFieldSquare().setFill(Color.DARKORANGE);
                        }
                    }
                }

            }
        };
        this.text.addEventFilter(MouseEvent.MOUSE_CLICKED,eventMouseClick);
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void setFieldSquare(Rectangle fieldSquare) {
        this.fieldSquare = fieldSquare;
    }

    public void setFieldSlot(Slot fieldSlot) {
        this.fieldSlot = fieldSlot;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public Rectangle getFieldSquare() {
        return fieldSquare;
    }

    public Slot getFieldSlot() {
        return fieldSlot;
    }

    public int getFieldID() {
        return fieldID;
    }
    public static boolean checkAvailability(int steps,int actualFieldID,int targetFieldID, Game game)
    {
        if(steps>=0 && actualFieldID==targetFieldID)
        {
            return true;
        }
        else
        {
            if(steps==0)
            {
                return false;
            }
            else
            {
                boolean left= false;
                boolean right = false;
                boolean up = false;
                boolean down = false;
                if((actualFieldID+1)%12!=0)
                {
                    right=checkAvailability(steps-1,actualFieldID+1,targetFieldID,game);
                }
                if((actualFieldID-1)%12!=11 && actualFieldID!=0)
                {
                    left=checkAvailability(steps-1,actualFieldID-1,targetFieldID,game);
                }
                if((actualFieldID-12)>=0)
                {
                    up=checkAvailability(steps-1,actualFieldID-12,targetFieldID,game);
                }
                if((actualFieldID+12)<144)
                {
                    down=checkAvailability(steps-1,actualFieldID+12,targetFieldID,game);
                }
                return (up||down||left||right);
            }
        }
    }
}
