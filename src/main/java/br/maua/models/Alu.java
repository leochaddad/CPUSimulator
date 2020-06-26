package br.maua.models;

public class Alu extends Connectable {
    Connectable inputConnectionA;
    Connectable inputConnectionB;

    public Alu(Connectable inputConnectionA, Connectable inputConnectionB) {
        this.inputConnectionA = inputConnectionA;
        this.inputConnectionB = inputConnectionB;
        inputConnectionA.connectTo(this);
        inputConnectionB.connectTo(this);
    }

    private void sumOperation(){
        this.getOutcomingData().setFromDecimal(inputConnectionA.getOutcomingData().getDecimalData() +
                inputConnectionB.getOutcomingData().getDecimalData());
        this.setOutDataAndNotify(this.getOutcomingData());
    }


    public void Update() {
        sumOperation();
    }
}
