package br.maua.models;

public class Counter extends Register {

    //For testing
    public Counter(String internalData) {
        super(internalData);
    }

    public void Control(boolean BE, boolean OE, boolean IN, boolean CK) throws Exception {
        super.Control(BE, OE, IN);
        if (CK){
            if (IN){
                throw new Exception("COUNTER CANNOT LATCH AND INCREMENT SIMULTANEOUSLY");
            }
            else{
                increment();
            }
        }
    }

    private void increment() throws Exception {
            if(this.bufferEnabled){
                throw new Exception("BUFFER MUST BE DISABLED TO INCREASE COUNTER");
            }
            this.getInternalData().setFromDecimal(this.getInternalData().getDecimalData()+1);
            this.setInternalData(this.getInternalData());
    }
}
