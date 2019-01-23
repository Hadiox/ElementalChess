package Utility;
import Elements.*;
import Units.Archer;
import Units.Mage;
import Units.Templar;
import Units.Unit;
import Game.Game;

class ArmyCreator {
    private Player[] players;
     ArmyCreator()
    {
        Player[] players = new Player[2];
        players[0] = new Player(1);
        players[1] = new Player(2);
        this.players = players;
    }

    Player[] getPlayers() {
        return players;
    }
    void setArmy(Game game)
    {
        System.out.println("First Player 1 will set his army. There are 3 types of units: Archer, Mage and Templar.\n" +
                "They can have 1 of 4 elements: Abyss, Fortress, Forest and Shadow. You have 1000 points to spend\n" +
                "on your units. Each unit costs 5 points. To buy units type: <unit element> <unit type> <number of units>\n" +
                "for example to buy 20 Forest Archers type: Forest Archer 20\n" +
                "To end choosing your army type: 'finish' Then Player 2 will set up his army.");
        setForPlayer(this.players[0],game);
        System.out.println("Now Player 2 chooses!");
        setForPlayer(this.players[1],game);
        System.out.println("Finished choosing army!");
    }
    private void setForPlayer(Player processedPlayer, Game game)
    {
        int slotID = 0;
        while(true)
        {
            String command = MenuHandler.getCommand();
            if(command.equals("finish"))
            {
                if(processedPlayer.getBackpack().isEmpty())
                {
                    System.out.println("You did not choose any units! Try again!");
                }
                else {
                    return;
                }
            }
            else
            {
               processCommand(command, processedPlayer,slotID,game);
            }
        }
    }
    private void processCommand(String command, Player processedPlayer, int slotID, Game game)
    {
        String [] commandSplit = command.split(" ");
        if(commandSplit.length != 3)
        {
            System.out.println("Typed wrong command! Try again.");
        }
        else
        {
            try
            {
                Slot slotInserted = new Slot(Integer.parseInt(commandSplit[2]),chooseUnit(commandSplit[1], commandSplit[0]),game,slotID, processedPlayer);
                if(slotInserted.getCost()> processedPlayer.getPoints())
                {
                    System.out.println("You can't afford such amount of units! Get a cheaper slot!");
                }
                else
                {
                    boolean ifExists = checkExistanceInBackpack(processedPlayer,slotInserted);
                    if(!ifExists)
                    {
                        addNewSlot(processedPlayer,slotInserted);
                        slotID++;
                    }
                }
            }
            catch(UnexpectedElementNameException e)
            {
                e.exportError();
            }
            catch(UnexpectedUnitNameException e)
            {
                e.exportError();
            }
            catch(NumberFormatException e)
            {
                System.out.println("Typed a wrong number! Try again!");
            }
        }
    }
    private Unit chooseUnit(String unit, String element) throws UnexpectedUnitNameException,UnexpectedElementNameException
    {
        switch(unit)
        {
            case "Archer":
            {
                return new Archer(chooseElement (element));
            }
            case "Mage":
            {
                return new Mage(chooseElement (element));
            }
            case "Templar":
            {
                return new Templar(chooseElement (element));
            }
            default:
            {
                throw new UnexpectedUnitNameException();
            }
        }
    }

    private Element chooseElement(String element) throws UnexpectedElementNameException
    {
        switch(element)
        {
            case "Abyss":
            {
                return new Abyss();
            }
            case "Shadow":
            {
                return new Shadow();
            }
            case "Fortress":
            {
                return new Fortress();
            }
            case "Forest":
            {
                return new Forest();
            }
            default:
            {
                throw new UnexpectedElementNameException();
            }
        }
    }
    private boolean checkExistanceInBackpack(Player processedPlayer,Slot slotInserted)
    {
        boolean ifExists = false;
        for(Slot slotInBackpack : processedPlayer.getBackpack())
        {
            if(slotInBackpack.getSlotElementName().equals(slotInserted.getSlotElementName()) && slotInBackpack.getSlotTypeName().equals(slotInserted.getSlotTypeName()))
            {
                ifExists = true;
                addToExistingSlot(slotInBackpack,processedPlayer,slotInserted);
            }
        }
        return ifExists;
    }
    private void addToExistingSlot(Slot slotInBackpack,Player processedPlayer,Slot slotInserted)
    {
        slotInBackpack.setNumberOfUnits(slotInBackpack.getNumberOfUnits()+ slotInserted.getNumberOfUnits());
        processedPlayer.setPoints(processedPlayer.getPoints()- slotInserted.getCost());
        System.out.println("Units bought! Now you have: " + processedPlayer.getPoints() + " points left!");

    }
    private void addNewSlot(Player processedPlayer,Slot slotInserted)
    {
        processedPlayer.getBackpack().add(slotInserted);
        processedPlayer.setPoints(processedPlayer.getPoints()- slotInserted.getCost());
        System.out.println("Units bought! Now you have: " + processedPlayer.getPoints() + " points left!");

    }
}
