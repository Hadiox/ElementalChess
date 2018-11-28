public abstract class Element {
    private ElementName element;
    private static int elementStrongValueMin = 15;
    private static int elementStrongValueMax = 20;
    private ElementName elementStrong;

    public ElementName getElement() {
        return element;
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

    public void setElement(ElementName element) {
        this.element = element;
    }

    public void setElementStrong(ElementName elementStrong) {
        this.elementStrong = elementStrong;
    }
}
