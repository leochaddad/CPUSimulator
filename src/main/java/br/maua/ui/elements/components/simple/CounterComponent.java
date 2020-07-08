package br.maua.ui.elements.components.simple;

import br.maua.ui.enums.ComponentType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CounterComponent extends RegisterComponent {
    public CounterComponent() {

        this.componentType = ComponentType.COUNTER;
        ((Shape)this.format.getChildren().get(0)).setFill(Color.STEELBLUE);
    }

}
