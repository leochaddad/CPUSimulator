package br.maua.interfaces;

public interface Controllable {
    public void ControlByString(String logic) throws Exception;
    public String getControlls();
    public int getControlSize();
}
