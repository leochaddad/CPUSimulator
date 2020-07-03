package br.maua.ui.elements.components;

import br.maua.ui.elements.Component;
import br.maua.ui.elements.shapes.ArrowL;
import br.maua.ui.enums.ComponentType;


public class BusComponent extends Component {

    public BusComponent() {
        super(ComponentType.BUS);
        this.format = new ArrowL();
        this.getChildren().add(format);
    }

}
