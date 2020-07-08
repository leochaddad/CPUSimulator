package br.maua.ui.elements.components.controllers;

import br.maua.ui.interfaces.Controller;
import br.maua.ui.panes.creator.CreatorView;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class DragController implements Controller {

    Group draggable;
    AnchorPane pane;

    private EventHandler<MouseEvent> dragDetectedHandler;
    private EventHandler<MouseEvent> elementDragHandler;
    private EventHandler<MouseEvent> elementDroppedHandler;

    public DragController(Group draggable) {
        this.draggable = draggable;
        this.pane = CreatorView.getCreationAreaAnchorPane();
        buildEventHandlers();
    }

    public void addEventHandlers() {
        draggable.addEventHandler(MouseEvent.DRAG_DETECTED,dragDetectedHandler);
    }

    public void removeEventHandlers() {
        draggable.addEventHandler(MouseEvent.MOUSE_DRAGGED,elementDragHandler);
        draggable.addEventHandler(MouseEvent.MOUSE_RELEASED, elementDroppedHandler);
        draggable.addEventHandler(MouseEvent.DRAG_DETECTED,dragDetectedHandler);
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

        dragDetectedHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                draggable.addEventHandler(MouseEvent.MOUSE_DRAGGED,elementDragHandler);
                draggable.addEventHandler(MouseEvent.MOUSE_RELEASED, elementDroppedHandler);
            }
        };

    }

    @Override
    public void enable() {
        addEventHandlers();
    }

    @Override
    public void disable() {
        removeEventHandlers();
    }

}
