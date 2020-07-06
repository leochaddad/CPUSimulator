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
        ((ArrowL)format).vProperty().addListener(observable -> addConnexionPoints());
        setupConnexionPoints();
    }

    ArrayList<ConnexionPoint> horizontalPoints = new ArrayList<>();
    ArrayList<ConnexionPoint> verticalPoints = new ArrayList<>();


    private void setupConnexionPoints(){
        for (int i = 1; i < 100 ; i++) {
            ConnexionPoint newHPoint = new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,42*i,((ArrowL) format).HORIZONTAL_CENTER);
            ConnexionPoint newVPoint = new ConnexionPoint(ConnexionPointType.INPUT_OUTPUT,((ArrowL) format).VERTICAL_CENTER,42*i);
            horizontalPoints.add(newHPoint);
            verticalPoints.add(newVPoint);
            newHPoint.setVisible(false);
            newVPoint.setVisible(false);
        }
        connexionPoints.addAll(horizontalPoints);
        connexionPoints.addAll(verticalPoints);
        getChildren().addAll(horizontalPoints);
        getChildren().addAll(verticalPoints);
    }

    @Override
    public void addConnexionPoints() {

        int expectedNumberOfHPoints =((ArrowL)format).hProperty().intValue()/42;
        int expectedNumberOfVPoints =((ArrowL)format).vProperty().intValue()/42;


        horizontalPoints.subList(0, expectedNumberOfHPoints).forEach(connexionPoint -> connexionPoint.setVisible(true));
        horizontalPoints.subList(expectedNumberOfHPoints,horizontalPoints.size()).forEach(connexionPoint -> {
            connexionPoint.setVisible(false);
             if(connexionPoint.isOccupied()){
                    ((ArrowL) format).setMINWIDTH(connexionPoint.getPositionX());
                }
        }
        );

        verticalPoints.subList(0, expectedNumberOfVPoints).forEach(connexionPoint -> connexionPoint.setVisible(true));
        verticalPoints.subList(expectedNumberOfVPoints,verticalPoints.size()).forEach(connexionPoint -> {
                    connexionPoint.setVisible(false);
                    if(connexionPoint.isOccupied()){
                        ((ArrowL) format).setMINHEIGHT(connexionPoint.getPositionY());
                    }
                }
        );

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
