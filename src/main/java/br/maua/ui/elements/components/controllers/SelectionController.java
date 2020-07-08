package br.maua.ui.elements.components.controllers;

import br.maua.ui.interfaces.Controller;
import br.maua.ui.interfaces.Selectable;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class SelectionController implements Controller {



    public SelectionController(Selectable selectable) {
        this.selectable = selectable;
        buildEventHandlers();
    }

    private final Selectable selectable;
    EventHandler<MouseEvent> mouseClickedHandler;
    EventHandler<MouseEvent> mouseClickedOnParentHandler;


    private void addEventHandlers(){
        if(((Group)selectable).getParent()!=null) {
            ((Group) selectable).addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedHandler);
            ((Group) selectable).getParent().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedOnParentHandler);

        }
    }
    private void removeEventHandlers(){
        if(((Group)selectable).getParent()!=null) {
            ((Group) selectable).removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedHandler);
            ((Group) selectable).getParent().removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickedOnParentHandler);
        }
    }

    private void buildEventHandlers(){
        mouseClickedHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Deselects everyone
                ((Group)selectable).getParent().getChildrenUnmodifiable().forEach(children->
                        {if(children instanceof Selectable ){
                            ((Selectable) children).styleAsDeselected();
                        } }
                );
                //Selects target
                selectable.styleAsSelected();
                selectable.select();
                event.consume();
            }
        };
        mouseClickedOnParentHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Deselects everyone
                deselectAll();
                event.consume();
            }
        };
    }
    private void deselectAll(){
        if(((Group)selectable).getParent() != null) {
            ((Group)selectable).getParent().getChildrenUnmodifiable().forEach(children ->
                    {
                        if (children instanceof Selectable) {
                            ((Selectable) children).styleAsDeselected();
                            ((Selectable) children).deselect();
                        }
                    }
            );
        }
    }

    @Override
    public void enable() {
        addEventHandlers();
    }

    @Override
    public void disable() {
        deselectAll();
        removeEventHandlers();
    }
}




