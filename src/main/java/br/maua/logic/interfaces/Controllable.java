package br.maua.logic.interfaces;

public interface Controllable {
    void controlByString(String logic) throws Exception;
    String getControlls();
    int getControlSize();
}
