package br.maua.models;

import br.maua.interfaces.Notifiable;

public class Register extends Connectable implements Notifiable {

        //Constructor for testing purposes only
        public Register(String internalData) {
            this.internalData = new Data(internalData);
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
            public void latchInput(){
                this.outputEnable(false);
                internalData.setData(getExternalData().getData());
            }

        /**
         * Enables or disables output
         * @param enable
         */
            public void outputEnable(boolean enable){
                if (!outputEnabled&enable) {
                    outputEnabled = true;
                    setExternalData(this.getExternalData().add(this.internalData));
                }
                else if(!enable&outputEnabled){
                    outputEnabled = false;
                    setExternalData(this.getExternalData().remove(this.internalData));
                }
            }

        @Override
        public void notifyChange() {

        }
}
