package br.maua.ui.views;
import br.maua.ui.enums.ConnexionPointType;
import javafx.scene.Group;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class ConnexionPoint extends Group {

    public ConnexionPoint(ConnexionPointType type, double positionX, double positionY) {
        this.type = type;
        this.positionX = positionX;
        this.positionY = positionY;
        this.getChildren().add(shape);
        this.setLayoutX(this.positionX);
        this.setLayoutY(this.positionY);
        colorCircle();
    }

    private ConnexionPointType type;
    private boolean occupied = false;
    private Shape shape = new Circle(10);
    private double positionX;
    private double positionY;


    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
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

    private void colorCircle(){
       switch (this.type){

           case INPUT:
               this.shape.setFill(Color.DARKBLUE);
               break;
           case INPUT_OUTPUT:
               this.shape.setFill(Color.DARKSLATEBLUE);
           break;
           case OUTPUT:
               this.shape.setFill(Color.DARKMAGENTA);
       }

    }
}
