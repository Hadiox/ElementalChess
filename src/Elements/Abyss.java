package Elements;

import javafx.scene.text.Text;

public class Abyss extends Element {
    public Abyss()
    {
        this.setElementName(ElementName.ABYSS);
        this.setElementStrong(ElementName.FORTRESS);
        this.setElementVulnerable(ElementName.FOREST);
        this.setSymbol(new Text("🔥"));
    }
}
