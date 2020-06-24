package br.maua.models;

import java.util.Arrays;

public final class Data {

    private static final int dataSize = 12;
    private String data;

    public Data(String data) {
        this.data = data;
    }

    public Data() {
        this.data ="000000000000";
    }

    public String getData() {
        return data;
    }

    public int getDecimalData(){
        return Integer.parseInt(data,2);
    }


    public void Add(Data dataToAdd){
        StringBuilder bitWiseSum = new StringBuilder();
        for(int i = 0; i< dataSize; i++){
            bitWiseSum.append(Integer.parseInt(String.valueOf(this.data.charAt(i))) |
                    Integer.parseInt(String.valueOf(dataToAdd.data.charAt(i))));
        }
        this.data = bitWiseSum.toString();
    }


    @Override
    public String toString() {
        return data + "  "+ this.getDecimalData();

    }
}
