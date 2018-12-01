package Utility;
public class ArmyCreator {
    private Player[] players;
    public ArmyCreator()
    {
        Player[] players = new Player[2];
        players[0] = new Player("Player 1",1);
        players[1] = new Player("Player 2",2);
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }
}
