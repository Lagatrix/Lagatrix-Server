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
 * This class manage the Request and Response objects without encryption.
 *
 * @author javierfh03
 * @since 0.2
 */
public class PlainCommunicator implements CommunicatorBase {
    
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public PlainCommunicator(Socket socket) throws ConnectionInOutException {
        this.socket = socket;
        
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("plain text", ActionsEnum.OPEN));
        }
    }
    
    @Override
    public Request obtainRequest() throws ConnectionInOutException, AlgorithmException, BadClassFormatException {
        try {
            return (Request) in.readObject();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("plain text", ActionsEnum.RECEIVE));
        } catch (ClassNotFoundException ex) {
            throw new BadClassFormatException("unexpected class");
        }
    }

    @Override
    public void sendResponse(Response response) throws ConnectionInOutException, AlgorithmException {
        try {
            out.writeObject(response);
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("plain text", ActionsEnum.SEND));
        }
    }

    @Override
    public void close() throws ConnectionInOutException {
        try {
            out.close();
            in.close();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("plain text", ActionsEnum.CLOSE));
        }
    }

    @Override
    public void closeClient() throws ConnectionInOutException {
        try {
            close();
            socket.close();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("plain text", ActionsEnum.CLOSE));
        }
    }

}
