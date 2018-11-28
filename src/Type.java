public class Type {
    private TypeName type;
    private static int typeStrongValueMin = 15;
    private static int typeStrongValueMax = 20;
    private TypeName typeStrong;
    public Type(TypeName type, TypeName typeStrong) {
        this.type = type;
        this.typeStrong = typeStrong;
    }

    public TypeName getType() {
        return type;
    }

    public static int getTypeStrongValueMin() {
        return typeStrongValueMin;
    }

    public static int getTypeStrongValueMax() {
        return typeStrongValueMax;
    }

    public TypeName getTypeStrong() {
        return typeStrong;
    }

    public void setType(TypeName type) {
        this.type = type;
    }

    public void setTypeStrong(TypeName typeStrong) {
        this.typeStrong = typeStrong;
    }
}
