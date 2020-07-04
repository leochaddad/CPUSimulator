package br.maua.ui.elements.mouselogic.interfaces;

import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPoint;

import java.util.ArrayList;

public interface Connectable {

    ArrayList<ConnexionPoint> connexionPoints = new ArrayList<>();
    void addConnexionPoints();
    public ArrayList<ConnexionPoint> getConnexionPoints();

}
