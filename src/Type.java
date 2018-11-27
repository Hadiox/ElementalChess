public class Type {
    protected TypeName type;
    protected static int typeStrongValueMin = 15;
    protected static int typeStrongValueMax = 20;
    protected TypeName typeStrong;
    public Type(TypeName type, TypeName typeStrong) {
        this.type = type;
        this.typeStrong = typeStrong;
    }
}
