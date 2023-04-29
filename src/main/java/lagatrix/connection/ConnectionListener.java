package lagatrix.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import lagatrix.connection.client.AuthClient;
import lagatrix.connection.client.ClientManager;
import lagatrix.connection.client.EstablishConnection;
import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.components.PackageManagerComponents;
import lagatrix.exceptions.connection.ConnectionException;
import lagatrix.exceptions.connection.ConnectionInOutException;
import lagatrix.file.log.LogContoller;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class create the connextion. wait an client, auth this and set manager.
 *
 * @author javierfh03
 * @since 0.2
 */
public class ConnectionListener extends Thread {

    private ServerSocket socket;
    private LogContoller logger;
    private PackageManagerComponents packageManager;

    /**
     * Constructor of the class.
     * 
     * @param socket The socket who use.
     * @param logger The LogController.
     * @param packageManager The PackageManager of the system.
     */
    public ConnectionListener(ServerSocket socket, LogContoller logger, PackageManagerComponents packageManager){
        this.socket = socket;
        this.logger = logger;
        this.packageManager = packageManager;
    }

    @Override
    public void run() {
        AESCommunicator aesc;
        Socket client = null;
        CommandExecutor executor;

        while (true) {
            try {
                client = getClient();

                aesc = establishConnection(client);
                
                executor = new CommandExecutor();

                if (auth(aesc, executor)){
                    new ClientManager(aesc, logger, executor, packageManager).start();
                    
                } else {
                    aesc.close();
                }
            } catch (ConnectionException ex) {
                if (client != null) {
                    logger.warning(client.getInetAddress().toString(), ex);
                } else {
                    logger.warning("Unknown IP", ex);
                }
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
