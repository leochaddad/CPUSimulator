package br.maua.ui.panes.creator;
import br.maua.ui.elements.Component;
import br.maua.ui.elements.ComponentFactory;
import br.maua.ui.elements.components.AluComponent;
import br.maua.ui.elements.mouselogic.controllers.MultiplePaneDragController;
import br.maua.ui.enums.ComponentType;
import javafx.scene.Group;

public class CreatorController {

    public CreatorController(CreatorView creatorView) {
        this.creatorView = creatorView;
        addAvailableComponent(new AluComponent());
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
    }

}
