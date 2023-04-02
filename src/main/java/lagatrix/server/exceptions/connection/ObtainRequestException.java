package lagatrix.server.exceptions.connection;

/**
 * This exception represents the errors relates to obtain request of the client.
 *
 * @author javier
 * @since 1.0
 */
public class ObtainRequestException extends ConnectionException {

    public ObtainRequestException(String typeCommunicator) {
        super(String.format("%s communicator error when obtain request", typeCommunicator));
    }

}
