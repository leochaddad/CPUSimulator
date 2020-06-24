package br.maua.models;

    public class Register extends Connectable{
        public Data getInternalData() {
            return internalData;
        }

        private Data internalData = new Data("000000001000");
        public void In(){
        }
    }
