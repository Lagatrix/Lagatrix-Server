package lagatrix.server.connection.communicators;

import java.io.IOException;
import java.net.Socket;
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.connection.AlgorithmException;
import lagatrix.server.exceptions.connection.BadClassFormatException;
import lagatrix.server.exceptions.connection.ConnectionInOutException;

/**
 * This class manage the Request and Response objects without encryption.
 *
 * @author javierfh03
 * @since 0.2
 */
public class PlainCommunicator extends CommunicatorBase {

    public PlainCommunicator(Socket socket) throws ConnectionInOutException {
        super(socket);
    }
    
    @Override
    public Request obtainRequest() throws ConnectionInOutException, AlgorithmException, BadClassFormatException {
        try {
            return (Request) in.readObject();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO(
                    this.getClass(), ActionsEnum.RECEIVE));
        } catch (ClassNotFoundException ex) {
            throw new BadClassFormatException("unexpected class");
        }
    }

    @Override
    public void sendResponse(Response response) throws ConnectionInOutException, AlgorithmException {
        try {
            out.writeObject(response);
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO(
                    this.getClass(), ActionsEnum.SEND));
        }
    }
}
