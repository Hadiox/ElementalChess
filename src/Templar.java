public class Templar extends Unit {
    public Templar(Element e)
    {
        this.type = new Type (TypeName.TEMPLAR,TypeName.MAGE);
        this.life = 300;
        this.speed = 8;
        this.sight = 2;
        this.element = e;
    }
}
