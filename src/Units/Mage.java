package Units;

import Elements.Element;
import javafx.scene.text.Text;

public class Mage extends Unit {
    public Mage(Element e)
    {
        this.setType(new Type(TypeName.MAGE,TypeName.ARCHER,TypeName.TEMPLAR,"★"));
        this.setLife(300);
        this.setSpeed(2);
        this.setSight(8);
        this.setElement(e);
    }
}
