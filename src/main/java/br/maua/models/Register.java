package br.maua.models;

public class Register extends Connectable  {

        //Constructor for testing purposes only
        public Register(String internalData) {
            this.internalData = new Data(internalData);
        }

        Connectable bufferedConnection;
        private boolean bufferEnabled = false;

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
            if (enabled){
                this.connectTo(bufferedConnection);
                bufferEnabled = true;
            }else if (bufferEnabled){
                this.disconnectFrom(bufferedConnection);
                bufferedConnection.Update();
            }
            else {
                throw new Exception("BUFFER IS ALREADY DISABLED");
            }
        }

        /**
         * used by outputEnable()
         */
        boolean outputEnabled = false;

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
         * Disables output from this register and latches on external data
         */
            public void latchInput() throws Exception {
                if(outputEnabled){
                    throw new Exception("OUTPUT MUST BE DISABLED TO LATCH VALUE INTO REGISTER");
                }
                else{
                    this.internalData.clear();
                    for(Connectable input:inputs) {
                        this.internalData = (this.internalData.add(input.getOutcomingData()));
                    }
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
