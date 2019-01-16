package Game;
import Utility.*;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.*;


import java.util.ArrayList;

public class Game extends Application {
    private int playerTurn=1;
    private int enabledSlotID = -1;
    private int enabledFieldID = -1;
    private Text turnLabel;
    private ArrayList<Field> fields;
    private Player[]players;
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
        String url1 ="https://images.unsplash.com/photo-1519120693210-d47b03b31d77?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=3b8d462021896b6a8825629fb69a04b4&auto=format&fit=crop&w=1537&q=80";
        Scene scene = new Scene(root,1366,768);
        Image img1 = new Image(url1);
        ImagePattern pat = new ImagePattern(img1);
        scene.setFill(pat);
        BoardSetter setter = new BoardSetter(this,players);
        ArrayList<Text> unitsOfPlayer1 = new ArrayList<>();
        ArrayList<Text> unitsOfPlayer2 = new ArrayList<>();
        ObservableList list = setter.setBoard(root,players,unitsOfPlayer1,unitsOfPlayer2);
        turnLabel = BoardSetter.createTurnLabel();
        fields = new ArrayList<Field>();
        BattlefieldManager manager = new BattlefieldManager(this,players);
        TextField amountOfUnits = manager.createTextFieldForAmountOfUnits(384,650);
        Text amountOfUnitsText = manager.createAmountOfUnitsText(370,690);
        CheckBox moveCheckBox = manager.createCheckBox("Move",600,650);
        CheckBox attackCheckBox = manager.createCheckBox("Attack",700,650);
        CheckBox moveFromBackpackCheckBox = manager.createCheckBox("Move from backpack",800,650);
        manager.setDisablingCheckBoxes(moveCheckBox,moveFromBackpackCheckBox,attackCheckBox,this);
        for(int row = 0;row < 12;row++)
        {
            for(int column = 0;column<12;column++)
            {
                Field f = manager.createField(384+(column*50),45+(row*50),(row*12)+column,amountOfUnits,moveFromBackpackCheckBox,moveCheckBox,attackCheckBox);
                list.add(f.getFieldSquare());
                list.add(f.getText());
                fields.add(f);
            }
        }
        list.addAll(amountOfUnits,amountOfUnitsText,moveCheckBox,attackCheckBox,moveFromBackpackCheckBox,turnLabel,BoardSetter.createExitButton(), BoardSetter.createPlayerLabel(1,100,30,Color.DARKBLUE),BoardSetter.createPlayerLabel(2,1155,30,Color.DARKRED));
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

    public void setEnabledFieldID(int enabledFieldID) {
        this.enabledFieldID = enabledFieldID;
    }

    public int getEnabledFieldID() {
        return enabledFieldID;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void changePlayerTurn(int playerTurn) {
        if(playerTurn==1)
        {
            this.playerTurn=2;
        }
        else
        {
            this.playerTurn=1;
        }
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public Text getTurnLabel() {
        return turnLabel;
    }

    public void setTurnLabel(int playerNumber) {
        this.turnLabel.setText("Player "+playerNumber+" has turn");
    }
    public void cleanUpAfterTurn()
    {
        boolean ifWon = true;
        boolean ifPlayerOneWon = false;
        boolean ifPlayerTwoWon = false;
        for(Slot s : this.getPlayers()[0].getBackpack())
        {
            if(!s.isDroppedOnField())
            {
                ifWon=false;
            }
        }
        for(Field f:this.getFields())
        {
            if(f.getFieldSlot()!=null && f.getFieldSlot().getPlayer().getPlayerNumber()==1)
            {
                ifWon=false;
            }
        }
        if(ifWon==true)
        {
            ifPlayerTwoWon=true;
        }
        ifWon=true;
        for(Slot s : this.getPlayers()[1].getBackpack())
        {
            if(!s.isDroppedOnField())
            {
                ifWon=false;
            }
        }
        for(Field f:this.getFields())
        {
            if(f.getFieldSlot()!=null && f.getFieldSlot().getPlayer().getPlayerNumber()==2)
            {
                ifWon=false;
            }
        }
        if(ifWon==true)
        {
            ifPlayerOneWon=true;
        }
        for(Slot slot:this.getPlayers()[this.getPlayerTurn()-1].getBackpack())
        {
            Glow g = new Glow();
            g.setLevel(0);
            slot.getRepresentation().setEffect(g);
        }
        for(Field f:this.getFields())
        {
            Glow g = new Glow();
            g.setLevel(0);
            f.getText().setEffect(g);
        }
        this.changePlayerTurn(this.getPlayerTurn());
        this.setEnabledSlotID(-1);
        this.setEnabledFieldID(-1);
        this.setTurnLabel(this.getPlayerTurn());
        BattlefieldManager.setBoardColor(Color.GREEN,this);
        if(ifPlayerOneWon)
        {
            if(ifPlayerTwoWon)
            {
                this.getTurnLabel().setX(575);
                this.getTurnLabel().setText("Game over! Draw!");
            }
            else
            {
                this.getTurnLabel().setX(550);
                this.getTurnLabel().setText("Game over! Player 1 won!");
                return;
            }
        }
        else
        {
            if(ifPlayerTwoWon)
            {
                this.getTurnLabel().setX(550);
                this.getTurnLabel().setText("Game over! Player 2 won!");
                return;
            }
        }
    }
}
