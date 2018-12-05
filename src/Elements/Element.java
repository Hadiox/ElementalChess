package Elements;

import javafx.scene.text.Text;

public abstract class Element {
    private ElementName elementName;
    private static int elementStrongValueMin = 5;
    private static int elementStrongValueMax = 10;
    private ElementName elementStrong;
    private static int elementVulnerableValueMin = 5;
    private static int elementVulnerableValueMax = 10;
    private ElementName elementVulnerable;
    private Text symbol;

    public void setSymbol(Text sybol) {
        this.symbol = sybol;
    }

    public Text getSybol() {
        return symbol;
    }

    public void setElementVulnerable(ElementName elementVulnerable) {
        this.elementVulnerable = elementVulnerable;
    }

    public static int getElementVulnerableValueMin() {
        return elementVulnerableValueMin;
    }

    public static int getElementVulnerableValueMax() {
        return elementVulnerableValueMax;
    }

    public ElementName getElementVulnerable() {
        return elementVulnerable;
    }

    public ElementName getElementName() {
        return elementName;
    }

    public static int getElementStrongValueMin() {
        return elementStrongValueMin;
    }

    public static int getElementStrongValueMax() {
        return elementStrongValueMax;
    }

    public ElementName getElementStrong() {
        return elementStrong;
    }

    public void setElementName(ElementName element) {
        this.elementName = element;
    }

    public void setElementStrong(ElementName elementStrong) {
        this.elementStrong = elementStrong;
    }
}
