package br.maua.ui.elements.components.controllers;

import br.maua.ui.interfaces.Controller;
import br.maua.ui.interfaces.DropAction;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;


public class MultiPaneDragController implements Controller {

    public MultiPaneDragController(Group draggableElement, DropAction actionOnDrop, AnchorPane dropPane) {
        this.draggableElement = draggableElement;
        this.dropPane = dropPane;
        this.actionOnDrop = actionOnDrop;
        buildEventHandlers();
    }


    private final AnchorPane dropPane;
    private EventHandler<DragEvent> dragDroppedHandler;
    private EventHandler<DragEvent> dragOverDropPaneHandler;
    private EventHandler<MouseEvent> dragDetectedHandler;


    private final DropAction actionOnDrop;
    private Event firstDragEvent;
    private Group draggableElement;
    private Group elementToAdd;


    //Handles drag detection between panes
    private void addEventHandlers() {
        draggableElement.addEventHandler(MouseEvent.DRAG_DETECTED,dragDetectedHandler);
    }

    private void removeEventHandlers(){
        draggableElement.removeEventHandler(MouseEvent.DRAG_DETECTED,dragDetectedHandler);
        dropPane.removeEventHandler(DragEvent.DRAG_OVER, dragOverDropPaneHandler);
        dropPane.removeEventHandler(DragEvent.DRAG_DROPPED, dragDroppedHandler);

    }


    private void buildEventHandlers() {

        dragOverDropPaneHandler = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);
                event.consume();
            }
        };

        dragDroppedHandler = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.setDropCompleted(true);
                dropPane.removeEventHandler(DragEvent.DRAG_OVER, dragOverDropPaneHandler);
                dropPane.removeEventHandler(DragEvent.DRAG_DROPPED, dragDroppedHandler);
                draggableElement.setVisible(true);
                event.consume();
                int newPositionX = (int) Math.ceil((event.getX() / 1) - draggableElement.getBoundsInLocal().getCenterX());
                int newPositionY = (int) Math.ceil((event.getY() / 1) - draggableElement.getBoundsInLocal().getCenterY());
                if (!((Group) firstDragEvent.getSource()).getParent().equals(dropPane)) {
                    actionOnDrop.executeOn(draggableElement,newPositionX,newPositionY);
                }
            }
        };

        dragDetectedHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                dropPane.setOnDragOver(dragOverDropPaneHandler);
                dropPane.setOnDragDropped(dragDroppedHandler);

                draggableElement = (Group) event.getSource();

                ClipboardContent content = new ClipboardContent();

                content.putString(draggableElement.toString());
                draggableElement.startDragAndDrop(TransferMode.ANY).setContent(content);
                firstDragEvent = event;

            }
        };

        dropPane.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                dropPane.removeEventHandler(DragEvent.DRAG_OVER, dragOverDropPaneHandler);
                dropPane.removeEventHandler(DragEvent.DRAG_DROPPED, dragDroppedHandler);
                draggableElement.setVisible(true);
                event.consume();
            }
        });


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










