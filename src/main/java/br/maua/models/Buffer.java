package br.maua.models;

import br.maua.interfaces.Notifiable;

import java.util.ArrayList;

public class Buffer extends Connectable implements Notifiable {

    /**
     * enabled buffers will allow data to go trought
     */
    private boolean enabled = true;

    /**
     * Setter for enabled
     * @param enabled
     */
    public void setEnabled(boolean enabled){
        this.enabled=enabled;
        if(this.enabled){
            setExternalData(this.externalData);
        }
    }

    /**
     * Overrides method so disabled buffers don't pass data forward to other connectable objects
     * @param externalData Data to be set as external
     */
    @Override
    public void setExternalData(Data externalData) {
        if(enabled){
            this.externalData = externalData;
            for(Connection connection:this.getConnections()){
                if(connection.isExternalConnection()
                        &!connection.getConnectedElement().getExternalData().equals(this.getExternalData())){
                    connection.getConnectedElement().setExternalData(this.getExternalData());
                }

                else{
                    connection.getConnectedElement().notifyChange();
                }
            }
        }
        else{

        }
    }


    @Override
    public void notifyChange() {

    }
}
