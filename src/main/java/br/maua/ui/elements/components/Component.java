package br.maua.ui.elements.components;

import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPoint;
import br.maua.ui.interfaces.Connectable;
import br.maua.ui.interfaces.Selectable;
import br.maua.ui.enums.ComponentType;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;

import java.util.ArrayList;

public abstract class Component extends Group implements Selectable, Connectable {

    @Override
    public Group selectableWho() {
        return this;
    }

    protected Group format;

    protected ComponentType componentType = null;

    protected SimpleStringProperty displayText;

    protected ArrayList<ConnexionPoint> connexionPoints = new ArrayList<>();

    public Component(ComponentType type) {
        this.componentType = type;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public ArrayList<ConnexionPoint> getConnexionPoints() {
        return connexionPoints;
    }

    public void addConnexionPoints(){

    }
    public void select(){
        //Nothing by default
    }
    public void deselect() {
        //Nothing by default
    }

}
