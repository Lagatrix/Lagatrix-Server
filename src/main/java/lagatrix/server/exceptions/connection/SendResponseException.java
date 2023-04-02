package lagatrix.server.exceptions.connection;

/**
 * This exception represents the errors relates to send response to the client.
 *
 * @author javier
 * @since 1.0
 */
public class SendResponseException extends ConnectionException {

    public SendResponseException(String typeCommunicator) {
        super(String.format("%s communicator error when send response", typeCommunicator));
    }

}
