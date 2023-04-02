package lagatrix.server.connection.communicators;

import lagatrix.server.exceptions.connection.ObtainRequestException;
import lagatrix.server.exceptions.connection.SendResponseException;

/**
 * This interface is used to apply method to create communication class.
 * 
 * @author javierfh03
 * @since 0.2
 */
public interface CommunicatorBase {
    
    public void obtainRequest() throws ObtainRequestException;
    
    public void sendResponse() throws SendResponseException;
}
