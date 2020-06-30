package br.maua.ui.views.draggables.simple.shapes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class ArrowL extends Group {

    private Shape shape;
    private int v = 140;
    private int h = 140;

    public ArrowL() {
        createShape(h,v);
        this.getChildren().add(this.shape);
    }

    private void createShape(int h, int v){
        String path = "M 92 0 L " + 140 + " 27.5 L 92 55 L 92 42.5 L 30 42.5 L 30 " +v+ " L 15 " + v + " L 0 " + v + " L 0 12.5 L 92 12.5 Z";
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(path);
        svgPath.setFill(Color.LIGHTSKYBLUE);
        svgPath.setStrokeWidth(1);
        svgPath.setStroke(Color.BLACK);
        this.shape = (Shape) svgPath;
    }

    public void setHeight(int v){
        this.v = v;
        createShape(h, v);
        this.getChildren().remove(0);
        this.getChildren().add(0,this.shape);
    }

    public void setWidth(int h){
        this.h = h;
        createShape(h, v);
        this.getChildren().remove(0);
        this.getChildren().add(0,this.shape);
    }
}
