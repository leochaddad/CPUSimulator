package br.maua.ui.elements.mouselogic.interfaces;


import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public interface Selectable {

    public Group selectableWho();

    default public void select(){
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.SKYBLUE);
        shadow.setSpread(0.5);
        shadow.setRadius(5);

        selectableWho().setEffect(shadow);
    };

    default public void deselect(){
        selectableWho().setEffect(null);
    };

}
