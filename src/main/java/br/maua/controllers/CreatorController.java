package br.maua.controllers;

import br.maua.views.CreatorView;
import br.maua.views.uiobjects.Draggable;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;


public class CreatorController {

    public CreatorController(CreatorView view) {
        this.view = view;
    }

    private CreatorView view;

    private EventHandler elementDragOverElementsArea;
    private EventHandler elementDragDropped;
    private EventHandler elementDragOverCreationArea;


    public void addElement(Draggable element){
        view.getElementsArea().getChildren().add(element);
        addDragDetection(element);
    }

    public void addDragDetection(Draggable element){
        element.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                view.getElementsArea().setOnDragOver(elementDragOverElementsArea);
                view.getCreationArea().setOnDragOver(elementDragOverCreationArea);
                view.getCreationArea().setOnDragDropped(elementDragDropped);

                Draggable element = (Draggable) event.getSource();

                view.setDragOverElement(element);
                view.getDragOverElement().relocateToPoint(new Point2D (event.getSceneX(), event.getSceneY()));

                ClipboardContent content = new ClipboardContent();
                content.putString(view.getDragOverElement().toString());


                view.getDragOverElement().startDragAndDrop(TransferMode.ANY).setContent(content);

            }
        });

    }

public void buildDragHandlers() {

        elementDragOverElementsArea = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Point2D p = view.getElementsArea().sceneToLocal(event.getSceneX(), event.getSceneY());
                if (!view.getElementsArea().boundsInLocalProperty().get().contains(p)) {
                   // view.getDragOverElement().relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                    return;
                }
                //event.consume();
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
                int posx = (int) Math.ceil((event.getX()/1)-50);
                int posy = (int) Math.ceil((event.getY()/1)-50);
                System.out.println(event.getSource());
                Node nodeToAdd = (Node) view.getDragOverElement();
                nodeToAdd.setLayoutX(posx);
                nodeToAdd.setLayoutY(posy);
                view.getCreationArea().getChildren().add(nodeToAdd);
                event.consume();
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
            }});}


    }



