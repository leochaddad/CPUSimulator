package br.maua.ui.elements.internalparts.draghandle;

import br.maua.ui.enums.DragHandleType;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DragHandle extends Group {
    DragHandleType type;
    private final double SIZE = 8;
    private final Rectangle shape = new Rectangle(SIZE,SIZE, Color.SLATEGRAY);
    private final Rectangle invisibleShape = new Rectangle(18,18, Color.TRANSPARENT);


    public DragHandle(Double x, Double y, DragHandleType type) {
        this.type = type;
        this.getChildren().addAll(shape, invisibleShape);
        setPosition(x, y);

    }

    public void setPosition(Double x, Double y){
        shape.setArcHeight(4);
        shape.setArcWidth(4);
        this.setLayoutX(x-SIZE/2);
        this.setLayoutY(y-SIZE/2);
    }


}
