package br.maua.ui.elements.components.controllers;

import br.maua.ui.interfaces.Selectable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SelectableController {

    public SelectableController(Selectable selectable) {
        this.selectable = selectable;
        addEventHandlers();
    }

    private final Selectable selectable;

    private void addEventHandlers(){
        selectable.selectableWho().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Deselects everyone
                selectable.selectableWho().getParent().getChildrenUnmodifiable().forEach(children->
                        {if(children instanceof Selectable ){
                            ((Selectable) children).styleAsDeselected();
                        } }
                );
                //Selects target
                selectable.styleAsSelected();
                selectable.select();
                event.consume();
            }
        });

        selectable.selectableWho().getParent().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Deselects everyone
                selectable.selectableWho().getParent().getChildrenUnmodifiable().forEach(children->
                        {if(children instanceof Selectable ){
                            ((Selectable) children).styleAsDeselected();
                            ((Selectable) children).deselect();
                        } }
                );
                event.consume();
            }
        });
    }
    }




