package Utility;
import Units.Unit;
 class AttackCounter {
    private static int countAttackOfOneUnit(Unit attacker, Unit defender)
    {
        int attack = Unit.getAttackMin() + ((int)((double)(Unit.getAttackMax() - Unit.getAttackMin())*Math.random()));

        int attackElement;
        if(attacker.getElementStrong().equals(defender.getElementName()))
        {
            attackElement =(int)((double) (attacker.getElementStrongValueMax()-attacker.getElementStrongValueMin())*Math.random());
            attackElement+= attacker.getElementStrongValueMin();
        }
        else
        {
            if(attacker.getElementVulnerable().equals(defender.getElementName()))
            {
                attackElement =-((int)((double) (attacker.getElementVulnerableValueMax()-attacker.getElementVulnerableValueMin())*Math.random()));
                attackElement -= attacker.getElementVulnerableValueMin();
            }
            else
            {
                attackElement = 0;
            }
        }
        int attackType;
        if(attacker.getTypeStrong().equals(defender.getTypeName()))
        {
            attackType =(int)((double) (attacker.getTypeStrongValueMax()-attacker.getTypeStrongValueMin())*Math.random());
            attackType+= attacker.getTypeStrongValueMin();
        }
        else
        {
            if(attacker.getTypeVulnerable().equals(defender.getTypeName()))
            {
                attackType =-((int)((double) (attacker.getTypeVulnerableValueMax()-attacker.getTypeVulnerableValueMin())*Math.random()));
                attackType -= attacker.getTypeVulnerableValueMin();
            }
            else
            {
                attackType = 0;
            }
        }
        attack+=attackElement;
        attack+=attackType;
        if(attack<5)
        {
            attack = 5;
        }
        return attack;
    }
    static int countAttack(Slot attacker,Slot defender)
    {
        int attack=0;
        for(int a=0;a<attacker.getNumberOfUnits();a++)
        {
            attack+=AttackCounter.countAttackOfOneUnit(attacker.getUnitName(),defender.getUnitName());
        }
        return attack;
    }
}
