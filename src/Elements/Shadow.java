package Elements;

import javafx.scene.text.Text;

public class Shadow extends Element {
    public Shadow()
    {
        this.setElementName(ElementName.SHADOW);
        this.setElementStrong(ElementName.FOREST);
        this.setElementVulnerable(ElementName.FORTRESS);
        this.setSymbol(new Text("💀"));
    }
}
