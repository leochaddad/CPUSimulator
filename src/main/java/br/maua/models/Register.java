package br.maua.models;

public class Register extends Connectable  {

        //Constructor for testing purposes only
        public Register(String internalData) {
            this.internalData = new Data(internalData);
        }

        Connectable bufferedConnection;
        private boolean bufferEnabled = false;
        boolean outputEnabled = false;

    /**
     * @param BE BufferEnable
     * @param OE OutputEnable
     * @param IN Clock (Latch Input)
     */
        public void Control(boolean BE, boolean OE, boolean IN ) throws Exception {
            if(OE^outputEnabled){
                outputEnable(OE);
            }
            if(BE^bufferEnabled){
                bufferEnable(BE);
                System.out.println("BE: "+BE);
                System.out.println("Buffer: "+bufferEnabled);
            }
            if(IN){
                System.out.println("IN Buffer: "+bufferEnabled);
                latchInput();
            }
        }

    /**
     * Sets the buffered connection
     * @param connectable bufferedConnection
     */
        public void connectToBuffered(Connectable connectable){
            bufferedConnection = connectable;
        }

    /**
     * Adds or removes bufferedConnection from outputs
     * @param enabled
     */
        public void bufferEnable(boolean enabled) throws Exception {
            if (enabled & !bufferEnabled){
                bufferEnabled = true;
                this.connectTo(bufferedConnection);
            }else if (!enabled & bufferEnabled){
                bufferEnabled = false;
                this.disconnectFrom(bufferedConnection);
                bufferedConnection.Update();
            }
        }


    public void setInternalData(Data internalData) {
        this.internalData = internalData;
        if(outputEnabled){
            setOutDataAndNotify(this.getInternalData());
        }
    }

    /**
         * Getter for register's internal data
         * @return internalData
         */
        public Data getInternalData() {
            return internalData;
        }

        /**
         * Register's internal data
         */
        private Data internalData = new Data("000000011000");

        /**
         * Latches on external data
         */
            public void latchInput() throws Exception {
                if(bufferEnabled){
                    throw new Exception("BUFFER MUST BE DISABLED TO LATCH VALUE INTO REGISTER");
                }
                else{
                    this.internalData.clear();
                    for(Connectable input:inputs) {
                        this.internalData = (this.internalData.add(input.getOutcomingData()));
                    }
                    this.setInternalData(internalData);
                }
            }

        /**
         * Enables or disables output
         * @param enable
         */
            public void outputEnable(boolean enable){
                this.outputEnabled = enable;
                if (enable){
                    setOutDataAndNotify(this.getInternalData());
                }
                else{
                    setOutDataAndNotify(new Data());
                }
            }

    public void Update() {

    }

}
