package Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuHandler {
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
    public static void startingMessage()
    {
        System.out.println("Welcome to ElementalChess! Type 'start' to open army creator. If you want to exit - type 'exit'");
    }
}
