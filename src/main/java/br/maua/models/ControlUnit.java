package br.maua.models;

import br.maua.models.connectables.Counter;
import br.maua.models.connectables.DataSplitter;
import br.maua.models.connectables.Memory;
import br.maua.models.connectables.Register;

public class ControlUnit extends Connectable{
    //ControlMultiplexer controlMultiplexer = new ControlMultiplexer();
    Counter programCounter = new Counter("PC", "000000000000");
    Counter microcodeCounter = new Counter("MIR", "000000000000");
    Register instructionRegister = new Register("IR", "000000");
    //Memory  microcodeMemory = new Memory("MICROCODE_MEMORY", 16,microcodeCounter,controlMultiplexer);
    DataSplitter splitterA = new DataSplitter("SPLITTER_A",microcodeCounter, false,true);
    DataSplitter splitterB = new DataSplitter("SPLITTER_A",this, false,true);

    public ControlUnit(String name) {
        super(name);
    }

    public void setup(){
        instructionRegister.connectTo(splitterA);
        instructionRegister.connectToBuffered(splitterB);
    }

    @Override
    public void update() {

    }
}
