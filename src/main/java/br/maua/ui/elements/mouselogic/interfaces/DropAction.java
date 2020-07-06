package br.maua.ui.elements.mouselogic.interfaces;

import br.maua.ui.elements.Component;
import javafx.scene.Group;


public interface DropAction {
    void executeOn(Group element, double x, double y);
}
