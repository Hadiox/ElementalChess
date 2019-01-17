package Utility;
import Game.Game;
import Units.Unit;
import javafx.event.EventHandler;
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
    private Player player;
    private Unit UnitName;
    private int cost;
    private Text representation;
    private Text fieldRepresentation;
    private int lifeLost;

    void setLifeLost(int lifeLost) {
        this.lifeLost = lifeLost;
    }

    int getLifeLost() {
        return lifeLost;
    }

    public Text getFieldRepresentation() {
        return fieldRepresentation;
    }

    void setDroppedOnField() {
        this.droppedOnField = true;
    }

    public boolean isDroppedOnField() {
        return droppedOnField;
    }

    public Player getPlayer() {
        return player;
    }

    int getSlotID() {
        return slotID;
    }


    private void setIfSelected() {
        this.ifSelected = false;
    }

    void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    int getNumberOfUnits() {
        return numberOfUnits;
    }

    Unit getUnitName() {
        return UnitName;
    }


    private void setRepresentation(Text representation) {
        this.representation = representation;
    }

    public Text getRepresentation() {
        return representation;
    }

    int getCost() {
        return cost;
    }

    Slot(int numberOfUnits, Unit unitName,Game game,int ID,Player player) {
        this.slotID = ID;
        this.ifSelected = false;
        this.player=player;
        this.droppedOnField = false;
        this.numberOfUnits = numberOfUnits;
        this.UnitName = unitName;
        this.fieldRepresentation=new Text(unitName.getElement().getSymbol()+unitName.getType().getSymbol()+numberOfUnits);
        this.fieldRepresentation.setVisible(false);
        this.cost = Unit.getUnitCost()*numberOfUnits;
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
                                s.setIfSelected();
                                BoardSetter.createGlow(0,s.getRepresentation());
                            }
                            ifSelected = true;
                            game.setEnabledSlotID(slotID);
                            BoardSetter.createGlow(4,t);
                        }
                    } else {
                        if (!isDroppedOnField()) {
                            ifSelected = false;
                            game.setEnabledSlotID(-1);
                            BoardSetter.createGlow(0,t);
                        }
                    }
                }
            }
        };
        t.addEventFilter(MouseEvent.MOUSE_CLICKED,eventHandler);
        this.setRepresentation(t);
    }
}
