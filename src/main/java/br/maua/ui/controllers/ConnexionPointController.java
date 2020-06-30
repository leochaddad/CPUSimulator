package br.maua.ui.controllers;

import br.maua.ui.views.ConnexionPoint;
import br.maua.ui.views.CreatorView;
import br.maua.ui.views.draggables.Draggable;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

public class ConnexionPointController {

    private ConnexionPoint point;
    private AnchorPane creationArea;
    private EventHandler pointDragDetected;
    private EventHandler pointDragDropped;
    private EventHandler pointDragOverPoint;
    private EventHandler pointDroppedOverPoint;

    public void initialize(CreatorView view) {
        buildDragHandlers();
        creationArea = view.getCreationArea();
        point = (ConnexionPoint) ((Draggable)creationArea.getChildren().get(0)).getChildren().get(0);
        point.setOnDragDetected(pointDragDetected);
    }


    public void buildDragHandlers() {

        pointDragDetected = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                point.getParent().setOnDragOver(null);
                point.getParent().setOnDragDropped(null);
                point.getParent().setOnDragOver(pointDragOverPoint);
                point.getParent().setOnDragDropped(pointDragDropped);
                System.out.println("heyooo");
                event.consume();
            }
        };

        pointDragDropped = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                point.getParent().setOnDragOver(null);
                point.getParent().setOnDragDropped(null);
                event.setDropCompleted(true);
            }
        };

        pointDragOverPoint = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);
                event.consume();

            }
        };

        pointDroppedOverPoint = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                point.getParent().setOnDragOver(null);
                point.getParent().setOnDragDropped(null);

                event.setDropCompleted(true);
                event.consume();
            }
        };
    }

}
