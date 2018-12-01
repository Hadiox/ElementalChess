package Utility;
import java.util.ArrayList;
public class Player {
    private String playerName;
    private int playerNumber;
    private ArrayList<Slot> backpack;

    public Player(String playerName, int playerNumber) {
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.backpack = new ArrayList<Slot>();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setBackpack(ArrayList<Slot> backpack) {
        this.backpack = backpack;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public ArrayList<Slot> getBackpack() {
        return backpack;
    }
}
