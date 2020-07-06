package br.maua.ui.panes.creator;
import br.maua.ui.elements.components.Component;
import br.maua.ui.elements.components.ComponentFactory;
import br.maua.ui.elements.components.simple.AluComponent;
import br.maua.ui.elements.components.simple.BusComponent;
import br.maua.ui.elements.components.simple.RegisterComponent;
import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPointController;
import br.maua.ui.elements.components.controllers.DragController;
import br.maua.ui.elements.components.controllers.MultiplePaneDragController;
import br.maua.ui.elements.components.controllers.SelectableController;
import br.maua.ui.enums.ComponentType;
import javafx.scene.Group;

public class CreatorController {

    public CreatorController(CreatorView creatorView) {
        this.creatorView = creatorView;
        addAvailableComponent(new AluComponent());
        addAvailableComponent(new BusComponent());
        addAvailableComponent(new RegisterComponent());
    }

    private final CreatorView creatorView;


    public void addAvailableComponent(Component component){
        new MultiplePaneDragController(component, this::addElementToCreationArea,creatorView.getCreationAreaAnchorPane());
        creatorView.getAvailableComponentsBox().getChildren().add(component);
    }

    public void addElementToCreationArea(Group component, double x, double y){
        ComponentType type = ((Component)component).getComponentType();
        Component componentToAdd = new ComponentFactory().newComponentAt(type,x,y);
        creatorView.getCreationAreaAnchorPane().getChildren().add(componentToAdd);
        new DragController(componentToAdd);
        new SelectableController(componentToAdd);
        componentToAdd.addConnexionPoints();
        componentToAdd.getConnexionPoints().forEach(connexionPoint -> new ConnexionPointController(connexionPoint,creatorView.getCreationAreaAnchorPane() ));
    }

}
