package br.maua.models;

import br.maua.models.connectables.Alu;
import br.maua.models.connectables.Bus;
import br.maua.models.connectables.Register;
import br.maua.uiobjects.RegisterDrawer;
import javafx.scene.Group;

public class SequenceComputer {


    public Register registerA = new Register("000000000001");
    public Register registerB = new Register("000000000011");
    public Register registerC = new Register("000000000000");

    public Bus bus = new Bus();
    public Alu alu = new Alu(registerA,registerB);

    RegisterDrawer drawer = new RegisterDrawer(registerA,600,400);


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
        return new Group(
                alu.UIObject(500.0,500.0),
                drawer.draw(),
                registerB.UIObject(250, 100),
                bus.UIObject(10,10),
                drawer.drawConnections(1, 10),
                registerC.UIObject(145,300)
        );
    }

}
