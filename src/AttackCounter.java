public class AttackCounter {
    public int countAttack(Unit attacker, Unit defender)
    {
        int attack = Unit.getAttackMin() + ((int)((double)(Unit.getAttackMax() - Unit.getAttackMin())*Math.random()));
        double attackPlus = (double)(Unit.getAttackMax() - Unit.getAttackMin());
        int attackPlus2 = (int)(attackPlus*Math.random());
        attack+=attackPlus2;
        return attack;
    }
}
