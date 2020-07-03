package br.maua.ui.elements;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;

public abstract class Draggable extends Group {

    protected Group format;

    protected boolean dragEnabled = true;

    public double getCenterX() {
        return this.getBoundsInLocal().getCenterX();
    }

    public double getCenterY() {
        return this.getBoundsInLocal().getCenterY();
    }


    public void relocateToPoint (Point2D p) { //Draggable
        Point2D localCoords = getParent().sceneToLocal(p);
        relocate (
                (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
        );
    }

    public boolean isDragEnabled() {
        return dragEnabled;
    }

    public void setDragEnabled(boolean dragEnabled) {
        this.dragEnabled = dragEnabled;
    }



}
