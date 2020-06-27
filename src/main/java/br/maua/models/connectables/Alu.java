package br.maua.models.connectables;

import br.maua.interfaces.Controllable;
import br.maua.models.Connectable;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Alu extends Connectable implements Controllable {

    private final Connectable inputConnectionA;
    private final Connectable inputConnectionB;
    private boolean flagZero;
    private boolean flagNeg;
    private int operation =0;

    public Alu(String name, Connectable inputConnectionA, Connectable inputConnectionB) {
        super(name);
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
     * @param SUB
     */
    public void Control(boolean SUB) {
        if (SUB){
            this.operation = 1;
        }
        if(!SUB){
            this.operation = 0;
        }
        this.Update();
    }

    public void ControlByString(String controls) throws Exception {
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

}
