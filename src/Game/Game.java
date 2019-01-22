package Game;
import Utility.*;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
        MenuHandler menuHandler = new MenuHandler();
        this.players=menuHandler.chooseUnits(this);
        Group root = new Group();
        Scene scene = new Scene(root,1366,768);
        BoardSetter setter = new BoardSetter();
        setter.createBackground(scene);
        ObservableList list = setter.setBoard(root,players);
        turnLabel = BoardSetter.createTurnLabel();
        fields = new ArrayList<>();
        BattlefieldManager manager = new BattlefieldManager(this,players);
        TextField amountOfUnits = manager.createAmountOfUnitsTextField(384,650);
        Text amountOfUnitsText = manager.createAmountOfUnitsText(370,690);
        CheckBox moveCheckBox = manager.createCheckBox("Move",600,650);
        CheckBox attackCheckBox = manager.createCheckBox("Attack",700,650);
        CheckBox moveFromBackpackCheckBox = manager.createCheckBox("Move from backpack",800,650);
        manager.setDisablingCheckBoxes(moveCheckBox,moveFromBackpackCheckBox,attackCheckBox,this);
        for(int row = 0;row < 12;row++)
        {
            for(int column = 0;column<12;column++)
            {
                Field actuallyCreated = manager.createField(384+(column*50),45+(row*50),(row*12)+column,amountOfUnits,moveFromBackpackCheckBox,moveCheckBox,attackCheckBox);
                list.add(actuallyCreated.getFieldSquare());
                list.add(actuallyCreated.getText());
                fields.add(actuallyCreated);
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

     private Player[] getPlayers() {
        return players;
    }

     private void changePlayerTurn(int playerTurn) {
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

    private  Text getTurnLabel() {
        return turnLabel;
    }

    private void setTurnLabel(int playerNumber) {
        this.turnLabel.setText("Player "+playerNumber+" has turn");
    }
    public void cleanUpAfterTurn()
    {
        boolean ifPlayerOneWon = checkIfPlayerWon(this.getPlayers()[1]);
        boolean ifPlayerTwoWon = checkIfPlayerWon(this.getPlayers()[0]);
        for(Slot slot:this.getPlayers()[this.getPlayerTurn()-1].getBackpack())
        {
            BoardSetter.createGlow(0,slot.getRepresentation());
        }
        for(Field f:this.getFields())
        {
            BoardSetter.createGlow(0,f.getText());
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
            }
        }
        else
        {
            if(ifPlayerTwoWon)
            {
                this.getTurnLabel().setX(550);
                this.getTurnLabel().setText("Game over! Player 2 won!");
            }
        }
    }
    private boolean checkIfPlayerWon(Player player)
    {
        boolean ifWon = true;
        for(Slot backpackSlot : player.getBackpack())
        {
            if(!backpackSlot.isDroppedOnField())
            {
                ifWon=false;
            }
        }
        for(Field fieldChecked:this.getFields())
        {
            if(fieldChecked.getFieldSlot()!=null && fieldChecked.getFieldPlayerNumber()==player.getPlayerNumber())
            {
                ifWon=false;
            }
        }
        return ifWon;
    }
}
