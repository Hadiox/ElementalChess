package Utility;

import Game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuHandler {
    static String getCommand()
    {
        String st = null;
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            st = reader.readLine();
        }
        catch(IOException e)
        {
            System.out.println("Error with input!");
            e.printStackTrace();
        }
        return st;
    }
    private static void startingMessage()
    {
        System.out.println("Welcome to ElementalChess! Type 'start' to open army creator. If you want to exit - type 'exit'");
    }
    public Player[] chooseUnits(Game game)
    {
        MenuHandler.startingMessage();
        Player[]players;
        while (true) {
            String st = MenuHandler.getCommand();
            if (st.equals("start"))
            {
                ArmyCreator armyCreator = new ArmyCreator();
                armyCreator.setArmy(game);
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
        return players;
    }
}
