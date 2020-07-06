package br.maua.ui.elements.mouselogic.interfaces;

import br.maua.ui.elements.internalparts.connexionpoint.ConnexionPoint;
import br.maua.ui.elements.internalparts.draghandle.DragHandle;

import java.util.ArrayList;

public interface Resizable {

    ArrayList<DragHandle> dragHandles = new ArrayList<>();
    ArrayList<DragHandle> getDragHandles();
    void addHandles();
    void extendX(double x);
    void extendY(double y);

    default void setResizeEnabled(boolean resizeEnabled){
        getDragHandles().forEach(dh->dh.setVisible(resizeEnabled));
    }

}
