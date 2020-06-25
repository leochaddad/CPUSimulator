package br.maua.models;

import br.maua.interfaces.Notifiable;

import java.util.ArrayList;

public abstract class Connectable implements Notifiable {

    /**
     * List of connections to the object
     */
    protected ArrayList<Connection> connections = new ArrayList<>();

    /**
     * Data external to the object
     */
    protected Data externalData = new Data("000000000000");

    /**
     * Getter for list of connections
     * @return List of connections to the object
     */
    public ArrayList<Connection> getConnections() {
        return connections;
    }

    /**
     * For internal use
     * @return List of Connectable objects connected to this
     */
    private ArrayList<Connectable> getConnectedElements() {
        ArrayList<Connectable> connectedElements = new ArrayList<>();
        for(Connection connection:this.getConnections()){
            connectedElements.add(connection.getConnectedElement());}
        return connectedElements;
    }

    /**
     * Sets external data of the object and all others with an external connection
     * @param externalData Data to be set as external
     */
    public void setExternalData(Data externalData) {
        this.externalData = externalData;
        for(Connection connection:this.getConnections()){
            if(connection.isExternalConnection()&
                    !connection.getConnectedElement().getExternalData().getData().equals(this.getExternalData().getData())){
                connection.getConnectedElement().setExternalData(this.getExternalData());
            }

            else{
                this.externalData = externalData;
                connection.getConnectedElement().notifyChange();
            }
        }
    }

    /**
     * Getter method for external data
     * @return External Data to the object
     */
    public Data getExternalData() {
        return externalData;
    }

    /**
     * Creates new connection (if it doesn't exist)
     * Adds to this objects connections and to the object being connected
     * @param connectableElement Element to be connected
     */
    public void connectTo(Connectable connectableElement, boolean externalConnection){
        if(!this.getConnectedElements().contains(connectableElement)){
            this.connections.add(new Connection(connectableElement, externalConnection));
            connectableElement.connectTo(this,externalConnection);
        }
    }

    /**
     * Creates new connection (if it doesn't exist) external by default
     * Adds to this objects connections and to the object being connected
     * @param connectableElement Element to be connected
     */
    public void connectTo(Connectable connectableElement){
        if(!this.getConnectedElements().contains(connectableElement)){
            this.connections.add(new Connection(connectableElement));
            connectableElement.connectTo(this);
        }
    }

}
