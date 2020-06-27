package br.maua.models.connectables;

import br.maua.interfaces.Controllable;
import br.maua.models.Connectable;
import br.maua.models.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Memory extends Connectable implements Controllable {


    private  int memorySize;
    private String[] memoryContents = new String[memorySize];
    private Connectable dataPort;
    private Connectable adressPort;
    private boolean readEnable = false;
    private boolean writeEnable = false;

    public Memory(String name, int memorySize, Connectable dataPort, Connectable addressPort) {
        super(name);
        this.dataPort = dataPort;
        this.adressPort = addressPort;
        dataPort.connectTo(this);
        addressPort.connectTo(this);
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

    public void ControlByString(String controls) throws Exception {
        Control((Character.getNumericValue(controls.charAt(0)))==1,
                (Character.getNumericValue(controls.charAt(1)))==1 );
    }
    @Override
    public String getControlls() {
        return (this.getName()+"_RE "+
                this.getName()+"_WE " );
    }

    @Override
    public int getControlSize() {
        return 2;
    }

    private void read(){
        setOutDataAndNotify(new Data(memoryContents[adressPort.getOutcomingData().getDecimalData()]));
    }
    private void write(){
        memoryContents[adressPort.getOutcomingData().getDecimalData()]=dataPort.getOutcomingData().getData();
    }


    public void Show() {
        for(String data:memoryContents){
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
