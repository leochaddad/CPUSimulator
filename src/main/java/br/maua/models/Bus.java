package br.maua.models;

public class Bus extends Connectable  {

    public void Update() {
        this.setOutDataAndNotify(this.getOutcomingData().clear());
        for(Connectable input:inputs){
            this.setOutDataAndNotify(this.getOutcomingData().add(input.getOutcomingData()));
        }
    }
}
