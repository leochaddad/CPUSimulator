package br.maua.models;

import br.maua.interfaces.Controllable;

import java.util.ArrayList;

public class ControlMultiplexer extends Connectable{

    public ControlMultiplexer(ArrayList<Controllable> controllables) {
        super("ControlMultiplexer");
        this.controllables = controllables;
    }

    private ArrayList<Controllable> controllables = new ArrayList<>();

    public String getControlSequence() {
        StringBuilder controlSequence = new StringBuilder();
        for(Controllable controllable:controllables){
            controlSequence.append(controllable.getControlls());
        }
        return controlSequence.toString();
    }


    public void Control(String fullControlSequence) throws Exception {
        int currentIndex = 0;
        for(Controllable controllable:controllables){
            String controlSequence = fullControlSequence.substring(
                    currentIndex, currentIndex+controllable.getControlSize());
            currentIndex += controllable.getControlSize();
            controllable.controlByString(controlSequence);
        }
    }

    @Override
    public void update() {
        try{
        Control(getOutcomingData().getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setup() {

    }
}
