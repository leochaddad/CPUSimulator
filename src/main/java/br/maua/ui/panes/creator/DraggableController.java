package br.maua.ui.panes.creator;

import br.maua.ui.elements.Draggable;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class DraggableController {

    Draggable draggable;
    AnchorPane pane;

    private EventHandler<MouseEvent> elementDragHandler;
    private EventHandler<MouseEvent> elementDroppedHandler;

    public DraggableController(Draggable draggable) {
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
                draggable.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
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
