public class AttackCounter {
    public int countAttack(Unit attacker, Unit defender)
    {
        int attack = Unit.attackMin + ((int)((double)(Unit.attackMax - Unit.attackMin)*Math.random()));
        double attackPlus = (double)(Unit.attackMax - Unit.attackMin);
        int attackPlus2 = (int)(attackPlus*Math.random());
        attack+=attackPlus2;
        return attack;
    }
}
