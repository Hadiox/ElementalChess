package Elements;
public abstract class Element {
    private ElementName elementName;
    private final static int elementStrongValueMin = 5;
    private final static int elementStrongValueMax = 10;
    private ElementName elementStrong;
    private final static int elementVulnerableValueMin = 5;
    private final static int elementVulnerableValueMax = 10;
    private ElementName elementVulnerable;
    private String symbol;

    void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

     void setElementVulnerable(ElementName elementVulnerable) {
        this.elementVulnerable = elementVulnerable;
    }

    public int getElementVulnerableValueMin() {
        return elementVulnerableValueMin;
    }

    public int getElementVulnerableValueMax() {
        return elementVulnerableValueMax;
    }

    public ElementName getElementVulnerable() {
        return elementVulnerable;
    }

    public ElementName getElementName() {
        return elementName;
    }

    public int getElementStrongValueMin() {
        return elementStrongValueMin;
    }

    public int getElementStrongValueMax() {
        return elementStrongValueMax;
    }

    public ElementName getElementStrong() {
        return elementStrong;
    }

    void setElementName(ElementName element) {
        this.elementName = element;
    }

    void setElementStrong(ElementName elementStrong) {
        this.elementStrong = elementStrong;
    }
}
