import Utility.ArmyCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public static void main (String[]args)
    {
        Game game = new Game();
        game.startingMessage();
        while (true)
        {
            String st = getCommand();
            if (st.equals("start"))
            {
                ArmyCreator armyCreator = new ArmyCreator();
            }
            else
                {
                    if (st.equals("exit"))
                    {
                        System.exit(0);
                    }
                    else {
                         System.out.println("Wrong input! Type 'start' to enter army creator or 'exit' to exit the game!");
                     }
            }
        }
    }
    public static String getCommand()
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
    public void startingMessage()
    {
        System.out.println("Welcome to ElementalChess! Type 'start' to open army creator. If you want to exit - type 'exit'");
    }

}
