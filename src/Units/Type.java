package Units;

import javafx.scene.text.Text;

public class Type {
    private TypeName typeName;
    private static int typeStrongValueMin = 5;
    private static int typeStrongValueMax = 10;
    private TypeName typeStrong;
    private static int typeVulnerableValueMin = 5;
    private static int typeVulnerableValueMax = 10;
    private TypeName typeVulnerable;
    private String symbol;

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setTypeVulnerable(TypeName typeVulnerable) {
        this.typeVulnerable = typeVulnerable;
    }

    public static int getTypeVulnerableValueMin() {
        return typeVulnerableValueMin;
    }

    public static int getTypeVulnerableValueMax() {
        return typeVulnerableValueMax;
    }

    public TypeName getTypeVulnerable() {
        return typeVulnerable;
    }
    public Type(TypeName type, TypeName typeStrong, TypeName typeVulnerable,String symbol) {
        this.setTypeName(type);
        this.setTypeStrong(typeStrong);
        this.setTypeVulnerable(typeVulnerable);
        this.setSymbol(symbol);
    }

    public TypeName getTypeName() {
        return typeName;
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

    public void setTypeName(TypeName type) {
        this.typeName = type;
    }

    public void setTypeStrong(TypeName typeStrong) {
        this.typeStrong = typeStrong;
    }
}
