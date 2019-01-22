package Units;

import Elements.Element;
import Elements.ElementName;

public class Unit {
    private Type type;
    private Element element;
    private final static int attackMin = 30;
    private final static int attackMax = 40;
    private int life;
    private int sight;
    private int speed;
    private final static int unitCost=5;
    public static int getUnitCost() {
        return unitCost;
    }
    public Type getType() {
        return type;
    }

    public Element getElement() {
        return element;
    }

    public ElementName getElementStrong()
    {
        return this.getElement().getElementStrong();
    }
    public ElementName getElementVulnerable()
    {
        return this.getElement().getElementVulnerable();
    }

    public int getElementVulnerableValueMin()
    {
        return this.getElement().getElementVulnerableValueMin();
    }

    public int getElementVulnerableValueMax()
    {
        return this.getElement().getElementVulnerableValueMax();
    }

    public int getElementStrongValueMin()
    {
        return this.getElement().getElementStrongValueMin();
    }

    public int getElementStrongValueMax()
    {
        return this.getElement().getElementStrongValueMax();
    }

    public ElementName getElementName()
    {
        return this.getElement().getElementName();
    }

    public TypeName getTypeStrong()
    {
        return this.getType().getTypeStrong();
    }
    public TypeName getTypeVulnerable()
    {
        return this.getType().getTypeVulnerable();
    }

    public int getTypeVulnerableValueMin()
    {
        return this.getType().getTypeVulnerableValueMin();
    }

    public int getTypeVulnerableValueMax()
    {
        return this.getType().getTypeVulnerableValueMax();
    }

    public int getTypeStrongValueMin()
    {
        return this.getType().getTypeStrongValueMin();
    }

    public int getTypeStrongValueMax()
    {
        return this.getType().getTypeStrongValueMax();
    }

    public TypeName getTypeName()
    {
        return this.getType().getTypeName();
    }

    public static int getAttackMin() {
        return attackMin;
    }

    public static int getAttackMax() {
        return attackMax;
    }

    public int getLife() {
        return life;
    }

    public int getSight() {
        return sight;
    }

    public int getSpeed() {
        return speed;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    void setLife() {
        this.life = 100;
    }

    void setSight(int sight) {
        this.sight = sight;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }
}
