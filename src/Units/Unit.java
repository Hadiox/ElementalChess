package Units;

import Elements.Element;

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
