package br.maua.logic.models.connectables.simple;

import br.maua.logic.interfaces.Controllable;
import br.maua.logic.models.connectables.Connectable;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class Alu extends Connectable implements Controllable {

    private  Connectable inputConnectionA;
    private  Connectable inputConnectionB;
    private boolean flagZero;
    private boolean flagNeg;
    private int operation =0;

    public Alu(String name) {
        super(name);
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


    public void update() {
        Operate();
    }


    /**
     * 0 - SUM, 1-SUBT
     * @param SUB
     */
    public void Control(boolean SUB) {
        if (SUB){
            this.operation = 1;
        }
        if(!SUB){
            this.operation = 0;
        }
        this.update();
    }

    public void controlByString(String controls) throws Exception {
        Control((Character.getNumericValue(controls.charAt(0)))==1);}

    @Override
    public String getControlls() {
        return (this.getName()+"_SUB ");
    }

    @Override
    public int getControlSize() {
        return 1;
    }

    public Group UIObject(Double x, Double y) {
        Polygon aluShape = new Polygon();
        aluShape.getPoints().addAll(
                x, y,
                x-100, y,
                x-150, y+50,
                x-200, y,
                x-300, y,
                x-200,y+100,
                x-100,y+100,
                x,y
                );
        return new Group(aluShape);
    }

    @Override
    public void setup() {
        this.inputConnectionA = inputs.get(0);
        this.inputConnectionA = inputs.get(1);
    }
}
