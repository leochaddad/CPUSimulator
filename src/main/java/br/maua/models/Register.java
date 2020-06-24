package br.maua.models;

    public class Register extends Connectable{

        public Data getInternalData() {
            return internalData;
        }

        private Data internalData = new Data("000000011000");

        public void in(){
            internalData = getIncomingOutcomingData();
        }
        public void out(){
            this.getIncomingOutcomingData().add(this.internalData);
            for(Connectable connected:this.getConnectedTo()){
                connected.setIncomingOutcomingData(this.getIncomingOutcomingData());
            }
        }
    }
