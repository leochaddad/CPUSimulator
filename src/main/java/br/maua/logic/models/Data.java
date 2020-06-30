package br.maua.logic.models;

public final class Data {

    private static final int dataSize = 12;

    public Data(String data) {
        this.data = data;
    }

    public Data() {

    }

    public void setFromDecimal(Integer data) {
        String binString = Integer.toBinaryString(data);
        while (binString.length() < dataSize) {
            binString = "0" + binString;
        }
        this.data = binString;
    }

    public void resize() {
        String dataString = this.getData();
        while (dataString.length() < dataSize) {
            dataString = "0" + dataString;
        }
        this.setData(dataString);
    }

    public void cutInHalf(boolean firstHalf){
        String dataString = this.getData();
        if (firstHalf){
            dataString = dataString.substring(0,dataSize/2);
        }
        else {
            dataString = dataString.substring(dataSize/2,dataSize);
        }
        this.setData(dataString);
    }

    private String data = "000000000000";

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }


    /**
     * @return Integer value of binary data
     */
    public int getDecimalData(){
        return Integer.parseInt(data,2);
    }


    /**
     * Does bitwise or operation between data
     * @param dataToAdd
     * @return bitwise or operation
     */
    public Data add(Data dataToAdd){
        StringBuilder bitWiseSum = new StringBuilder();
        for(int i = 0; i< dataSize; i++){
            bitWiseSum.append(Integer.parseInt(String.valueOf(this.data.charAt(i))) |
                    Integer.parseInt(String.valueOf(dataToAdd.data.charAt(i))));
        }
        return new Data(bitWiseSum.toString());
    }

    /**
     * Does bitwise !and operation between data (reverses data.add)
     * @param dataToRemove
     * @return bitwise and operation
     */
    public Data remove(Data dataToRemove){
        StringBuilder bitWiseRm = new StringBuilder();
        for(int i = 0; i< dataSize; i++){
            bitWiseRm.append(Integer.parseInt(String.valueOf(this.data.charAt(i))) &
                   (Integer.parseInt(String.valueOf(dataToRemove.data.charAt(i))) ^ 1));
        }
        return new Data(bitWiseRm.toString());
    }

    public Data clear(){
        this.setData("0".repeat(dataSize));
        return this;
    }



    @Override
    public String toString() {
        return data + "  "+ this.getDecimalData();
    }
}
