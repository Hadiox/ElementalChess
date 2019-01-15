package Utility;
import Game.Game;
import Units.Unit;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Slot {
    private int numberOfUnits;
    private boolean droppedOnField;
    private int slotID;
    private boolean ifSelected;
    private Game game;
    private Player player;
    private Unit UnitName;
    private int slotLife;
    private int cost;
    private Text representation;
    private Text fieldRepresentation;
    private int lifeLost;

    public void setLifeLost(int lifeLost) {
        this.lifeLost = lifeLost;
    }

    public int getLifeLost() {
        return lifeLost;
    }

    public void setFieldRepresentation(Text fieldRepresentation) {
        this.fieldRepresentation = fieldRepresentation;
    }

    public Text getFieldRepresentation() {
        return fieldRepresentation;
    }

    public void setDroppedOnField(boolean droppedOnField) {
        this.droppedOnField = droppedOnField;
    }

    public boolean isDroppedOnField() {
        return droppedOnField;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSlotID() {
        return slotID;
    }

    public Game getGame() {
        return game;
    }

    public void setIfSelected(boolean ifSelected) {
        this.ifSelected = ifSelected;
    }

    public boolean getIfSelected() {
        return ifSelected;
    }

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

    public Slot(int numberOfUnits, Unit unitName,Game game,int ID,Player player) {
        this.slotID = ID;
        this.game = game;
        this.ifSelected = false;
        this.player=player;
        this.droppedOnField = false;
        this.numberOfUnits = numberOfUnits;
        this.UnitName = unitName;
        this.fieldRepresentation=new Text(unitName.getElement().getSymbol()+unitName.getType().getSymbol()+numberOfUnits);
        this.fieldRepresentation.setVisible(false);
        this.slotLife = unitName.getLife()*numberOfUnits;
        this.cost = unitName.getUnitCost()*numberOfUnits;
        this.lifeLost=0;
        Text t = new Text();
        t.setText(unitName.getElement().getElementName().element+ " "+unitName.getType().getTypeName().type+" "+getNumberOfUnits());
        t.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR,16));
        EventHandler<MouseEvent> eventHandler=new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e) {
                if (player.getPlayerNumber() == game.getPlayerTurn()) {
                    if (!ifSelected) {
                        if (!isDroppedOnField()) {
                            for (Slot s : player.getBackpack()) {
                                s.setIfSelected(false);
                                Glow g = new Glow();
                                g.setLevel(0);
                                s.getRepresentation().setEffect(g);
                            }
                            ifSelected = true;
                            game.setEnabledSlotID(slotID);
                            Glow g = new Glow();
                            g.setLevel(4);
                            t.setEffect(g);
                        }
                    } else {
                        if (!isDroppedOnField()) {
                            ifSelected = false;
                            game.setEnabledSlotID(-1);
                            Glow g = new Glow();
                            g.setLevel(0);
                            t.setEffect(g);
                        }
                    }
                }
            }
        };
        t.addEventFilter(MouseEvent.MOUSE_CLICKED,eventHandler);
        this.setRepresentation(t);
    }
}
