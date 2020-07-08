package br.maua.ui.elements.internalparts.draghandle;


import br.maua.ui.interfaces.Resizable;

import br.maua.ui.enums.DragHandleType;
import javafx.event.EventHandler;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class DragHandleController {

    DragHandle handle;
    EventHandler<MouseEvent> handleDrag;
    EventHandler<MouseEvent> handleReleased;


    public DragHandleController(DragHandle handle) {
        this.handle = handle;
        buildEventHandlers();
        addDragHandleHandlers();
    }

    public void addDragHandleHandlers() {
        handle.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(handle.getParent() instanceof Resizable){
                    handle.addEventHandler(MouseEvent.MOUSE_DRAGGED,handleDrag);
                    handle.addEventHandler(MouseEvent.MOUSE_RELEASED,handleReleased);
                }
                event.consume();
        }
        });

        handle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(handle.type==DragHandleType.HORIZONTAL){
                    handle.setCursor(Cursor.H_RESIZE);
                }
                else if(handle.type == DragHandleType.VERTICAL){
                    handle.setCursor(Cursor.V_RESIZE);

                }
                event.consume();
            }
        });

    }

    private void buildEventHandlers() {
        handleDrag = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                event.consume();
                if (handle.type.equals(DragHandleType.HORIZONTAL)){
                    ((Resizable)handle.getParent()).extendX(event.getX());
                }
                else if (handle.type.equals(DragHandleType.VERTICAL)){
                    //Resize vertical if implemented...
                }
            }
        };

        handleReleased = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                event.consume();
                handle.removeEventHandler(MouseEvent.MOUSE_DRAGGED, handleDrag);
                handle.removeEventHandler(MouseEvent.MOUSE_RELEASED, handleReleased);
            }
        };
    }
}
