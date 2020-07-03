package br.maua.ui.elements.draggables;

import br.maua.ui.elements.shapes.ArrowL;
import br.maua.ui.enums.DraggableType;


public class BusDraggable extends Draggable {

    public BusDraggable() {
        super(DraggableType.BUS);
        this.format = new ArrowL();
        this.getChildren().add(format);
    }

}
