package br.maua.models;

import br.maua.interfaces.Notifiable;

public class Alu extends Connectable {
    Connection inputConnectionA;
    Connection inputConnectionB;

    public Alu(Connectable inputA, Connectable inputB) {
        this.connectTo(inputA,false);
        this.connectTo(inputB,false);
    }


    @Override
    public void notifyChange() {

    }
}
