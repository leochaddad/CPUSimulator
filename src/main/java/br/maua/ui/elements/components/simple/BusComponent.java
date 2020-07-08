package br.maua.ui.elements.components.simple;

import br.maua.ui.elements.components.Component;
import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPoint;
import br.maua.ui.interfaces.Resizable;
import br.maua.ui.elements.shapes.ArrowBus;
import br.maua.ui.enums.ComponentType;
import br.maua.ui.enums.ConnexionPointType;

import java.util.ArrayList;


public class BusComponent extends Component  {

    public BusComponent() {
        super(ComponentType.BUS);
        this.format = new ArrowBus();
        this.getChildren().add(format);
        ((Resizable)format).setResizeEnabled(false); //Disabled by default
        ((ArrowBus)format).hProperty().addListener(observable -> addConnexionPoints());
        ((ArrowBus)format).vProperty().addListener(observable -> addConnexionPoints());
        setupConnexionPoints();
    }

    ArrayList<ConnexionPoint> setOfPoints = new ArrayList<>();
    ArrayList<ConnexionPoint> pointsToAdd = new ArrayList<>();

    private void setupConnexionPoints(){
        for (int i = 1; i < 100 ; i++) {
            ConnexionPoint newHPoint = new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,42*i,((ArrowBus) format).HORIZONTAL_CENTER);
            setOfPoints.add(newHPoint);
        }
    }

    @Override
    public void addConnexionPoints() {
        int expectedNumberOfHPoints =((ArrowBus)format).hProperty().intValue()/42;

        if(connexionPoints.size() < expectedNumberOfHPoints){
        setOfPoints.subList(connexionPoints.size(), expectedNumberOfHPoints).forEach(connexionPoint -> {
            connexionPoints.add(connexionPoint);
            getChildren().add(connexionPoint);
        });
        }
        else {
              connexionPoints.subList(expectedNumberOfHPoints, connexionPoints.size()).forEach(connexionPoint -> {
                 if(connexionPoint.isOccupied()){
                        ((ArrowBus) format).setMINWIDTH(connexionPoint.getPositionX());
                    }
                 getChildren().remove(connexionPoint);
                 connexionPoints.remove(connexionPoint);
            }
            );
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
