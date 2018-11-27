public class Mage extends Unit {
    public Mage(Element e)
    {
        this.type = new Type (TypeName.MAGE,TypeName.ARCHER);
        this.life = 300;
        this.speed = 2;
        this.sight = 8;
        this.element = e;
    }
}
