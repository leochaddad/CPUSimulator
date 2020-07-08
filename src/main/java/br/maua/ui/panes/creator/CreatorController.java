package br.maua.ui.panes.creator;
import br.maua.ui.elements.components.Component;
import br.maua.ui.elements.components.simple.AluComponent;
import br.maua.ui.elements.components.simple.BusComponent;
import br.maua.ui.elements.components.simple.CounterComponent;
import br.maua.ui.elements.components.simple.RegisterComponent;
import br.maua.ui.enums.ComponentContext;

public class CreatorController {

    public CreatorController(CreatorView creatorView) {
        this.creatorView = creatorView;
        addAvailableComponent(new AluComponent());
        addAvailableComponent(new BusComponent());
        addAvailableComponent(new RegisterComponent());
        addAvailableComponent(new CounterComponent());
    }

    private final CreatorView creatorView;

    public void addAvailableComponent(Component component){
        component.setContext(ComponentContext.ON_DISPLAY);
        creatorView.getAvailableComponentsBox().getChildren().add(component);
    }



}
