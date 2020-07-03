package br.maua.ui.elements;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public abstract class Draggable extends Group {


    protected Group format; //None


    public double getCenterX() {
        return this.getBoundsInLocal().getCenterX();
    } //Draggable

    public double getCenterY() {
        return this.getBoundsInLocal().getCenterY();
    } //Draggabe

//    public Draggable(DraggableType type) {
//        this.draggableType = type;
//    } //CP

    public Draggable() {}

//    public DraggableType getDraggableType() {
//        return draggableType;
//    } //CP

//    public Draggable createNewDraggable(){ //Factory
//        Draggable returnType = null;
//        switch (draggableType){
//            case ALU:returnType = new AluComponent();
//            break;
//            case BUS:returnType = new BusComponent();
//            break;
//            case MEMORY:returnType = new MemoryComponent();
//            break;
//            case COUNTER:returnType = new CounterComponent();
//            break;
//            case REGISTER:returnType = new RegisterComponent();
//            break;
//            case DATA_SPLITTER:returnType = new DataSplitterComponent();
//            break;
//            case CONTROL_MULTIPLEXER:returnType = new ControlMultiplexerComponent();
//            break;
//        }
//        return returnType;
//    }

    public void relocateToPoint (Point2D p) { //Draggable
        Point2D localCoords = getParent().sceneToLocal(p);
        relocate (
                (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
        );
    }

//    public void select(){ //Selectable
//
//    }
//
//    public void deselect(){ //Selectable
//
//    }
//
//    private void addConnexionPoints(){ //Connectable
//
//    }
}
