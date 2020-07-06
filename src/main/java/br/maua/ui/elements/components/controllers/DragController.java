package br.maua.ui.elements.components.controllers;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class DragController {

    Group draggable;
    AnchorPane pane;

    private EventHandler<MouseEvent> elementDragHandler;
    private EventHandler<MouseEvent> elementDroppedHandler;

    public DragController(Group draggable) {
        this.draggable = draggable;
        this.pane = (AnchorPane) draggable.getParent();
        buildEventHandlers();
        addActionHandlers();
    }

    public void addActionHandlers() {
        draggable.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                draggable.addEventHandler(MouseEvent.MOUSE_DRAGGED,elementDragHandler);
                draggable.addEventHandler(MouseEvent.MOUSE_RELEASED, elementDroppedHandler);
            }
        });
    }

    private void buildEventHandlers(){
        elementDragHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Point2D localCoords = pane.sceneToLocal(new Point2D(event.getSceneX(), event.getSceneY()));
                draggable.relocate((localCoords.getX()-draggable.getBoundsInLocal().getWidth()/2),
                        (localCoords.getY()-draggable.getBoundsInLocal().getHeight()/2));
            }
        };

        elementDroppedHandler = new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                draggable.removeEventHandler(MouseEvent.MOUSE_DRAGGED,elementDragHandler);
               // draggable.removeEventHandler(MouseEvent.MOUSE_RELEASED, elementDroppedHandler);
                event.consume();
        }
    };
    }
}
