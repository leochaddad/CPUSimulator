package br.maua.ui.panes.creator;

import br.maua.ui.elements.internalparts.ConnexionPoint;
import br.maua.ui.elements.shapes.Arrow;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ConnexionPointController {

    public ConnexionPointController(ConnexionPoint connexionPoint, AnchorPane pane) {
        this.startPoint = connexionPoint;
        this.pane = pane;
        buildEventHandlers();
        addConnexionPointHandlers();
    }

    private AnchorPane pane;
    private ConnexionPoint startPoint;
    private Arrow arrow;

    private EventHandler<MouseEvent> arrowDragHandler;
    private EventHandler<MouseEvent> arrowReleasedHandler;


    public void addConnexionPointHandlers(){
        //Detects drag to avoid element being dragged by draggable
        startPoint.setOnDragDetected(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                if(startPoint.canBeOutputted()){
                    //Occupies the point
                    startPoint.setOccupied(true);
                    //Creates new arrow (size configured to not flash)
                    arrow = new Arrow(startPoint.getPositionX(), startPoint.getPositionY(), startPoint.getPositionX(), startPoint.getPositionY());
                    //Mouse transparency used so the mouse release event don't pick on the arrow
                    arrow.setMouseTransparent(true);
                    //Binds the start of the arrow with the output node
                    arrow.x1Property().bind(startPoint.centerInPaneXPropriety());
                    arrow.y1Property().bind(startPoint.centerInPaneYPropriety());
                    //Handles the drag event
                    pane.addEventHandler(MouseEvent.MOUSE_DRAGGED,arrowDragHandler);
                    //Adds the arrow to the pane
                    pane.getChildren().add(arrow);
                    //Handles release event only if start point can be outputted
                    startPoint.addEventHandler(MouseEvent.MOUSE_RELEASED,arrowReleasedHandler);
                }
                event.consume();
            }
        });
    }


    private void buildEventHandlers(){
        arrowDragHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                arrow.setX2(event.getX());
                arrow.setY2(event.getY());
                event.consume();
            }
        };

        arrowReleasedHandler = new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                pane.removeEventHandler(MouseEvent.MOUSE_DRAGGED,arrowDragHandler);
                //Has to verify if node is a ConnexionPoint before moving forward
                Node endTarget = event.getPickResult().getIntersectedNode().getParent();
                if(endTarget instanceof ConnexionPoint){
                    ConnexionPoint endPoint = (ConnexionPoint) endTarget;
                    //Blocks connecting within same draggable
                    boolean parentsAreTheSame = endPoint.getParent().equals(startPoint.getParent());
                    if(!parentsAreTheSame & endPoint.canBeInputted()){
                        //Binds arrow end to input
                        arrow.x2Property().bind(endPoint.centerInPaneXPropriety());
                        arrow.y2Property().bind(endPoint.centerInPaneYPropriety());
                        endPoint.setOccupied(true);
                    }
                    //Occupied, same parents or not input type
                    else {
                        startPoint.setOccupied(false);
                        pane.getChildren().remove(arrow);
                    }
                }
                //Didn't land on a connexion point
                else {
                    startPoint.setOccupied(false);
                    pane.getChildren().remove(arrow);
                }
                //Removes handler so occupied point can't fall in one of the else conditionals above
                startPoint.removeEventHandler(MouseEvent.MOUSE_RELEASED,arrowReleasedHandler);
                event.consume();
            }
        };
    }
}
