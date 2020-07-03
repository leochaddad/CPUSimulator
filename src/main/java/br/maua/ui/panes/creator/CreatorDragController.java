package br.maua.ui.panes.creator;

import br.maua.ui.elements.Component;
import br.maua.ui.elements.ComponentFactory;
import br.maua.ui.elements.Draggable;
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
    public void addElement(Draggable element) {
        view.getElementsArea().getChildren().add(element);
        addExternalActionHandlers(element);
    }

    //Handles drag detection between panes
    public void addExternalActionHandlers(Draggable element) {
        element.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                view.getCreationArea().setOnDragOver(elementDragOverCreationArea);
                view.getCreationArea().setOnDragDropped(elementDragDropped);

                draggedElement = (Draggable) event.getSource();

                view.setDraggedElement(draggedElement);

                ClipboardContent content = new ClipboardContent();

                content.putString(view.getDraggedElement().toString());
                view.getDraggedElement().startDragAndDrop(TransferMode.ANY).setContent(content);
                firstDragEvent = event;

            }
        });
    }

    //Handles drag detection on draggable elements within the creation area pane
    public void addInternalActionHandlers(Draggable element) {
        element.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                view.getCreationArea().setOnDragOver(elementDragInsideCreationArea);
                view.getCreationArea().setOnDragDropped(elementDragInsideCreationAreaDropped);

                draggedElement = (Draggable) event.getSource();

                view.setDraggedElement(draggedElement);

                ClipboardContent content = new ClipboardContent();

                content.putString(view.getDraggedElement().toString());
                view.getDraggedElement().startDragAndDrop(TransferMode.ANY).setContent(content);
                firstDragEvent = event;
            }
        });
        element.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                //should select element and deselect other

                event.consume();
            }
        });
        view.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Clicked outside, deselects last element selected (only one possible)
                //element.deselect();
                event.consume();
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
                view.getDraggedElement().setVisible(true);
                event.consume();
                int newPositionX = (int) Math.ceil((event.getX() / 1) - draggedElement.getCenterX());
                int newPositionY = (int) Math.ceil((event.getY() / 1) - draggedElement.getCenterY());

                if (!((Draggable) firstDragEvent.getSource()).getParent().equals(view.getCreationArea())) {
                    //Creates new element if it comes from the left pane
                    if (draggedElement instanceof Component) {
                        Component elementToAdd = new ComponentFactory().createNewDraggable(((Component) draggedElement).getComponentType());
                        elementToAdd.setLayoutX(newPositionX);
                        elementToAdd.setLayoutY(newPositionY);
                        view.getCreationArea().getChildren().add(elementToAdd);
                        //Adds internal handlers
                        addInternalActionHandlers(elementToAdd);
                        //Adds connexion point handlers
                        elementToAdd.getConnexionPoints().forEach(cp -> new ConnexionPointController(cp, view.getCreationArea()));
                    }
                }
            }
        };

        elementDragInsideCreationArea = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);
                view.getDraggedElement().relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
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
                view.getDraggedElement().setVisible(true);
                event.consume();
            }
        });

    }
}










