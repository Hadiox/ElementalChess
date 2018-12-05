package Utility;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class BoardSetter {
    public ObservableList setBoard(Group root, Player [] players,ArrayList<Text>unitsOfPlayer1,ArrayList<Text>unitsOfPlayer2)
    {
        ObservableList list = root.getChildren();
        Rectangle board = new Rectangle(383,84,600,600);
        Rectangle p1Board = new Rectangle(96,84,192,600);
        Rectangle p2Board = new Rectangle(1079,84,192,600);
        p1Board.setFill(Color.GREEN);
        p2Board.setFill(Color.GREEN);
        board.setFill(Color.GREEN);
        list.addAll(board,p1Board,p2Board);
        for(Integer a=0;a<13;a++)
        {
            Line l = new Line(383+(a*50),84,383+(a*50),684);
            if(a<12)
            {
                Text t = new Text(383+(a*50)+20,80,Character.toString((char)(65+a)));
                t.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR,20));
                t.setFill(Color.WHITE);
                list.add(t);
                Text t2 = new Text(360,84+(a*50)+30,a.toString());
                t2.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR,20));
                t2.setFill(Color.WHITE);
                list.add(t2);
            }
            l.setVisible(true);
            Line l2 = new Line(383,84+(a*50),983,84+(a*50));
            list.add(l);
            list.add(l2);
        }
        unitsOfPlayer1 = makePlayersUnitsVisible(players[0]);
        unitsOfPlayer2 = makePlayersUnitsVisible(players[1]);
        int i = 0;
        for (Text t : unitsOfPlayer1) {
            t.setFill(Color.DARKBLUE);
            t.setX(100);
            t.setY(100 + 30 * i);
            list.add(t);
            i++;
        }
        i = 0;
        for (Text t : unitsOfPlayer2) {
            t.setFill(Color.DARKRED);
            t.setX(1085);
            t.setY(100 + 30 * i);
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
}
