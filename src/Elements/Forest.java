package Elements;

import javafx.scene.text.Text;

public class Forest extends Element {
    public Forest()
    {
        this.setElementName(ElementName.FOREST);
        this.setElementStrong(ElementName.ABYSS);
        this.setElementVulnerable(ElementName.SHADOW);
        this.setSymbol(new Text("♠"));
    }
}
