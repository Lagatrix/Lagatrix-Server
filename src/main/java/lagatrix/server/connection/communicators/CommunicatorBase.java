package lagatrix.server.connection.communicators;

import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.connection.BadClassFormatException;
import lagatrix.server.exceptions.connection.ConnectionInOutException;
import lagatrix.server.exceptions.connection.ObtainRequestException;
import lagatrix.server.exceptions.connection.SendResponseException;

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
     * @throws ObtainRequestException If can't obtain the request.
     * @throws BadClassFormatException If client send an not valid class.
     */
    public Request obtainRequest() throws ObtainRequestException, BadClassFormatException;
    
    /**
     * This method send an response to the client.
     * 
     * @param response The response to send.
     * @throws SendResponseException If can't send the response.
     */
    public void sendResponse(Response response) throws SendResponseException;
    
    /**
     * Close the connector.
     * 
     * @throws ConnectionInOutException If the connector can't close.
     */
    public void close() throws ConnectionInOutException;
}
