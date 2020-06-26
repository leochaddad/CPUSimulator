package br.maua.models;

import br.maua.interfaces.Controlled;
import br.maua.interfaces.Observer;

import java.util.ArrayList;

public abstract class Connectable implements Observer {

    /**
     * Data being outputted
     */
    private Data outcomingData = new Data("000000000000");


    /**
     * List of objects connected
     */
    protected ArrayList<Connectable> inputs = new ArrayList<>();
    protected ArrayList<Connectable> outputs = new ArrayList<>();


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
            observer.Update();
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



}
