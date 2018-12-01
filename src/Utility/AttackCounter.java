package Utility;
import Units.Unit;
public class AttackCounter {
    public int countAttack(Unit attacker, Unit defender)
    {
        int attack = Unit.getAttackMin() + ((int)((double)(Unit.getAttackMax() - Unit.getAttackMin())*Math.random()));

        int attackElement=0;
        if(attacker.getElement().getElementStrong() == defender.getElement().getElementName())
        {
            attackElement =(int)((double) (attacker.getElement().getElementStrongValueMax()-attacker.getElement().getElementStrongValueMin())*Math.random());
            attackElement+= attacker.getElement().getElementStrongValueMin();
        }
        else
        {
            if(attacker.getElement().getElementVulnerable() == defender.getElement().getElementName())
            {
                attackElement =-((int)((double) (attacker.getElement().getElementVulnerableValueMax()-attacker.getElement().getElementVulnerableValueMin())*Math.random()));
                attackElement -= attacker.getElement().getElementVulnerableValueMin();
            }
            else
            {
                attackElement = 0;
            }
        }
        int attackType=0;
        if(attacker.getType().getTypeStrong() == defender.getType().getTypeName())
        {
            attackType =(int)((double) (attacker.getType().getTypeStrongValueMax()-attacker.getType().getTypeStrongValueMin())*Math.random());
            attackType+= attacker.getType().getTypeStrongValueMin();
        }
        else
        {
            if(attacker.getType().getTypeVulnerable() == defender.getType().getTypeName())
            {
                attackType =-((int)((double) (attacker.getType().getTypeVulnerableValueMax()-attacker.getType().getTypeVulnerableValueMin())*Math.random()));
                attackType -= attacker.getType().getTypeVulnerableValueMin();
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
        System.out.println(attack);
        return attack;
    }
}
