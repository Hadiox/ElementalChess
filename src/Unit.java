public class Unit {
    private Type type;
    private Element element;
    private static int attackMin = 15;
    private static int attackMax = 20;
    private int life;
    private int sight;
    private int speed;

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

    public void setLife(int life) {
        this.life = life;
    }

    public void setSight(int sight) {
        this.sight = sight;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
