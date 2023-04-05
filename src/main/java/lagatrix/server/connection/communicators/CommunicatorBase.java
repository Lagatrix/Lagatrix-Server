package lagatrix.server.connection.communicators;

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
public interface CommunicatorBase {
    
    /**
     * This method obtain an request to the client.
     * 
     * @return The request of the client.
     * @throws ConnectionInOutException If have an I/O error in communication.
     * @throws AlgorithmException If have an error related to the cipher.
     * @throws BadClassFormatException If the class who send the client is not 
     * valid.
     */
    public Request obtainRequest() throws ConnectionInOutException, AlgorithmException, BadClassFormatException;
    
    /**
     * This method send an response to the client.
     * 
     * @param response The response to send.
     * @throws ConnectionInOutException If have an I/O error in communication.
     * @throws AlgorithmException If have an error related to the cipher.
     */
    public void sendResponse(Response response) throws ConnectionInOutException, AlgorithmException;
    
    /**
     * Close the connector.
     * 
     * @throws ConnectionInOutException If the connector can't close.
     */
    public void close() throws ConnectionInOutException;
    
    /**
     * Close the connector and client connection.
     * 
     * @throws ConnectionInOutException If the connector can't close.
     */
    public void closeClient() throws ConnectionInOutException;
}
