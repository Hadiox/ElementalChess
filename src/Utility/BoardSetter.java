package Utility;

import Game.Game;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class BoardSetter {
    private Game game;
    private Player[] players;
    public Game getGame() {
        return game;
    }

    public Player[] getPlayers() {
        return players;
    }

    public BoardSetter(Game game, Player[]players) {
        this.game = game;
        this.players=players;
    }

    public ObservableList setBoard(Group root, Player [] players, ArrayList<Text>unitsOfPlayer1, ArrayList<Text>unitsOfPlayer2)
    {
        ObservableList list = root.getChildren();
        Rectangle board = new Rectangle(383,44,600,600);
        Rectangle p1Board = new Rectangle(96,44,192,600);
        Rectangle p2Board = new Rectangle(1079,44,192,600);
        p1Board.setFill(Color.GREEN);
        p2Board.setFill(Color.GREEN);
        board.setFill(Color.GREEN);
        list.addAll(board,p1Board,p2Board);
        for(Integer a=0;a<13;a++)
        {
            Line l = new Line(383+(a*50),44,383+(a*50),644);
            if(a<12)
            {
                Text t = new Text(383+(a*50)+20,40,Character.toString((char)(65+a)));
                t.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR,20));
                t.setFill(Color.WHITE);
                list.add(t);
                Text t2 = new Text(360,44+(a*50)+30,a.toString());
                t2.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR,20));
                t2.setFill(Color.WHITE);
                list.add(t2);
            }
            l.setVisible(true);
            Line l2 = new Line(383,44+(a*50),983,44+(a*50));
            list.add(l);
            list.add(l2);
        }
        unitsOfPlayer1 = makePlayersUnitsVisible(players[0]);
        unitsOfPlayer2 = makePlayersUnitsVisible(players[1]);
        int i = 0;
        for (Text t : unitsOfPlayer1) {
            t.setFill(Color.DARKBLUE);
            t.setX(100);
            t.setY(60 + 30 * i);
            list.add(t);
            i++;
        }
        i = 0;
        for (Text t : unitsOfPlayer2) {
            t.setFill(Color.DARKRED);
            t.setX(1085);
            t.setY(60 + 30 * i);
            i++;
            list.add(t);
        }
        return list;
    }
    public ArrayList<Text> makePlayersUnitsVisible(Player p)
    {
        ArrayList<Text> result = new ArrayList<>();
        for(Slot s :p.getBackpack())
        {
            result.add(s.getRepresentation());
        }
        return result;
    }
    public TextField createTextFieldForAmountOfUnits(double x,double y)
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
                                        if(numberOfUnits<s.getNumberOfUnits())
                                        {
                                            s.setNumberOfUnits(s.getNumberOfUnits()-numberOfUnits);
                                            s.getRepresentation().setText(s.getUnitName().getElement().getElementName().element+ " "+s.getUnitName().getType().getTypeName().type+" "+s.getNumberOfUnits());
                                            BoardSetter setter = new BoardSetter(game,players);
                                            setter.moveUnits(f,s,numberOfUnits);
                                        }
                                        else
                                        {
                                            s.getRepresentation().setVisible(false);
                                            BoardSetter setter = new BoardSetter(game,players);
                                            setter.moveUnits(f,s,numberOfUnits);
                                            s.setDroppedOnField(true);
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
    public void moveUnits(Field f, Slot s,int numberOfUnits)
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
