package lagatrix.connection.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.connection.ConnectionInOutException;

/**
 * This class wrapper the socket to use object streams.
 *
 * @author javierfh03
 * @since 0.3
 */
public class ObjectSocket {
    
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    /**
     * The constructor of the class.
     * 
     * @param socket The socket.
     * @throws ConnectionInOutException If can't open the streams.
     */
    public ObjectSocket(Socket socket) throws ConnectionInOutException {
        this.socket = socket;
        
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO(
                    this.getClass(), ActionsEnum.OPEN, ex));
        }
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }
    
    public Socket getSocket() {
        return socket;
    }
}
