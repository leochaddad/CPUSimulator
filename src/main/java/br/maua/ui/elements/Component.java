package br.maua.ui.elements;

import br.maua.ui.elements.internalparts.ConnexionPoint;
import br.maua.ui.enums.ComponentType;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public abstract class Component extends Draggable implements Selectable, Connectable {

    protected ComponentType componentType = null; //CP

    protected SimpleStringProperty displayText; //CP ok

    protected ArrayList<ConnexionPoint> connexionPoints = new ArrayList<>(); //Connectable

    public Component(ComponentType type) {
        this.componentType = type;
    } //CP

    public ComponentType getComponentType() {
        return componentType;
    } //CP

    public ArrayList<ConnexionPoint> getConnexionPoints() {
        return connexionPoints;
    }
    public void addConnexionPoints(){

    }

    @Override
    public void select() {

    }

    @Override
    public void deselect() {

    }


}
