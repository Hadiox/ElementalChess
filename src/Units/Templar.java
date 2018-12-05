package Units;
import Elements.Element;
import javafx.scene.text.Text;

public class Templar extends Unit {
    public Templar(Element e)
    {
        this.setType(new Type(TypeName.TEMPLAR,TypeName.MAGE,TypeName.ARCHER,new Text("⚔")));
        this.setLife(300);
        this.setSpeed(8);
        this.setSight(2);
        this.setElement(e);
    }
}
