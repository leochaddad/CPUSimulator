package br.maua.logic.models.connectables;

import br.maua.logic.interfaces.Observer;
import br.maua.logic.interfaces.Setup;
import br.maua.logic.models.Data;

import java.util.ArrayList;

public abstract class Connectable implements Observer, Setup {

    public Connectable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private final String name;

    /**
     * Data being outputted
     */
    protected Data outcomingData = new Data("000000000000");


    /**
     * List of objects connected
     */
    protected ArrayList<Connectable> inputs = new ArrayList<>();
    protected ArrayList<Connectable> outputs = new ArrayList<>();
    Connectable bufferedConnection;

    /**
     * sets the outcomming data and alerts outputs of change
     * @param data new data
     */
    protected void setOutDataAndNotify(Data data) {
        if(!this.outcomingData.getData().equals(data.getData()))
        this.outcomingData = data;
        notifyObservers();
    }

    public Data getOutcomingData() {
        return outcomingData;
    }

    /**
     *Alerts outputs of change
     */
    private void notifyObservers() {
        for(Observer observer:outputs){
            observer.update();
        }
    }

    /**
     * Creates new connection (if not one already)
     * @param output element to be connected
     */
    public void connectTo(Connectable output){
        this.outputs.add(output);
        output.inputs.add(this);
        notifyObservers();
    }

    public void disconnectFrom(Connectable output) throws Exception {
        if(output.inputs.contains(this)&this.outputs.contains(output)){
            output.inputs.remove(this);
            this.outputs.remove(output);
            notifyObservers();
        }
        else {
            throw new Exception("OBJECT IS NOT PROPERLY CONNECTED");
        }
    }

    public boolean isConnectedTo(Connectable connectable){
        return this.outputs.contains(connectable);
    }


    protected boolean bufferEnabled = false;
    /**
     * Sets the buffered connection
     * @param connectable bufferedConnection
     */

    public void connectToBuffered(Connectable connectable){
        bufferedConnection = connectable;
    }
    /**
     * Adds or removes bufferedConnection from outputs
     * @param enabled
     */


    public void bufferEnable(boolean enabled) throws Exception {
        if (enabled & !bufferEnabled){
            bufferEnabled = true;
            this.connectTo(bufferedConnection);
        }else if (!enabled & bufferEnabled){
            bufferEnabled = false;
            this.disconnectFrom(bufferedConnection);
            bufferedConnection.update();
        }
    }


}
