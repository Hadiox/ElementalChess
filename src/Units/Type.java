package Units;


public class Type {
    private TypeName typeName;
    private final static int typeStrongValueMin = 5;
    private final static int typeStrongValueMax = 10;
    private TypeName typeStrong;
    private final static int typeVulnerableValueMin = 5;
    private final static int typeVulnerableValueMax = 10;
    private TypeName typeVulnerable;
    private String symbol;

    private void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    private void setTypeVulnerable(TypeName typeVulnerable) {
        this.typeVulnerable = typeVulnerable;
    }

    public int getTypeVulnerableValueMin() {
        return typeVulnerableValueMin;
    }

    public int getTypeVulnerableValueMax() {
        return typeVulnerableValueMax;
    }

    public TypeName getTypeVulnerable() {
        return typeVulnerable;
    }
    Type(TypeName type, TypeName typeStrong, TypeName typeVulnerable,String symbol) {
        this.setTypeName(type);
        this.setTypeStrong(typeStrong);
        this.setTypeVulnerable(typeVulnerable);
        this.setSymbol(symbol);
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public int getTypeStrongValueMin() {
        return typeStrongValueMin;
    }

    public int getTypeStrongValueMax() {
        return typeStrongValueMax;
    }

    public TypeName getTypeStrong() {
        return typeStrong;
    }

    private void setTypeName(TypeName type) {
        this.typeName = type;
    }

    private void setTypeStrong(TypeName typeStrong) {
        this.typeStrong = typeStrong;
    }
}
