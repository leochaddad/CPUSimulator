package br.maua.ui.interfaces;

import br.maua.ui.elements.internalparts.draghandle.DragHandle;

import java.util.ArrayList;

public interface Resizable {

    ArrayList<DragHandle> dragHandles = new ArrayList<>();
    ArrayList<DragHandle> getDragHandles();
    void addHandles();
    void extendX(double x);
    default void setResizeEnabled(boolean resizeEnabled){
        getDragHandles().forEach(dh->dh.setVisible(resizeEnabled));
    }
}
