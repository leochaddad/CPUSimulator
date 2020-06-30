package br.maua.ui.views.draggables.simple;

import br.maua.ui.views.draggables.Draggable;
import br.maua.ui.views.draggables.simple.shapes.ArrowL;
import br.maua.ui.enums.DraggableType;


public class BusDraggable extends Draggable {

    public BusDraggable() {
        super(DraggableType.BUS);
        this.format = new ArrowL();
        this.getChildren().add(format);
    }

}
