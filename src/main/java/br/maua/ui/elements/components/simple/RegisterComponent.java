package br.maua.ui.elements.components.simple;

import br.maua.ui.elements.components.Component;
import br.maua.ui.enums.ConnexionPointType;
import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPoint;
import br.maua.ui.enums.ComponentType;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RegisterComponent extends Component {

    public RegisterComponent() {
        super(ComponentType.REGISTER);
        this.format = new Group();
        Rectangle shape = new Rectangle(140,80);
        shape.setFill(Color.LIGHTCORAL);
        shape.setStrokeWidth(1);
        shape.setStroke(Color.BLACK);
        this.format.getChildren().add(shape);
        this.getChildren().add(0,this.format);
    }

    public void addConnexionPoints(){
        //Top
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,30,0));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,70,0));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,110,0));
        //Bottom
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,30,80));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,70,80));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,110,80));
        //Left
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,0,21));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,0,59));
        //Right
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,140,21));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,140,59));
        this.getChildren().addAll(connexionPoints);
    }


}
