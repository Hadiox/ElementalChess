package Elements;

import Elements.Element;
import javafx.scene.text.Text;

public class Fortress extends Element {
    public Fortress()
    {
        this.setElementName(ElementName.FORTRESS);
        this.setElementStrong(ElementName.SHADOW);
        this.setElementVulnerable(ElementName.ABYSS);
        this.setSymbol(new Text("✚"));
    }
}
