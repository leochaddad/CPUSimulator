package br.maua.ui.interfaces;


import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public interface Selectable {

    default boolean selectableEnabled(){return false;}

    Group selectableWho();

    default void styleAsSelected(){
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.SKYBLUE);
        shadow.setSpread(0.5);
        shadow.setRadius(5);
        selectableWho().setEffect(shadow);
    }

    default void styleAsDeselected(){
        selectableWho().setEffect(null);
    }

    void select();
    void deselect();

    default void setSelectEnabled(boolean selectEnabled){
        if(!selectEnabled){
            selectableWho().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {});
        }
        else {
            selectableWho().removeEventHandler(MouseEvent.MOUSE_CLICKED, event -> {});
        }
    }

}
