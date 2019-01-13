package Units;
import Elements.Element;
import javafx.scene.text.Text;

public class Archer extends Unit {
    public Archer(Element e)
    {
        this.setType(new Type(TypeName.ARCHER, TypeName.TEMPLAR, TypeName.MAGE,"↟"));
        this.setLife(100);
        this.setSpeed(5);
        this.setSight(5);
        this.setElement(e);
    }
}
