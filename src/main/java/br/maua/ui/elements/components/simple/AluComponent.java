package br.maua.ui.elements.components.simple;

import br.maua.ui.elements.components.Component;
import br.maua.ui.enums.ConnexionPointType;
import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPoint;
import br.maua.ui.enums.ComponentType;
import br.maua.ui.elements.shapes.Trapezoid;

public class AluComponent extends Component {



    public AluComponent() {
        super(ComponentType.ALU);
        this.format = new Trapezoid();
        this.getChildren().add(format);
    }

    public void addConnexionPoints(){
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT,38,0));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.INPUT,200,0));
        this.connexionPoints.add(new ConnexionPoint(ConnexionPointType.OUTPUT,119,118));
        this.getChildren().addAll(connexionPoints);
    }
}
