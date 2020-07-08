package br.maua.ui.interfaces;

import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPoint;
import javafx.scene.Node;

import java.util.ArrayList;

public interface Connectable {


    ArrayList<ConnexionPoint> connexionPoints = new ArrayList<>();

    void addConnexionPoints();

    ArrayList<ConnexionPoint> getConnexionPoints();

    default void disableConnexionPoints(){
        connexionPoints.forEach(cp->cp.setVisible(false));
    }

    default void enableConnexionPoints(){
        connexionPoints.forEach(cp->cp.setVisible(true));
        addConnexionPoints();
    }




}
