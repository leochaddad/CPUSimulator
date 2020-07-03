package br.maua.ui.elements;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public abstract class Draggable extends Group {


    protected Group format; //None


    public double getCenterX() {
        return this.getBoundsInLocal().getCenterX();
    }

    public double getCenterY() {
        return this.getBoundsInLocal().getCenterY();
    }

    public Draggable() {}


    public void relocateToPoint (Point2D p) { //Draggable
        Point2D localCoords = getParent().sceneToLocal(p);
        relocate (
                (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
        );
    }

}
