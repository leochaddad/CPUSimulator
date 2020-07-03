package br.maua.ui.panes.creator;

import br.maua.ui.elements.small.ConnexionPoint;
import br.maua.ui.elements.draggables.Draggable;
import br.maua.ui.elements.shapes.Arrow;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
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
    private EventHandler<MouseEvent> arrowDragHandler;
    private EventHandler<MouseEvent> arrowReleasedHandler;

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

                    view.setDraggedElement(draggedElement);

                    ClipboardContent content = new ClipboardContent();

                    content.putString(view.getDraggedElement().getDraggableType().toString());
                    view.getDraggedElement().startDragAndDrop(TransferMode.ANY).setContent(content);
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

                view.setDraggedElement(draggedElement);

                ClipboardContent content = new ClipboardContent();

                content.putString(view.getDraggedElement().getDraggableType().toString());
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

    //Takes care of creating arrows between connexion points
    Arrow arrow;
    ConnexionPoint sourcePoint;
    ConnexionPoint endPoint;

    public void addConnexionPointHandlers(ConnexionPoint point){
        //Detects drag to avoid element being dragged by draggable
        point.setOnDragDetected(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                sourcePoint = ((ConnexionPoint) event.getSource());
                if(sourcePoint.canBeOutputted()){
                    //Occupies the point
                    sourcePoint.setOccupied(true);
                    //Creates new arrow (size configured to not flash)
                    arrow = new Arrow(sourcePoint.getPositionX(),sourcePoint.getPositionY(),sourcePoint.getPositionX(),sourcePoint.getPositionY());
                    //Mouse transparency used so the mouse release event don't pick on the arrow
                    arrow.setMouseTransparent(true);
                    //Binds the start of the arrow with the output node
                    arrow.x1Property().bind(sourcePoint.centerInPaneXPropriety());
                    arrow.y1Property().bind(sourcePoint.centerInPaneYPropriety());
                    //Handles the drag event
                    view.getCreationArea().addEventHandler(MouseEvent.MOUSE_DRAGGED,arrowDragHandler);
                    //Adds the arrow to the scene
                    view.getCreationArea().getChildren().add(arrow);
                    //Handles release event only if start point can be outputted
                    point.addEventHandler(MouseEvent.MOUSE_RELEASED,arrowReleasedHandler);
                }
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
                    int newPositionX = (int) Math.ceil((event.getX()/1)-draggedElement.getCenterX());
                    int newPositionY = (int) Math.ceil((event.getY()/1)-draggedElement.getCenterY());

                    if(!((Draggable) firstDragEvent.getSource()).getParent().equals(view.getCreationArea())){
                        //Creates new element if it comes from the left pane
                        Draggable elementToAdd = draggedElement.createNewDraggable();
                        elementToAdd.setLayoutX(newPositionX);
                        elementToAdd.setLayoutY(newPositionY);
                        view.getCreationArea().getChildren().add(elementToAdd);
                        //Adds internal handlers
                        addInternalActionHandlers(elementToAdd);
                        //Adds connexion point handlers
                        elementToAdd.getConnexionPoints().forEach(cp -> addConnexionPointHandlers(cp) );
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

            arrowDragHandler = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    arrow.setX2(event.getX());
                    arrow.setY2(event.getY());
                }
            };

            arrowReleasedHandler = new EventHandler<MouseEvent>(){
                public void handle(MouseEvent event){
                    view.getCreationArea().removeEventHandler(MouseEvent.MOUSE_DRAGGED,arrowDragHandler);
                    //Has to verify if node is a ConnexionPoint before moving forward
                    Node endTarget = event.getPickResult().getIntersectedNode().getParent();
                    if(endTarget instanceof ConnexionPoint){
                        ConnexionPoint endPoint = (ConnexionPoint) endTarget;
                        //Blocks connecting within same draggable
                        boolean parentsAreTheSame = endPoint.getParent().equals(sourcePoint.getParent());
                        if(!parentsAreTheSame & endPoint.canBeInputted()){
                            //Binds arrow end to input
                            arrow.x2Property().bind(endPoint.centerInPaneXPropriety());
                            arrow.y2Property().bind(endPoint.centerInPaneYPropriety());
                            endPoint.setOccupied(true);
                        }
                        //Occupied, same parents or not input type
                        else {
                            sourcePoint.setOccupied(false);
                            view.getCreationArea().getChildren().remove(arrow);
                        }
                    }
                    //Didn't land on a connexion point
                    else {
                        sourcePoint.setOccupied(false);
                        view.getCreationArea().getChildren().remove(arrow);
                    }
                    //Removes handler so occupied point can't fall in one of the else conditionals above
                    sourcePoint.removeEventHandler(MouseEvent.MOUSE_RELEASED,arrowReleasedHandler);
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
                }});
    }


}










