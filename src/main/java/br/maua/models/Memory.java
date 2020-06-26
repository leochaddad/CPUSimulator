package br.maua.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Memory extends Connectable {


    private static int memorySize = 16;
    private Data[] memoryContents = new Data[memorySize];
    private Connectable dataPort;
    private Connectable adressPort;
    private boolean readEnable = false;
    private boolean writeEnable = false;

    public Memory(int memorySize, Connectable dataPort, Connectable adressPort) {
        this.dataPort = dataPort;
        this.adressPort = adressPort;
        dataPort.connectTo(this);
        adressPort.connectTo(this);
    }

    public void Control(boolean RE, boolean WE) throws Exception {
        if(RE&WE){
            throw new Exception("COUNTER CANNOT READ AND WRITE SIMULTANEOUSLY");
        }
        else if(RE^readEnable){
            readEnable = RE;
        }
        else if(WE^writeEnable){
            writeEnable = WE;
        }
        this.Update();
    }

    private void read(){
        setOutDataAndNotify(memoryContents[adressPort.getOutcomingData().getDecimalData()]);
    }
    private void write(){
        memoryContents[adressPort.getOutcomingData().getDecimalData()]=dataPort.getOutcomingData();
    }


    public void Show() {
        for(Data data:memoryContents){
            if(data==null){
                System.out.println("null");
            }
            else {
                System.out.println(data.toString());
            }

        }

    }

    @Override
    public void Update() {
        if(readEnable){
            read();
        }
        if(writeEnable){
            write();
        }
        else {
            setOutDataAndNotify(this.getOutcomingData().clear());
        }
    }
}
