package br.maua.models;

public class Connection {

    /**
     * If connected is external it should be set to true
     */
    private boolean externalConnection;

    /**
     * Checks if this connection is external
     * @return true if connection is external, false otherwise
     */
    public boolean isExternalConnection() {
        return externalConnection;
    }

    /**
     * Getter method to connectedElement
     * @return Element of the connection
     */
    public Connectable getConnectedElement() {
        return connectedElement;
    }

    /**
     * Element of the connection
     */
    private Connectable connectedElement;


    /**
     * Constructor
     * @param connection Element to be connected
     * @param externalConnection true if connection is external
     */
    public Connection(Connectable connection, boolean externalConnection) {
        this.externalConnection = externalConnection;
        this.connectedElement = connection;
    }

    /**
     * Constructor with externalConnection default set to true
     * @param connection Element to be connected
     */
    public Connection(Connectable connection) {
        this.externalConnection = true;
        this.connectedElement = connection;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "connectedElement=" + connectedElement +
                '}';
    }
}
