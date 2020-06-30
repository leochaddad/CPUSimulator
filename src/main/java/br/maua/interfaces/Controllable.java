package br.maua.interfaces;

public interface Controllable {
    public void controlByString(String logic) throws Exception;
    public String getControlls();
    public int getControlSize();
}
