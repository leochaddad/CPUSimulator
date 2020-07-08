package br.maua.ui.elements.components;

import br.maua.ui.enums.ComponentContext;
import br.maua.ui.interfaces.Controller;
import br.maua.ui.elements.components.controllers.DragController;
import br.maua.ui.elements.components.controllers.MultiPaneDragController;
import br.maua.ui.elements.components.controllers.SelectionController;
import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPoint;
import br.maua.ui.interfaces.Connectable;
import br.maua.ui.interfaces.Selectable;
import br.maua.ui.enums.ComponentType;
import br.maua.ui.panes.creator.CreatorView;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import java.util.ArrayList;


public abstract class Component extends Group implements Selectable, Connectable {

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

    public void duplicateIntoCreationArea(Group component, double x, double y){
        ComponentType type = this.componentType;
        Component componentToAdd = ComponentFactory.newComponentAt(type,x,y);
        CreatorView.getCreationAreaAnchorPane().getChildren().add(componentToAdd);
        componentToAdd.setContext(ComponentContext.ON_EDITION);
    }


    public final Controller dragController = new DragController(this);
    public final Controller selectionController = new SelectionController(this);
    public final Controller multiPaneDragController = new MultiPaneDragController(this, this::duplicateIntoCreationArea, CreatorView.getCreationAreaAnchorPane());


    public void setContext(ComponentContext context){
        switch (context){
            case ON_DISPLAY:
                this.multiPaneDragController.enable();
                this.dragController.disable();
                this.selectionController.disable();
                break;
            case ON_EDITION:
                enableConnexionPoints();
                dragController.enable();
                selectionController.enable();
                break;
        }
    }


    public void addConnexionPoints(){
        //Remove once implemented by everyone
    }

    public void select(){
        //Nothing by default
    }
    public void deselect() {
        //Nothing by default
    }

}
