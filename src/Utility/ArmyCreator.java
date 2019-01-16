package Utility;
import Elements.*;
import Units.Archer;
import Units.Mage;
import Units.Templar;
import Units.Unit;
import Game.Game;

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
    public void setArmy(Game game)
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
    public void setForPlayer(Player p,Game game)
    {
        int slotID = 0;
        while(true)
        {
            String command = MenuHandler.getCommand();
            if(command.equals("finish"))
            {
                if(p.getBackpack().isEmpty())
                {
                    System.out.println("You did not choose any units! Try again!");
                }
                else {
                    return;
                }
            }
            else
            {
                String [] com = command.toString().split(" ");
                if(com.length != 3)
                {
                    System.out.println("Typed wrong command! Try again.");
                }
                else
                {
                    try
                    {
                        Slot s = new Slot(Integer.parseInt(com[2]),chooseUnit(com[1],com[0]),game,slotID,p);
                        if(s.getCost()>p.getPoints())
                        {
                            System.out.println("You can't afford such units! Get cheaper slot!");
                        }
                        else
                        {
                            boolean flag = false;
                            for(Slot s2:p.getBackpack())
                            {
                                if(s2.getUnitName().getElement().getElementName().equals(s.getUnitName().getElement().getElementName()) && s2.getUnitName().getType().getTypeName().equals(s.getUnitName().getType().getTypeName()))
                                {
                                    flag = true;
                                    s2.setNumberOfUnits(s2.getNumberOfUnits()+s.getNumberOfUnits());
                                    p.setPoints(p.getPoints()-s.getCost());
                                    System.out.println("Units bought! Now you have: " + p.getPoints() + " points left!");
                                }
                            }
                            if(!flag)
                            {
                                p.getBackpack().add(s);
                                p.setPoints(p.getPoints()-s.getCost());
                                System.out.println("Units bought! Now you have: " + p.getPoints() + " points left!");
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
        }
    }
    public Unit chooseUnit(String unit, String element) throws UnexpectedUnitNameException,UnexpectedElementNameException
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
    public Element chooseElement(String element) throws UnexpectedElementNameException
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
}
