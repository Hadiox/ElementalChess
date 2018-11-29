public class Test {
    public static void main(String []args)
    {
        AttackCounter a = new AttackCounter();
        a.countAttack(new Archer(new Forest()),new Mage(new Shadow()));
    }
}
