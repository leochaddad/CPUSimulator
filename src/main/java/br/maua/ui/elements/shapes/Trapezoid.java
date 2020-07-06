package br.maua.ui.elements.shapes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class Trapezoid extends Group {


    private Shape shape;

    public Trapezoid() {
        this.createShape();
        this.getChildren().add(shape);
    }


    private void createShape(){
        String path = "m0 0 80 0 38 66 39-67 76 0-69 119-95 0c0 0-52-88-68-118";
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(path);
        svgPath.setFill(Color.YELLOW);
        svgPath.setStrokeWidth(1);
        svgPath.setStroke(Color.BLACK);
        this.shape = svgPath;
    }

}


