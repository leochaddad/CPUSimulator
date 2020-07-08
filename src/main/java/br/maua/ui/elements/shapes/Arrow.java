package br.maua.ui.elements.shapes;

import br.maua.ui.interfaces.Selectable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Rotate;

public class Arrow extends Group implements Selectable {

    private final Polyline arrowLine = new Polyline();
    private final Polygon arrowHead = new Polygon();
    private final double ARROWHEAD_SIZE = 22;
    Rotate rotate = new Rotate();

    private final SimpleDoubleProperty x1 = new SimpleDoubleProperty();
    private final SimpleDoubleProperty y1 = new SimpleDoubleProperty();
    private final SimpleDoubleProperty x2 = new SimpleDoubleProperty();
    private final SimpleDoubleProperty y2 = new SimpleDoubleProperty();

    public Arrow(double x1, double y1, double x2, double y2) {
        arrowLine.setStrokeWidth(14);
        arrowHead.getPoints().setAll(x2,x2-20,x2,x2+20,x2+20,x2);
        this.x1.set(x1);
        this.y1.set(y1);
        this.x2.set(x2);
        this.y2.set(y2);

        arrowHead.getTransforms().addAll(rotate);
        arrowLine.setStroke(Color.SKYBLUE);
        arrowHead.setFill(Color.SKYBLUE);

        getChildren().addAll(arrowLine, arrowHead);

        for(SimpleDoubleProperty s: new SimpleDoubleProperty[]{this.x1,this.y1,this.x2,this.y2}){
            s.addListener((l,o,n) -> update());
        }
        update();

    }

    private void update() {
        //Updates arrow size
        arrowLine.getPoints().setAll(x1.get(),y1.get(),x2.get(),y2.get());

        rotate.setPivotX(x2.get()+arrowHead.getLayoutX());
        rotate.setPivotY(y2.get()+arrowHead.getLayoutY());


        //Sets arrowhead position
        arrowHead.getPoints().setAll(x2.get()-ARROWHEAD_SIZE,y2.get()-ARROWHEAD_SIZE,
                x2.get()-ARROWHEAD_SIZE,y2.get()+ARROWHEAD_SIZE,x2.get()+ARROWHEAD_SIZE,y2.get());

        //Rotates arrowhead
        rotate.setAngle(Math.toDegrees(Math.atan2((y2.get()-y1.get()),(x2.get()-x1.get()))));
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


    @Override
    public void select() {

    }

    @Override
    public void deselect() {

    }
}
