package br.maua.ui.elements.shapes;

import br.maua.ui.elements.internalparts.draghandle.DragHandle;
import br.maua.ui.elements.internalparts.draghandle.DragHandleController;
import br.maua.ui.interfaces.Resizable;
import br.maua.ui.enums.DragHandleType;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

import java.util.ArrayList;

public class ArrowL extends Group implements Resizable {


    private final SVGPath shape = new SVGPath();

    public final double BASEHEIGHT = 130;
    public final double BASEWIDTH = 90;
    public final double ARROWHEADWIDTH = 30;
    public final double HORIZONTAL_CENTER = 27.5;
    public final double VERTICAL_CENTER = 15;
    public final double MARGIN = 10;

    private final SimpleDoubleProperty h = new SimpleDoubleProperty();

    public SimpleDoubleProperty hProperty() {
        return h;
    }

    public SimpleDoubleProperty vProperty() {
        return v;
    }

    private final SimpleDoubleProperty v = new SimpleDoubleProperty();

    public ArrowL() {
        h.set(BASEWIDTH);
        v.set(BASEHEIGHT);
        setShape();
        h.addListener((observable -> setShape()));
        v.addListener((observable -> setShape()));
        this.getChildren().add(0,this.shape);
        addHandles();
    }

    private void setShape(){
        String path = "M " + h.get() + " 0 L "+ (30.0+h.get()) + " 27.5 L "+h.get()+" 55 L "+h.get()+" 42.5 L 30 42.5 L 30 " +v.get()+ " L 15 " + v.get() + " L 0 " + v.get() + " L 0 12.5 L "+h.get()+" 12.5 Z";
        shape.setContent(path);
        shape.setFill(Color.LIGHTSKYBLUE);
        shape.setStrokeWidth(1);
        shape.setStroke(Color.BLACK);
    }


    public void setHeight(Double v){
        this.v.set(v);
    }

    public void setWidth(Double h){
        this.h.set(h);
    }

    private final ArrayList<DragHandle> dragHandles = new ArrayList<>();

    //Horizontal Handle
    DragHandle handleH;
    //Vertical Handle
    DragHandle handleV;

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

        handleV = new DragHandle(VERTICAL_CENTER, getBoundsInLocal().getHeight() + 10, DragHandleType.VERTICAL);
        new DragHandleController(handleV);
        getDragHandles().add(handleV);
        handleV.layoutYProperty().bind(v.add(MARGIN));

        getChildren().addAll(dragHandles);

    }

    @Override
    public void extendX(double x) {
        if ((x+h.get())>BASEWIDTH){
            setWidth(x+h.get());
        }
    }

    @Override
    public void extendY(double y) {
        if ((y+v.get())>BASEHEIGHT){
            setHeight(y+v.get());
        }
    }




}
