package br.maua.models.connectables;

import br.maua.interfaces.Controllable;
import br.maua.models.Connectable;
import br.maua.models.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Memory extends Connectable implements Controllable {


    private int memorySize = 16;
    private ArrayList<String> memoryContents = new ArrayList<>();
    private Connectable dataPort;
    private Connectable adressPort;
    private boolean readEnable = false;
    private boolean writeEnable = false;

    public Memory(String name, int memorySize) {
        super(name);
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
        this.update();
    }

    public void controlByString(String controls) throws Exception {
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
        setOutDataAndNotify(new Data(memoryContents.get(adressPort.getOutcomingData().getDecimalData())));
    }
    private void write(){
        memoryContents.set(adressPort.getOutcomingData().getDecimalData(),dataPort.getOutcomingData().getData());
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
    public void update() {
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

    @Override
    public void setup() {
        this.memoryContents.addAll(Collections.nCopies(memorySize,""));
        this.adressPort = inputs.get(0);
        this.dataPort = inputs.get(1);
    }
}
