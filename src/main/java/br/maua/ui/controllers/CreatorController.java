package br.maua.ui.controllers;

import br.maua.ui.views.CreatorView;
import br.maua.ui.views.draggables.Draggable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.*;
import javafx.scene.transform.Scale;


public class CreatorController {

    public CreatorController(CreatorView view) {
        this.view = view;
        buildDragHandlers();
        addKeyDetection();
    }

    private CreatorView view;

    private EventHandler elementDragOverElementsArea;
    private EventHandler elementDragDropped;
    private EventHandler elementDragOverCreationArea;
    private Event firstDragEvent;
    private Draggable draggedElement;
    private boolean shiftDown = false;

    //Adds drag detection to elements
    public void addElement(Draggable element){
        view.getElementsArea().getChildren().add(element);
        addDragDetection(element);
    }

    //Handles drag detection on draggable elements
    public void addDragDetection(Draggable element){
            element.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    view.getElementsArea().setOnDragOver(elementDragOverElementsArea);
                    view.getCreationArea().setOnDragOver(elementDragOverCreationArea);
                    view.getCreationArea().setOnDragDropped(elementDragDropped);

                    draggedElement = (Draggable) event.getSource();

                    view.setDragOverElement(draggedElement);

                    ClipboardContent content = new ClipboardContent();
                    content.putString(view.getDragOverElement().getDraggableType().toString());
                    if(!shiftDown) {
                        view.getDragOverElement().startDragAndDrop(TransferMode.ANY).setContent(content);
                    }
                    firstDragEvent = event;

                }
            });
    }

    public void buildDragHandlers() {

            elementDragOverElementsArea = new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    Point2D p = view.getElementsArea().sceneToLocal(event.getSceneX(), event.getSceneY());
                    if (!view.getElementsArea().boundsInLocalProperty().get().contains(p)) {
                        view.getDragOverElement().relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                        return;
                    }
                    event.consume();
                }
            };

            elementDragOverCreationArea = new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                   event.acceptTransferModes(TransferMode.ANY);
                   view.getDragOverElement().relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                   event.consume();
                }
            };

            elementDragDropped = new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    event.setDropCompleted(true);
                    view.getElementsArea().removeEventHandler(DragEvent.DRAG_OVER, elementDragOverElementsArea);
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
                        addDragDetection(elementToAdd);
                    }
                }
            };

            view.setOnDragDone(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                        view.getElementsArea().removeEventHandler(DragEvent.DRAG_OVER, elementDragOverElementsArea);
                        view.getCreationArea().removeEventHandler(DragEvent.DRAG_OVER, elementDragOverCreationArea);
                        view.getCreationArea().removeEventHandler(DragEvent.DRAG_DROPPED, elementDragDropped);
                        view.getDragOverElement().setVisible(true);
                        event.consume();
                        ConnexionPointController controller1 = new ConnexionPointController();
                        controller1.initialize(view);
                }});
    }

    private void addKeyDetection(){
        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                shiftDown = keyEvent.isShiftDown();
            }
        });
        view.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                shiftDown = keyEvent.isShiftDown();
            }
        });
    }

    }









