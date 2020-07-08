package br.maua.ui.interfaces;


import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public interface Selectable {

    default boolean selectableEnabled(){return false;}


    default void styleAsSelected(){
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.SKYBLUE);
        shadow.setSpread(0.5);
        shadow.setRadius(5);
        ((Group)this).setEffect(shadow);
    }

    default void styleAsDeselected(){
        ((Group)this).setEffect(null);
    }


    default void defaultDeselect(){
        styleAsDeselected();
    }

    void select();
    void deselect();


}
