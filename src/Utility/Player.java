package Utility;
import java.util.ArrayList;
public class Player {
    private int playerNumber;
    private ArrayList<Slot> backpack;
    private int points;

    void setPoints(int points) {
        this.points = points;
    }

    int getPoints() {
        return points;
    }

    Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.backpack = new ArrayList<>();
        this.points = 1000;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public ArrayList<Slot> getBackpack() {
        return backpack;
    }
}
