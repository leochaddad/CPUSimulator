package br.maua.ui.elements.components;

import br.maua.ui.elements.Component;
import br.maua.ui.enums.ConnexionPointType;
import br.maua.ui.elements.internalparts.ConnexionPoint;
import br.maua.ui.enums.DraggableType;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RegisterComponent extends Component {

    public RegisterComponent() {
        super(DraggableType.REGISTER);
        this.format = new Group();
        Rectangle shape = new Rectangle(140,80);
        shape.setFill(Color.LIGHTCORAL);
        shape.setStrokeWidth(1);
        shape.setStroke(Color.BLACK);
        this.format.getChildren().add(shape);
        this.getChildren().add(0,this.format);
        addConnexionPoints();
    }

    public void addConnexionPoints(){
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,40,0));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,100,0));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,40,80));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,100,80));
        this.getChildren().addAll(connexionPoints);
    }


}
