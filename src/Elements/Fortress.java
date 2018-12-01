package Elements;

import Elements.Element;

public class Fortress extends Element {
    public Fortress()
    {
        this.setElementName(ElementName.FORTRESS);
        this.setElementStrong(ElementName.SHADOW);
        this.setElementVulnerable(ElementName.ABYSS);
    }
}
