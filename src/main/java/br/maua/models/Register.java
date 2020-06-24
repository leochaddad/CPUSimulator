package br.maua.models;

    public class Register extends Connectable{

        //Constructor for testing purposes only
        public Register(String internalData) {
            this.internalData = new Data(internalData);
        }

        public Data getInternalData() {
            return internalData;
        }

        private Data internalData = new Data("000000011000");

        public void in(){
            internalData = getIncomingOutcomingData();
        }
        public void out(){
            setIncomingOutcomingData(this.getIncomingOutcomingData().add(this.internalData));
        }
    }
