package br.maua.ui.elements.components;

import br.maua.ui.elements.Component;
import br.maua.ui.elements.shapes.ArrowL;
import br.maua.ui.enums.DraggableType;


public class BusComponent extends Component {

    public BusComponent() {
        super(DraggableType.BUS);
        this.format = new ArrowL();
        this.getChildren().add(format);
    }

}
