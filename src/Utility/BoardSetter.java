package Utility;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class BoardSetter {

    public void createBackground(Scene scene)
    {
        String url1 ="https://images.unsplash.com/photo-1519120693210-d47b03b31d77?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=3b8d462021896b6a8825629fb69a04b4&auto=format&fit=crop&w=1537&q=80";
        Image img1 = new Image(url1);
        ImagePattern pat = new ImagePattern(img1);
        scene.setFill(pat);

    }

    public ObservableList setBoard(Group root, Player [] players)
    {
        ObservableList list = root.getChildren();
        ArrayList<Text> unitsOfPlayer1;
        ArrayList<Text> unitsOfPlayer2;
        Rectangle board = new Rectangle(383,44,600,600);
        Rectangle p1Board = new Rectangle(96,44,192,600);
        Rectangle p2Board = new Rectangle(1079,44,192,600);
        p1Board.setFill(Color.GREEN);
        p2Board.setFill(Color.GREEN);
        board.setFill(Color.GREEN);
        list.addAll(board,p1Board,p2Board);
        for(Integer a=0;a<13;a++)
        {
            Line verticalLine = new Line(383+(a*50),44,383+(a*50),644);
            Line horizontalLine = new Line(383,44+(a*50),983,44+(a*50));
            if(a<12)
            {
                Text columnLetter = new Text(383+(a*50)+20,40,Character.toString((char)(65+a)));
                columnLetter.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR,16));
                columnLetter.setFill(Color.ORANGE);
                list.add(columnLetter);
                Text rowLetter = new Text(360,44+(a*50)+30,a.toString());
                rowLetter.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR,16));
                rowLetter.setFill(Color.ORANGE);
                list.add(rowLetter);
            }
            verticalLine.setVisible(true);
            list.add(verticalLine);
            list.add(horizontalLine);
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
    private ArrayList<Text> makePlayersUnitsVisible(Player p)
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
        turnLabel.setFont(Font.font("arial",FontWeight.BOLD,FontPosture.REGULAR,24));
        turnLabel.setFill(Color.ORANGE);
        turnLabel.setX(600);
        turnLabel.setY(20);
        return turnLabel;
    }
    public static Button createExitButton()
    {
        Button exitButton = new Button("Exit");
        exitButton.setLayoutX(1275);
        exitButton.setLayoutY(675);
        exitButton.setScaleX(1.5);
        exitButton.setScaleY(1.5);
        EventHandler<MouseEvent>eventClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.exit();
            }
        };
        exitButton.addEventFilter(MouseEvent.MOUSE_CLICKED,eventClick);
        return exitButton;
    }
    public static Text createPlayerLabel(int playerNumber,double x, double y,Color color)
    {
        Text playerLabel = new Text();
        playerLabel.setText("Player "+playerNumber);
        playerLabel.setFill(color);
        playerLabel.setFont(Font.font("arial",FontWeight.BOLD,FontPosture.REGULAR,30));
        playerLabel.setX(x);
        playerLabel.setY(y);
        return playerLabel;
    }
    public static void createGlow(double level, Node node)
    {
        Glow g = new Glow();
        g.setLevel(level);
        node.setEffect(g);
    }
}
