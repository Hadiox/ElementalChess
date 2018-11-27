public class Archer extends Unit{
    public Archer(Element e)
    {
        this.type = new Type (TypeName.ARCHER,TypeName.TEMPLAR);
        this.life = 300;
        this.speed = 5;
        this.sight = 5;
        this.element = e;
    }
}
