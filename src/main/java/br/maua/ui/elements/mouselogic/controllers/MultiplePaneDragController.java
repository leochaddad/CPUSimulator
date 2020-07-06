package br.maua.ui.elements.mouselogic.controllers;

import br.maua.ui.elements.mouselogic.interfaces.DropAction;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;


public class MultiplePaneDragController {

    public MultiplePaneDragController(Group draggableElement, DropAction actionOnDrop, AnchorPane dropPane) {
        this.draggedElement = draggableElement;
        this.dropPane = dropPane;
        this.actionOnDrop = actionOnDrop;
        buildDragHandlers();
        addExternalActionHandlers(draggableElement);
    }


    private final AnchorPane dropPane;
    private EventHandler<DragEvent> onDropHandler;
    private EventHandler<DragEvent> onDragOverDropPaneHandler;

    private final DropAction actionOnDrop;
    private Event firstDragEvent;
    private Group draggedElement;
    private Group elementToAdd;


    //Handles drag detection between panes
    public void addExternalActionHandlers(Group element) {
        element.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                dropPane.setOnDragOver(onDragOverDropPaneHandler);
                dropPane.setOnDragDropped(onDropHandler);

                draggedElement = (Group) event.getSource();

                ClipboardContent content = new ClipboardContent();

                content.putString(draggedElement.toString());
                draggedElement.startDragAndDrop(TransferMode.ANY).setContent(content);
                firstDragEvent = event;

            }
        });
    }


    public void buildDragHandlers() {

        onDragOverDropPaneHandler = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);
                event.consume();
            }
        };

        onDropHandler = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.setDropCompleted(true);
                dropPane.removeEventHandler(DragEvent.DRAG_OVER, onDragOverDropPaneHandler);
                dropPane.removeEventHandler(DragEvent.DRAG_DROPPED, onDropHandler);
                draggedElement.setVisible(true);
                event.consume();
                int newPositionX = (int) Math.ceil((event.getX() / 1) - draggedElement.getBoundsInLocal().getCenterX());
                int newPositionY = (int) Math.ceil((event.getY() / 1) - draggedElement.getBoundsInLocal().getCenterY());
                if (!((Group) firstDragEvent.getSource()).getParent().equals(dropPane)) {
                    actionOnDrop.executeOn(draggedElement,newPositionX,newPositionY);
                }
            }
        };

        dropPane.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                dropPane.removeEventHandler(DragEvent.DRAG_OVER, onDragOverDropPaneHandler);
                dropPane.removeEventHandler(DragEvent.DRAG_DROPPED, onDropHandler);
                draggedElement.setVisible(true);
                event.consume();
            }
        });

    }
}










