package br.maua.models;

import br.maua.interfaces.Notifiable;

public class Register extends Connectable implements Notifiable {

        //Constructor for testing purposes only
        public Register(String internalData) {
            this.internalData = new Data(internalData);
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

        public void in(){
            internalData = getExternalData();
        }

        public void out(){
            setExternalData(this.getExternalData().add(this.internalData));
        }

    @Override
    public void notifyChange() {

    }
}
