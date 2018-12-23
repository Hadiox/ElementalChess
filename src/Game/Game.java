package Game;
import Utility.*;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.*;


import java.util.ArrayList;

public class Game extends Application {
    int enabledSlotID = -1;
    ArrayList<Field> fields;
    Player[]players;
    public void start(Stage primaryStage)
    {
        //Menu---------------------------------------------------------------------------
        MenuHandler.startingMessage();
        while (true) {
            String st = MenuHandler.getCommand();
            if (st.equals("start"))
            {
                ArmyCreator armyCreator = new ArmyCreator();
                armyCreator.setArmy(this);
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
      //  String url1 ="https://images.unsplash.com/photo-1519120693210-d47b03b31d77?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=3b8d462021896b6a8825629fb69a04b4&auto=format&fit=crop&w=1537&q=80";
        Scene scene = new Scene(root,1366,768);
        //Image img1 = new Image(url1);
        //ImagePattern pat = new ImagePattern(img1);
        //scene.setFill(pat);
        BoardSetter setter = new BoardSetter(this,players);
        ArrayList<Text> unitsOfPlayer1 = new ArrayList<>();
        ArrayList<Text> unitsOfPlayer2 = new ArrayList<>();
        ObservableList list = setter.setBoard(root,players,unitsOfPlayer1,unitsOfPlayer2);
        fields = new ArrayList<Field>();
        BattlefieldManager manager = new BattlefieldManager(this,players);
        TextField amountOfUnits = manager.createTextFieldForAmountOfUnits(384,650);
        Text amountOfUnitsText = manager.createAmountOfUnitsText(370,690);
        for(int row = 0;row < 12;row++)
        {
            for(int column = 0;column<12;column++)
            {
                Field f = manager.createField(384+(column*50),45+(row*50),(row*12)+column,amountOfUnits);
                list.add(f.getFieldSquare());
                list.add(f.getText());
                fields.add(f);
            }
        }
        list.addAll(amountOfUnits,amountOfUnitsText);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String []args)
    {
        launch(args);
    }

    public int getEnabledSlotID() {
        return enabledSlotID;
    }

    public void setEnabledSlotID(int enabledSlot) {
        this.enabledSlotID = enabledSlot;
    }
}
