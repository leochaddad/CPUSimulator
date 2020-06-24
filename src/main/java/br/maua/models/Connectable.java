package br.maua.models;

import java.util.ArrayList;

public abstract class Connectable {

    private ArrayList<Connectable> connectedTo = new ArrayList<>();
    private Data incomingOutcomingData = new Data();

    public ArrayList<Connectable> getConnectedTo() {
        return connectedTo;
    }

    public void setIncomingOutcomingData(Data incomingOutcomingData) {
        this.incomingOutcomingData = incomingOutcomingData;
        for(Connectable connected:this.getConnectedTo()){
            if(!connected.getIncomingOutcomingData().equals(this.getIncomingOutcomingData()))
            connected.setIncomingOutcomingData(this.getIncomingOutcomingData());
        }
    }

    public Data getIncomingOutcomingData() {
        return incomingOutcomingData;
    }


    public void connectTo(Connectable connectableElement){
        if(!this.connectedTo.contains(connectableElement)){
            this.connectedTo.add(connectableElement);
            connectableElement.connectTo(this);
        }
    }

}
