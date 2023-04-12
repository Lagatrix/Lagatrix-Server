package lagatrix.server.connection.communicators;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.connection.AlgorithmException;
import lagatrix.server.exceptions.connection.BadClassFormatException;
import lagatrix.server.exceptions.connection.ConnectionInOutException;

/**
 * This interface is used to apply method to create communication class.
 * 
 * @author javierfh03
 * @since 0.2
 */
public abstract class CommunicatorBase {
    
    protected Socket socket;
    protected ObjectOutputStream out;
    protected ObjectInputStream in;

    public CommunicatorBase(Socket socket) throws ConnectionInOutException {
        this.socket = socket;
        
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO(
                    this.getClass(), ActionsEnum.OPEN));
        }
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
            out.close();
            in.close();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO(
                    this.getClass(), ActionsEnum.CLOSE));
        }
    }
    
    /**
     * Close the connector and client connection.
     * 
     * @throws ConnectionInOutException If the connector can't close.
     */
    public void closeClient() throws ConnectionInOutException {
        try {
            close();
            socket.close();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO(
                    this.getClass(), ActionsEnum.CLOSE));
        }
    }
    
    /**
     * Get the client IP.
     * 
     * @return The IP.
     */
    public String getClientIp() {
        return socket.getInetAddress().toString();
    }
}
