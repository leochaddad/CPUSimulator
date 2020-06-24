package br.maua.models;

import java.util.ArrayList;

public abstract class Connectable {
    private ArrayList<Connectable> connectedTo = new ArrayList<>();
    private Data incomingData = new Data();

    public Data getIncomingData() {
        return incomingData;
    }


    public void connectTo(Connectable connectableElement){
        if(!this.connectedTo.contains(connectableElement)){
            this.connectedTo.add(connectableElement);
            connectableElement.connectTo(this);
        }
    }

}
