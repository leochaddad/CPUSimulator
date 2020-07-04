package br.maua.ui.elements.internalparts.connexionpoint;
import br.maua.ui.enums.ConnexionPointType;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class ConnexionPoint extends Group {

    public ConnexionPoint(ConnexionPointType type, double positionX, double positionY) {
        this.type = type;
        this.positionX = positionX;
        this.positionY = positionY;
        this.getChildren().addAll(shape, invisibleShape);
        this.setLayoutX(this.positionX);
        this.setLayoutY(this.positionY);
        setStyles();
    }

    private ConnexionPointType type;
    private boolean occupied = false;
    private Shape shape = new Circle(7);
    private Shape secondaryShape = new Circle(4);
    private Shape invisibleShape = new Circle(16, Color.TRANSPARENT);
    private double positionX;
    private double positionY;
    SimpleDoubleProperty centerInPaneX = new SimpleDoubleProperty();
    SimpleDoubleProperty centerInPaneY = new SimpleDoubleProperty();

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean canBeOutputted(){
        return ((!this.isOccupied())&
                (this.type.equals(ConnexionPointType.OUTPUT)|
                        this.type.equals(ConnexionPointType.INPUT_OUTPUT)));
    }

    public boolean canBeInputted(){
        return ((!this.isOccupied())&
                (this.type.equals(ConnexionPointType.INPUT)|
                        this.type.equals(ConnexionPointType.INPUT_OUTPUT)));
    }

    public ConnexionPointType getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }


    private void setStyles(){
        this.shape.setFill(Color.rgb(255,255,255,0.6));
        this.shape.setStroke(Color.DEEPSKYBLUE);
        this.secondaryShape.setFill(Color.DEEPSKYBLUE);

       switch (this.type){

           case INPUT:
               shape.getStrokeDashArray().addAll(3d,3d);
               break;
           case INPUT_OUTPUT:
               shape.getStrokeDashArray().addAll(3d,3d);
               this.getChildren().add(secondaryShape);
           break;
           case OUTPUT:

               this.getChildren().add(secondaryShape);
       }

    }

    public SimpleDoubleProperty centerInPaneXPropriety(){
        centerInPaneX.bind(this.layoutXProperty().add(this.getParent().layoutXProperty()));
        return (centerInPaneX);
    }
    public SimpleDoubleProperty centerInPaneYPropriety(){
        centerInPaneY.bind(this.layoutYProperty().add(this.getParent().layoutYProperty()));
        return (centerInPaneY);
    }


}
