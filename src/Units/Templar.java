public class Templar extends Unit {
    public Templar(Element e)
    {
        this.setType(new Type (TypeName.TEMPLAR,TypeName.MAGE,TypeName.ARCHER));
        this.setLife(300);
        this.setSpeed(8);
        this.setSight(2);
        this.setElement(e);
    }
}
