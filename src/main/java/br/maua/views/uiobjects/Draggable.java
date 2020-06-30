package br.maua.views.uiobjects;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Draggable extends Group {

    public Draggable() {
        super(new Rectangle(100,180, Color.DARKORANGE));
    }


    public void setPosition(int x, int y){
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void relocateToPoint (Point2D p) {
        Point2D localCoords = getParent().sceneToLocal(p);
        relocate (
                (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
        );
    }
}
