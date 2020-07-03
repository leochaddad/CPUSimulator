package br.maua.ui.panes.creator;

import br.maua.ui.elements.Component;
import br.maua.ui.elements.ComponentFactory;
import br.maua.ui.elements.Draggable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.*;


public class CreatorController {


    public CreatorController(CreatorView view) {
        this.view = view;
        buildDragHandlers();
    }

    private CreatorView view;
    private EventHandler<DragEvent> elementDragDropped;
    private EventHandler<DragEvent> elementDragOverCreationArea;


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
                          //addInternalActionHandlers(elementToAdd);
                        new DraggableController(elementToAdd);
                        //Adds connexion point handlers
                        elementToAdd.getConnexionPoints().forEach(cp -> new ConnexionPointController(cp, view.getCreationArea()));
                    }
                }
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










