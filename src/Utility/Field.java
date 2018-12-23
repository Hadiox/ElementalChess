package Utility;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field {
    private Text text;
    private Rectangle fieldSquare;
    private Slot fieldSlot;
    private int fieldID;

    public Field(int fieldID) {
        this.fieldSquare = null;
        this.fieldSlot = null;
        this.fieldID = fieldID;
        this.text = new Text(50,50,"");
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void setFieldSquare(Rectangle fieldSquare) {
        this.fieldSquare = fieldSquare;
    }

    public void setFieldSlot(Slot fieldSlot) {
        this.fieldSlot = fieldSlot;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public Rectangle getFieldSquare() {
        return fieldSquare;
    }

    public Slot getFieldSlot() {
        return fieldSlot;
    }

    public int getFieldID() {
        return fieldID;
    }
}
