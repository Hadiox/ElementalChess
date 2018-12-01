package Utility;
import Units.Unit;
public class Slot {
    private int numberOfUnits;
    private Unit UnitName;
    private int slotLife;
    private int cost;
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

    public int getCost() {
        return cost;
    }

    public Slot(int numberOfUnits, Unit unitName) {
        this.numberOfUnits = numberOfUnits;
        this.UnitName = unitName;
        this.slotLife = unitName.getLife()*numberOfUnits;
        this.cost = unitName.getUnitCost()*numberOfUnits;
    }
}
