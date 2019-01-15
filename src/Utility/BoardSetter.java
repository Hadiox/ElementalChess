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
    public static Text createTurnLabel()
    {
        Text turnLabel = new Text("Player "+1+" has turn");
        turnLabel.setFont(Font.font("arial",FontWeight.BOLD,FontPosture.REGULAR,30));
        turnLabel.setFill(Color.BLACK);
        turnLabel.setX(550);
        turnLabel.setY(25);
        return turnLabel;
    }
}
