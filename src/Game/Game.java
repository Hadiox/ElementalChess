package Game;
import Utility.ArmyCreator;
import Utility.MenuHandler;
import Utility.Player;
import Utility.Slot;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.*;
import java.util.ArrayList;

public class Game extends Application {
    public void start(Stage primaryStage)
    {
        //Menu---------------------------------------------------------------------------
        MenuHandler.startingMessage();
        Player[] players;
        while (true) {
            String st = MenuHandler.getCommand();
            if (st.equals("start"))
            {
                ArmyCreator armyCreator = new ArmyCreator();
                armyCreator.setArmy();
                players = armyCreator.getPlayers();
                break;
            }
            else {
                if (st.equals("exit"))
                  {
                     System.exit(0);
                  }
                  else {
                        System.out.println("Wrong input! Type 'start' to enter army creator or 'exit' to exit the game!");
                  }
            }
        }
        // Graphics---------------------------------------------------------------------------------
        Group root = new Group();
        String url1 ="https://images.unsplash.com/photo-1519120693210-d47b03b31d77?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=3b8d462021896b6a8825629fb69a04b4&auto=format&fit=crop&w=1537&q=80";
        Scene scene = new Scene(root,1366,768);
        Image img1 = new Image(url1);
        ImagePattern pat = new ImagePattern(img1);
        scene.setFill(pat);
        Rectangle board = new Rectangle(383,84,600,600);
        Rectangle p1Board = new Rectangle(96,84,192,600);
        Rectangle p2Board = new Rectangle(1079,84,192,600);
        ObservableList list = root.getChildren();
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
        ArrayList<Text> unitsOfPlayer1 = makePlayersUnitsVisible(players[0]);
        ArrayList<Text> unitsOfPlayer2 = makePlayersUnitsVisible(players[1]);
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
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String []args)
    {
        launch(args);
    }
    public ArrayList<Text> makePlayersUnitsVisible(Player p)
    {
        ArrayList<Text> result = new ArrayList<Text>();
        for(Slot s :p.getBackpack())
        {
            Text t = new Text();
            t.setText(s.getUnitName().getElement().getElementName().element+ " "+s.getUnitName().getType().getTypeName().type+" "+s.getNumberOfUnits());
            t.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR,16));
            result.add(t);
        }
        return result;
    }
}
