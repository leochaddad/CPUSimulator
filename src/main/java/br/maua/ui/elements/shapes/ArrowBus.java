package br.maua.ui.elements.shapes;

import br.maua.ui.elements.internalparts.draghandle.DragHandle;
import br.maua.ui.elements.internalparts.draghandle.DragHandleController;
import br.maua.ui.interfaces.Resizable;
import br.maua.ui.enums.DragHandleType;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class ArrowBus extends Group implements Resizable {


    private  Shape shape;

    public final double BASEWIDTH = 90;
    public final double BASEHEIGHT = 55;
    public final double ARROWHEADWIDTH = 50;
    private double MINWIDTH = BASEWIDTH;
    public final double ARROWHEADEXTENSION = 20;
    public final double HORIZONTAL_CENTER = BASEHEIGHT/2;
    public final double MARGIN = 10;


    private final SimpleDoubleProperty h = new SimpleDoubleProperty();

    public SimpleDoubleProperty hProperty() {
        return h;
    }

    public SimpleDoubleProperty vProperty() {
        return v;
    }

    private final SimpleDoubleProperty v = new SimpleDoubleProperty();

    public ArrowBus() {
        h.set(BASEWIDTH);
        v.set(BASEHEIGHT);
        setShape();
        this.getChildren().add(0,shape);
        h.addListener((observable -> setShape()));
        v.addListener((observable -> setShape()));
        addHandles();
    }

    private void setShape(){
        Rectangle rectangleH = new Rectangle(h.get(), BASEHEIGHT);
        Polygon triangleRight = new Polygon(rectangleH.getWidth(), rectangleH.getHeight()+ARROWHEADEXTENSION,rectangleH.getWidth(),-ARROWHEADEXTENSION,rectangleH.getWidth()+ARROWHEADWIDTH, rectangleH.getHeight()/2);
        shape = Shape.union(rectangleH,triangleRight);
        shape.setFill(Color.LAVENDER);
        shape.setStrokeWidth(1);
        shape.setStroke(Color.BLACK);
        if (!this.getChildren().isEmpty()){
            this.getChildren().set(0,shape);
        }
    }

    public void setWidth(Double h){
        this.h.set(h);
    }

    private final ArrayList<DragHandle> dragHandles = new ArrayList<>();

    //Horizontal Handle
    DragHandle handleH;

    @Override
    public ArrayList<DragHandle> getDragHandles() {
        return this.dragHandles;
    }

    @Override
    public void addHandles() {
        handleH = new DragHandle(getBoundsInLocal().getWidth() +  10, HORIZONTAL_CENTER ,DragHandleType.HORIZONTAL);
        new DragHandleController(handleH);
        getDragHandles().add(handleH);
        handleH.layoutXProperty().bind(h.add(MARGIN+ARROWHEADWIDTH));
        getChildren().addAll(dragHandles);
    }


    public void setMINWIDTH(double minWidth) {
        this.MINWIDTH = minWidth;
    }

    @Override
    public void extendX(double x) {
        if ((x+h.get())>MINWIDTH){
            setWidth(x+h.get());
        }
    }
}
