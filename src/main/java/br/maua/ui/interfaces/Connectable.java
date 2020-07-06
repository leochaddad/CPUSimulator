package br.maua.ui.interfaces;

import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPoint;

import java.util.ArrayList;

public interface Connectable {


    ArrayList<ConnexionPoint> connexionPoints = new ArrayList<>();
    void addConnexionPoints();

    ArrayList<ConnexionPoint> getConnexionPoints();



}
