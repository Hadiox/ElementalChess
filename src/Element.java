public abstract class Element {
    private ElementName elementName;
    private static int elementStrongValueMin = 15;
    private static int elementStrongValueMax = 20;
    private ElementName elementStrong;
    private static int elementVulnerableValueMin = 10;
    private static int elementVulnerableValueMax = 15;
    private ElementName elementVulnerable;

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
