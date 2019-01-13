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
    public Field createField(double x, double y,int ID,TextField amountOfUnits, CheckBox moveFromBackpackCheckbox, CheckBox moveCheckBox, CheckBox attackCheckBox)
    {
        Field f = new Field(ID,game);
        Rectangle r = new Rectangle(x,y,48,48);
        r.setFill(Color.GREEN);
        EventHandler<MouseEvent> eventHandlerDrag = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                if(r.getFill()==Color.DARKORANGE)
                {
                    r.setFill(Color.ORANGE);
                }
                if(r.getFill()==Color.GREEN)
                {
                    r.setFill(Color.LIGHTGREEN);
                }
            }
        };
        EventHandler<MouseEvent> eventHandlerUndrag = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                if(r.getFill()==Color.ORANGE)
                {
                    r.setFill(Color.DARKORANGE);
                }
                if(r.getFill()==Color.LIGHTGREEN)
                {
                    r.setFill(Color.GREEN);
                }
            }
        };
        EventHandler<MouseEvent> eventHandlerClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                BattlefieldManager manager = new BattlefieldManager(game,players);
                if(moveFromBackpackCheckbox.isSelected()) {
                    if (game.getEnabledSlotID() == -1) {
                        return;
                    } else {
                        Player p = players[0];//TURNS
                        for (Slot s : p.getBackpack()) {
                            if (s.getSlotID() == game.getEnabledSlotID()) {
                                if (f.getFieldID() > 131 && f.getFieldID() < 144 && !s.isDroppedOnField()) {
                                    String numberOfUnitsText = amountOfUnits.getText();
                                    int numberOfUnits;
                                    try {
                                        numberOfUnits = Integer.parseInt(numberOfUnitsText);
                                        if (numberOfUnits > 0) {
                                            if (f.getFieldSlot() == null) {
                                                manager.checkAmountOfChosenUnits(f, s, numberOfUnits);
                                            } else {
                                                if (f.getFieldSlot().getUnitName().getType().equals(s.getUnitName().getType()) && f.getFieldSlot().getUnitName().getElement().equals(s.getUnitName().getElement())) {
                                                    manager.checkAmountOfChosenUnits(f, s, numberOfUnits);
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
                if(moveCheckBox.isSelected())
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
                        if(source.getFieldSlot()!= null && Field.checkAvailability(source.getFieldSlot().getUnitName().getSpeed(),source.getFieldID(),f.getFieldID(),game)) {
                            if (f.getFieldSlot() != null && source.getFieldSlot() != null && source.getFieldSlot().getUnitName().getElement().getElementName().equals(f.getFieldSlot().getUnitName().getElement().getElementName()) && source.getFieldSlot().getUnitName().getType().getTypeName().equals(f.getFieldSlot().getUnitName().getType().getTypeName())) {
                                manager.checkAmountOfChosenUnits(f, source.getFieldSlot(), source.getFieldSlot().getNumberOfUnits());
                                source.setFieldSlot(null);
                                source.getText().setText("");
                                BattlefieldManager.setBoardColor(Color.GREEN,game);
                            } else {
                                if (source.getFieldSlot() != null && f.getFieldSlot() == null) {
                                    f.setFieldSlot(source.getFieldSlot());
                                    source.setFieldSlot(null);
                                    source.getText().setText("");
                                    f.getText().setX(x + 5);
                                    f.getText().setY(y + 30);
                                    f.getText().setText(f.getFieldSlot().getFieldRepresentation().getText());
                                    f.getText().setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
                                    f.getText().setFill(Color.DARKBLUE);
                                    BattlefieldManager.setBoardColor(Color.GREEN,game);
                                }
                            }
                        }
                    }
                }
                if(attackCheckBox.isSelected()&&game.getEnabledFieldID()!=-1)
                {
                    Field source = null;
                    for(Field f1:game.getFields())
                    {
                        if(f1.getFieldID()==game.getEnabledFieldID())
                        {
                            source = f1;
                        }
                    }
                    if(source.getFieldSlot()!= null&& f.getFieldSlot()!=null && Field.checkAvailability(source.getFieldSlot().getUnitName().getSight(),source.getFieldID(),f.getFieldID(),game)/*&&(!source.getFieldSlot().getPlayer().equals(f.getFieldSlot().getPlayer()))*/)
                    {
                        int sourceAttack=AttackCounter.countAttack(source.getFieldSlot(),f.getFieldSlot());
                        int fAttack=AttackCounter.countAttack(f.getFieldSlot(),source.getFieldSlot());
                        fAttack+=source.getFieldSlot().getLifeLost();
                        sourceAttack+=f.getFieldSlot().getLifeLost();
                        int sourceUnitsLeft=source.getFieldSlot().getNumberOfUnits()-(fAttack/source.getFieldSlot().getUnitName().getLife());
                        int fUnitsLeft=f.getFieldSlot().getNumberOfUnits()-(sourceAttack/f.getFieldSlot().getUnitName().getLife());
                        int sourceAdditionalLifeLost=fAttack%source.getFieldSlot().getUnitName().getLife();
                        int fAdditionalLifeLost=sourceAttack%f.getFieldSlot().getUnitName().getLife();
                        reduceNumberOfUnits(source,sourceUnitsLeft,sourceAdditionalLifeLost);
                        reduceNumberOfUnits(f,fUnitsLeft,fAdditionalLifeLost);
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
    public static void reduceNumberOfUnits(Field f,int numberOfUnits,int additionalLifeLost)
    {
        if(numberOfUnits<=0)
        {
            f.setFieldSlot(null);
            f.getText().setText("");
        }
        else
        {
            f.getFieldSlot().setNumberOfUnits(numberOfUnits);
            f.getFieldSlot().getFieldRepresentation().setText(f.getFieldSlot().getUnitName().getElement().getSymbol()+f.getFieldSlot().getUnitName().getType().getSymbol()+f.getFieldSlot().getNumberOfUnits());
            f.getText().setText(f.getFieldSlot().getFieldRepresentation().getText());
            f.getFieldSlot().setLifeLost(additionalLifeLost);
        }
    }
    public CheckBox createCheckBox(String text, double x, double y)
    {
        CheckBox checkbox = new CheckBox(text);
        checkbox.setSelected(false);
        checkbox.setLayoutX(x);
        checkbox.setLayoutY(y);
        checkbox.setFont(Font.font("arial",FontWeight.BOLD,FontPosture.REGULAR,12));
        checkbox.setTextFill(Color.BLACK);
        return checkbox;
    }
    public static void setBoardColor(Color color, Game game)
    {
        for(Field f1:game.getFields())
        {
            f1.getFieldSquare().setFill(color);
        }
    }
    public void setDisablingCheckBoxes(CheckBox move, CheckBox moveFromBackpack, CheckBox attack)
    {
        EventHandler disablingEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() instanceof CheckBox) {
                    CheckBox chk = (CheckBox) event.getSource();
                    if ("Move".equals(chk.getText())) {
                        moveFromBackpack.setSelected(false);
                        attack.setSelected(false);
                    } else {
                        if ("Attack".equals(chk.getText())) {
                            move.setSelected(false);
                            moveFromBackpack.setSelected(false);
                        } else {
                            move.setSelected(false);
                            attack.setSelected(false);
                        }
                    }
                }
            }
        };
        moveFromBackpack.setOnAction(disablingEventHandler);
        move.setOnAction(disablingEventHandler);
        attack.setOnAction(disablingEventHandler);
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
