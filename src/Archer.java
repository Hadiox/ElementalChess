public class Archer extends Unit{
    public Archer(Element e)
    {
        this.setType(new Type (TypeName.ARCHER,TypeName.TEMPLAR));
        this.setLife(300);
        this.setSpeed(5);
        this.setSight(5);
        this.setElement(e);
    }
}
