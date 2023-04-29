package lagatrix.connection.communicators;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import lagatrix.connection.client.ObjectSocket;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.connection.AlgorithmException;
import lagatrix.exceptions.connection.BadClassFormatException;
import lagatrix.exceptions.connection.ConnectionInOutException;

/**
 * This interface is used to apply method to create communication class.
 * 
 * @author javierfh03
 * @since 0.2
 */
public abstract class CommunicatorBase {
    
    protected ObjectSocket socket;

    public CommunicatorBase(ObjectSocket socket) {
        this.socket = socket;
    }
    
    /**
     * This method obtain an request to the client.
     * 
     * @return The request of the client.
     * @throws ConnectionInOutException If have an I/O error in communication.
     * @throws AlgorithmException If have an error related to the cipher.
     * @throws BadClassFormatException If the class who send the client is not 
     * valid.
     */
    public abstract Request obtainRequest() throws ConnectionInOutException, AlgorithmException, BadClassFormatException;
    
    /**
     * This method send an response to the client.
     * 
     * @param response The response to send.
     * @throws ConnectionInOutException If have an I/O error in communication.
     * @throws AlgorithmException If have an error related to the cipher.
     */
    public abstract void sendResponse(Response response) throws ConnectionInOutException, AlgorithmException;
    
    /**
     * Close the connector.
     * 
     * @throws ConnectionInOutException If the connector can't close.
     */
    public void close() throws ConnectionInOutException {
        try {
            socket.getOut().close();
            socket.getIn().close();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO(
                    this.getClass(), ActionsEnum.CLOSE, ex));
        }
    }
    
    /**
     * Get the client IP.
     * 
     * @return The IP.
     */
    public String getClientIp() {
        return socket.getSocket().getInetAddress().toString();
    }
}
