package br.maua.models;

public class Alu extends Connectable {



    private Connectable inputConnectionA;
    private Connectable inputConnectionB;
    private boolean flagZero;
    private boolean flagNeg;
    private int operation =0;

    public Alu(Connectable inputConnectionA, Connectable inputConnectionB) {
        this.inputConnectionA = inputConnectionA;
        this.inputConnectionB = inputConnectionB;
        inputConnectionA.connectTo(this);
        inputConnectionB.connectTo(this);
    }

    private void Operate(){
        switch (operation) {
            case (0):
                sumOperation();
                break;
            case (1):
                subtractOperation();
                break;
        }
    }

    private void subtractOperation() {
        this.getOutcomingData().setFromDecimal(inputConnectionA.getOutcomingData().getDecimalData() -
                inputConnectionB.getOutcomingData().getDecimalData());
        this.setOutDataAndNotify(this.getOutcomingData());
    }

    private void sumOperation(){
        this.getOutcomingData().setFromDecimal(inputConnectionA.getOutcomingData().getDecimalData() +
                inputConnectionB.getOutcomingData().getDecimalData());
        this.setOutDataAndNotify(this.getOutcomingData());
    }


    public void Update() {
        Operate();
    }


    /**
     * 0 - SUM, 1-SUBT
     * @param subt
     */
    public void Control(boolean subt) {
        if (subt){
            this.operation = 1;
        }
        if(!subt){
            this.operation = 0;
        }
        this.Update();
    }

}
