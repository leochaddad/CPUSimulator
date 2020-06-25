package br.maua.models;

public class Alu extends Connectable {
    Connection inputConnectionA;
    Connection inputConnectionB;

    public Alu(Connectable inputA, Connectable inputB) {
        this.connectTo(inputA,false);
        inputConnectionA = connections.get(0);
        this.connectTo(inputB,false);
        inputConnectionB = connections.get(1);
    }

    public void sumOperation(){
        this.getExternalData().setFromDecimal(inputConnectionA.getConnectedElement().getExternalData().getDecimalData() +
                inputConnectionB.getConnectedElement().getExternalData().getDecimalData());
        this.setExternalData(this.getExternalData());
    }


    @Override
    public void notifyChange() {
        sumOperation();
    }


}
