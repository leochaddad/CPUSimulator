package br.maua.ui.elements;

import br.maua.ui.elements.internalparts.ConnexionPoint;

import java.util.ArrayList;

public interface Connectable {

    ArrayList<ConnexionPoint> connexionPoints = new ArrayList<>();
    void addConnexionPoints();
    public ArrayList<ConnexionPoint> getConnexionPoints();

}
