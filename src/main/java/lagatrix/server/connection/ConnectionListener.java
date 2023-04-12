package lagatrix.server.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import lagatrix.server.connection.client.AuthClient;
import lagatrix.server.connection.client.ClientManager;
import lagatrix.server.connection.client.EstablishConnection;
import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.exceptions.connection.ConnectionException;
import lagatrix.server.exceptions.connection.ConnectionInOutException;
import lagatrix.server.exceptions.file.CantCreateFile;
import lagatrix.server.file.log.LogContoller;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class create the connextion. wait an client, auth this and set manager.
 *
 * @author javierfh03
 * @since 0.2
 */
public class ConnectionListener extends Thread {

    private ServerSocket socket;
    private LogContoller logger;

    /**
     * Constructor of the class.
     */
    public ConnectionListener(){
        try {
            logger = new LogContoller();
        } catch (CantCreateFile ex) {
            System.err.println(ex.toString());
            System.exit(4);
        }

        try {
            socket = new ServerSocket(8000);
        } catch (IOException ex) {
            logger.error(new ConnectionInOutException("Can't open the server socket"));
            System.exit(3);
        }
    }

    @Override
    public void run() {
        AESCommunicator aesc;
        Socket client;
        CommandExecutor executor;

        while (true) {
            try {
                client = getClient();

                aesc = establishConnection(client);
                
                executor = new CommandExecutor();

                if (auth(aesc, executor)){
                    new ClientManager(aesc, logger, executor).start();
                } else {
                    aesc.close();
                }
            } catch (ConnectionException ex) {
                logger.warning(ex);
            }
        }
    }

    /**
     * This method make the exchanging the keys RSA and AES with client.
     *
     * @param client The client to establish the connection.
     * @return The AESCommunicator.
     * @throws ConnectionException If raise an connection error.
     */
    private synchronized AESCommunicator establishConnection(Socket client) throws ConnectionException {
        EstablishConnection ec = new EstablishConnection(client);

        ec.sendRSA();

        return ec.obtainAES();
    }
    
    /**
     * This method auth the user.
     * 
     * @param communicator The communicator with the client.
     * @param executor The executor who use the client.
     * @return If the user auth.
     * @throws ConnectionException If have an connection error. 
     */
    private synchronized boolean auth(AESCommunicator communicator, CommandExecutor executor) throws ConnectionException {
        AuthClient auth = new AuthClient(communicator, executor);
        
        return auth.makeLogin();
    }

    /**
     * Obtain the client, wraps the IOException.
     *
     * @return The client socket.
     * @throws ConnectionInOutException If have an I/O exception.
     */
    private Socket getClient() throws ConnectionInOutException {
        try {
            return socket.accept();
        } catch (IOException ex) {
            throw new ConnectionInOutException(String.format("Can't accept the client"));
        }
    }
}
