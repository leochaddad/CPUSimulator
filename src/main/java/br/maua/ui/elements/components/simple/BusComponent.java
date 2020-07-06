package br.maua.ui.elements.components.simple;

import br.maua.ui.elements.components.Component;
import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPoint;
import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPointController;
import br.maua.ui.interfaces.Resizable;
import br.maua.ui.elements.shapes.ArrowL;
import br.maua.ui.enums.ComponentType;
import br.maua.ui.enums.ConnexionPointType;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;


public class BusComponent extends Component  {

    public BusComponent() {
        super(ComponentType.BUS);
        this.format = new ArrowL();
        this.getChildren().add(format);
        ((Resizable)format).setResizeEnabled(false); //Disabled by default
        ((ArrowL)format).hProperty().addListener(observable -> addConnexionPoints());
        format.layoutYProperty().addListener(observable -> addConnexionPoints());
    }

    ArrayList<ConnexionPoint> horizontalPoints = new ArrayList<>();
    ArrayList<ConnexionPoint> verticalPoints = new ArrayList<>();

    @Override
    public void addConnexionPoints() {
        int expectedNumberOfHPoints =((ArrowL)format).hProperty().intValue()/42;

        for(int i = horizontalPoints.size() +1; i<expectedNumberOfHPoints; i++){
            expectedNumberOfHPoints =((ArrowL)format).hProperty().intValue()/42;
            ConnexionPoint cp = new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,expectedNumberOfHPoints*40, ((ArrowL) format).HORIZONTAL_CENTER );
            System.out.println("point @ "+ expectedNumberOfHPoints*40);
            new ConnexionPointController(cp,(AnchorPane)this.getParent());
            connexionPoints.add(cp);
            horizontalPoints.add(cp);
            getChildren().add(cp);
        }
    }

    @Override
    public void select(){
        ((Resizable)format).setResizeEnabled(true);
    }
    @Override
    public void deselect(){
        ((Resizable)format).setResizeEnabled(false);
    }

}
