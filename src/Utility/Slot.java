package Utility;
import Units.Unit;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Slot {
    private int numberOfUnits;
    private Unit UnitName;
    private int slotLife;
    private int cost;
    private Text representation;

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public int getSlotLife() {
        return slotLife;
    }

    public void setSlotLife(int slotLife) {
        this.slotLife = slotLife;
    }

    public void setUnitName(Unit unitName) {
        UnitName = unitName;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public Unit getUnitName() {
        return UnitName;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setRepresentation(Text representation) {
        this.representation = representation;
    }

    public Text getRepresentation() {
        return representation;
    }

    public int getCost() {
        return cost;
    }

    public Slot(int numberOfUnits, Unit unitName) {
        this.numberOfUnits = numberOfUnits;
        this.UnitName = unitName;
        this.slotLife = unitName.getLife()*numberOfUnits;
        this.cost = unitName.getUnitCost()*numberOfUnits;
        Text t = new Text();
        t.setText(unitName.getElement().getElementName().element+ " "+unitName.getType().getTypeName().type+" "+getNumberOfUnits());
        t.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR,16));
        this.setRepresentation(t);
    }
}
