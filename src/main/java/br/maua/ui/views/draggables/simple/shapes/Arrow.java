package br.maua.ui.views.draggables.simple.shapes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Polyline;

public class Arrow extends Group {
    private Polyline mainLine = new Polyline();

    private SimpleDoubleProperty x1 = new SimpleDoubleProperty();
    private SimpleDoubleProperty y1 = new SimpleDoubleProperty();
    private SimpleDoubleProperty x2 = new SimpleDoubleProperty();
    private SimpleDoubleProperty y2 = new SimpleDoubleProperty();

    public Arrow(double x1, double y1, double x2, double y2) {
        mainLine.setStrokeWidth(10);
        this.x1.set(x1);
        this.y1.set(y1);
        this.x2.set(x2);
        this.y2.set(y2);

        getChildren().addAll(mainLine);

        for(SimpleDoubleProperty s: new SimpleDoubleProperty[]{this.x1,this.y1,this.x2,this.y2}){
            s.addListener((l,o,n) -> update());
        }
        update();
    }

    private void update() {
        mainLine.getPoints().setAll(x1.get(),y1.get(),x2.get(),y2.get());

    }

    public double getX1() {
        return x1.get();
    }

    public void setX1(double x1) {
        this.x1.set(x1);
    }

    public double getY1() {
        return y1.get();
    }


    public void setY1(double y1) {
        this.y1.set(y1);
    }

    public double getX2() {
        return x2.get();
    }


    public void setX2(double x2) {
        this.x2.set(x2);
    }

    public double getY2() {
        return y2.get();
    }


    public void setY2(double y2) {
        this.y2.set(y2);
    }

    public SimpleDoubleProperty x1Property() {
        return x1;
    }

    public SimpleDoubleProperty y1Property() {
        update();
        return y1;
    }

    public SimpleDoubleProperty x2Property() {
        update();
        return x2;
    }

    public SimpleDoubleProperty y2Property() {
        return y2;
    }
}
