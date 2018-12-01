package Game;

import Elements.Fortress;
import Elements.Shadow;
import Units.Mage;
import Units.Templar;
import Utility.AttackCounter;

public class Test {
    public static void main(String []args)
    {
        AttackCounter a = new AttackCounter();
        a.countAttack(new Templar(new Fortress()),new Mage(new Shadow()));
    }
}
