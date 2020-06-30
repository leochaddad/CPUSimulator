package br.maua.logic.models.connectables.simple;

public class Counter extends Register {

    //For testing
    public Counter(String name, String internalData) {
        super(name, internalData);
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

    public void controlByString(String controls) throws Exception {
        Control((Character.getNumericValue(controls.charAt(0)))==1,
                (Character.getNumericValue(controls.charAt(1)))==1,
                (Character.getNumericValue(controls.charAt(2)))==1,
                (Character.getNumericValue(controls.charAt(3)))==1);
    }

    @Override
    public String getControlls() {
        return (this.getName()+"_OE "+
                this.getName()+"_BE "+
                this.getName()+"_IN "+
                this.getName()+"_CK ");
    }

    @Override
    public int getControlSize() {
        return 4;
    }



    private void increment() throws Exception {
            if(this.bufferEnabled){
                throw new Exception("BUFFER MUST BE DISABLED TO INCREASE COUNTER");
            }
            this.getInternalData().setFromDecimal(this.getInternalData().getDecimalData()+1);
            this.setInternalData(this.getInternalData());
    }
}
