package br.maua.ui.elements;

import br.maua.ui.elements.components.*;
import br.maua.ui.enums.ComponentType;

public class ComponentFactory {

    public Component createNewDraggable(ComponentType draggableType){ //Factory
        Component returnType = null;
        switch (draggableType){
            case ALU:returnType = new AluComponent();
                break;
            case BUS:returnType = new BusComponent();
                break;
            case MEMORY:returnType = new MemoryComponent();
                break;
            case COUNTER:returnType = new CounterComponent();
                break;
            case REGISTER:returnType = new RegisterComponent();
                break;
            case DATA_SPLITTER:returnType = new DataSplitterComponent();
                break;
            case CONTROL_MULTIPLEXER:returnType = new ControlMultiplexerComponent();
                break;
        }
        return returnType;
    }

}
