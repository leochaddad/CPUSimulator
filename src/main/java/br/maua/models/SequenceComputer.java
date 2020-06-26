package br.maua.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SequenceComputer {


    public Register registerA = new Register("000000000001");
    public Register registerB = new Register("000000000011");
    public Register registerC = new Register("000000000000");

    public Bus bus = new Bus();
    public Alu alu = new Alu(registerA,registerB);


    public void setup(){
        registerA.connectToBuffered(bus);
        registerB.connectToBuffered(bus);
        bus.connectTo(registerA);
        bus.connectTo(registerB);
        bus.connectTo(registerC);
        alu.connectTo(registerC);
        registerC.connectToBuffered(bus);
    }

    public void step(Group root)   {
        setup();
    }

    public Group UIObject() {
        return new Group(registerA.UIObject(50,150),
                registerB.UIObject(200, 150), bus.UIObject(250,10), registerC.UIObject(300,300));
    }

}
