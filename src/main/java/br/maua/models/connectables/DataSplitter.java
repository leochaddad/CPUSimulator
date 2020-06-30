package br.maua.models.connectables;

import br.maua.models.Connectable;
import br.maua.models.Data;

public class DataSplitter extends Connectable {

    Connectable output;


    public DataSplitter(String name, Connectable output, boolean resize, boolean firstHalf) {
        super("Splitter");
        this.output = output;
        this.resize = resize;
        this.firstHalf = firstHalf;
        this.connectTo(output);
    }

    boolean resize;
    boolean firstHalf;


    @Override
    public Data getOutcomingData() {
        outcomingData.cutInHalf(firstHalf);
        if(resize){
            outcomingData.resize();
        }
        return outcomingData;
    }

    @Override
    public void update() {
        this.setOutDataAndNotify(new Data().clear());
        for(Connectable input:inputs){
            this.setOutDataAndNotify(this.getOutcomingData().add(input.getOutcomingData()));
        }
    }

    @Override
    public void setup() {

    }
}
