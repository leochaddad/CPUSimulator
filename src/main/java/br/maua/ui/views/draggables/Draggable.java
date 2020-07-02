package br.maua.ui.views.draggables;

import br.maua.ui.views.ConnexionPoint;
import br.maua.ui.views.draggables.simple.*;
import br.maua.ui.enums.DraggableType;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;


import java.util.ArrayList;

public class Draggable extends Group {


    protected DraggableType draggableType = null;
    protected Group format;
    protected SimpleStringProperty displayText;

    public ArrayList<ConnexionPoint> getConnexionPoints() {
        return connexionPoints;
    }

    protected ArrayList<ConnexionPoint> connexionPoints = new ArrayList<>();

    public double getCenterX() {
        return this.getBoundsInLocal().getCenterX();
    }

    public double getCenterY() {
        return this.getBoundsInLocal().getCenterY();
    }

    public Draggable(DraggableType type) {
        this.draggableType = type;
    }

    public Draggable() {}

    public DraggableType getDraggableType() {
        return draggableType;
    }

    public Draggable createNewDraggable(){
        Draggable returnType = null;
        switch (draggableType){
            case ALU:returnType = new AluDraggable();
            break;
            case BUS:returnType = new BusDraggable();
            break;
            case MEMORY:returnType = new MemoryDraggable();
            break;
            case COUNTER:returnType = new CounterDraggable();
            break;
            case REGISTER:returnType = new RegisterDraggable();
            break;
            case DATA_SPLITTER:returnType = new DataSplitterDraggable();
            break;
            case CONTROL_MULTIPLEXER:returnType = new ControlMultiplexerDraggable();
            break;
        }
        return returnType;
    }

    public void relocateToPoint (Point2D p) {
        Point2D localCoords = getParent().sceneToLocal(p);
        relocate (
                (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
        );
    }

    public void select(){

    }

    public void deselect(){

    }

    private void addConnexionPoints(){

    }
}
