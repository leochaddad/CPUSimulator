package br.maua.ui.controllers;

import br.maua.ui.views.CreatorView;
import br.maua.ui.views.draggables.Draggable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.*;


public class CreatorDragController {

    public CreatorDragController(CreatorView view) {
        this.view = view;
        buildDragHandlers();
    }

    private CreatorView view;

    private EventHandler<DragEvent> elementDragDropped;
    private EventHandler<DragEvent> elementDragOverCreationArea;
    private EventHandler<DragEvent> elementDragInsideCreationArea;
    private EventHandler<DragEvent> elementDragInsideCreationAreaDropped;
    private Event firstDragEvent;
    private Draggable draggedElement;

    //Adds drag detection to elements in elements pane
    public void addElement(Draggable element){
        view.getElementsArea().getChildren().add(element);
        addExternalActionHandlers(element);
    }

    //Handles drag detection between panes
    public void addExternalActionHandlers(Draggable element){
            element.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    view.getCreationArea().setOnDragOver(elementDragOverCreationArea);
                    view.getCreationArea().setOnDragDropped(elementDragDropped);

                    draggedElement = (Draggable) event.getSource();

                    view.setDragOverElement(draggedElement);


                    ClipboardContent content = new ClipboardContent();

                    content.putString(view.getDragOverElement().getDraggableType().toString());
                    view.getDragOverElement().startDragAndDrop(TransferMode.ANY).setContent(content);
                    firstDragEvent = event;

               }
            });
    }

    //Handles drag detection on draggable elements within the creation area pane
    public void addInternalActionHandlers(Draggable element){
        element.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                view.getCreationArea().setOnDragOver(elementDragInsideCreationArea);
                view.getCreationArea().setOnDragDropped(elementDragInsideCreationAreaDropped);

                draggedElement = (Draggable) event.getSource();

                view.setDragOverElement(draggedElement);

                ClipboardContent content = new ClipboardContent();

                content.putString(view.getDragOverElement().getDraggableType().toString());
                view.getDragOverElement().startDragAndDrop(TransferMode.ANY).setContent(content);
                firstDragEvent = event;

            }
        });
        element.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Deselects all elements
                view.getCreationArea().getChildren().forEach(children -> ((Draggable) children).deselect());
                //Selects target
                element.select();
                event.consume();
            }
        });
        view.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Clicked outside, deselects last element selected (only one possible)
                element.deselect();
            }
        });
    }

    public void buildDragHandlers() {

            elementDragOverCreationArea = new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                   event.acceptTransferModes(TransferMode.ANY);
                   event.consume();
                }
            };

            elementDragDropped = new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    event.setDropCompleted(true);
                    view.getCreationArea().removeEventHandler(DragEvent.DRAG_OVER, elementDragOverCreationArea);
                    view.getCreationArea().removeEventHandler(DragEvent.DRAG_DROPPED, elementDragDropped);
                    view.getDragOverElement().setVisible(true);
                    event.consume();
                    int newPositionX = (int) Math.ceil((event.getX()/1)-draggedElement.getCenterX());
                    int newPositionY = (int) Math.ceil((event.getY()/1)-draggedElement.getCenterY());

                    if(!((Draggable) firstDragEvent.getSource()).getParent().equals(view.getCreationArea())){
                        //Creates new element if it comes from the left pane
                        Draggable elementToAdd = draggedElement.createNewDraggable();
                        elementToAdd.setLayoutX(newPositionX);
                        elementToAdd.setLayoutY(newPositionY);
                        view.getCreationArea().getChildren().add(elementToAdd);
                        addInternalActionHandlers(elementToAdd);
                    }
                }
            };
            elementDragInsideCreationArea = new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    event.acceptTransferModes(TransferMode.ANY);
                    view.getDragOverElement().relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                    event.consume();
                }
            };

            elementDragInsideCreationAreaDropped = new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    event.setDropCompleted(true);
                    view.getCreationArea().removeEventHandler(DragEvent.DRAG_OVER, elementDragOverCreationArea);
                    view.getCreationArea().removeEventHandler(DragEvent.DRAG_DROPPED, elementDragDropped);
                    event.consume();
                }
            };


            view.setOnDragDone(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                        view.getCreationArea().removeEventHandler(DragEvent.DRAG_OVER, elementDragOverCreationArea);
                        view.getCreationArea().removeEventHandler(DragEvent.DRAG_DROPPED, elementDragDropped);
                        view.getDragOverElement().setVisible(true);
                        event.consume();
                }});
    }


}










