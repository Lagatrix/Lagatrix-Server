package lagatrix.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import lagatrix.connection.client.AuthClient;
import lagatrix.connection.client.ClientManager;
import lagatrix.connection.client.EstablishConnection;
import lagatrix.connection.client.ObjectSocket;
import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.actions.ActionsEnum;
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

    private Socket client;
    private LogContoller logger;
    private PackageManagerComponents packageManager;
    private boolean rootAccess;

    /**
     * Constructor of the class.
     *
     * @param client The client who connect.
     * @param logger The LogController.
     * @param packageManager The PackageManager of the system.
     * @param rootAccess If root can access.
     */
    private ConnectionListener(Socket client, LogContoller logger, PackageManagerComponents packageManager, boolean rootAccess) {
        this.client = client;
        this.logger = logger;
        this.packageManager = packageManager;
        this.rootAccess = rootAccess;
    }

    /**
     * Init the listen.
     *
     * @param socket The socket who use.
     * @param logger The LogController.
     * @param packageManager The PackageManager of the system.
     * @param rootAccess If root can access.
     */
    public static void listen(ServerSocket socket, LogContoller logger, PackageManagerComponents packageManager, boolean rootAccess) {
        Socket client;

        while (true) {
            try {
                client = socket.accept();

                new ConnectionListener(client, logger, packageManager, rootAccess).start();
            } catch (IOException ex) {
                logger.warning("Unknown IP", new ConnectionException(
                        ConnectionInOutException.getMessageIO(ConnectionListener.class,
                                ActionsEnum.RECEIVE, ex)));
            }
        }
    }

    @Override
    public void run() {
        AESCommunicator aesc;
        CommandExecutor executor;
        
        try {
            aesc = establishConnection(new ObjectSocket(client));

            executor = new CommandExecutor();

            if (auth(aesc, executor)) {
                new ClientManager(aesc, logger, executor, packageManager).startManager();
            } else {
                aesc.close();
            }
        } catch (ConnectionException ex) {
            logger.warning(client.getInetAddress().toString(), ex);
        }
    }

    /**
     * This method make the exchanging the keys RSA and AES with client.
     *
     * @param client The client to establish the connection.
     * @return The AESCommunicator.
     * @throws ConnectionException If raise an connection error.
     */
    private synchronized AESCommunicator establishConnection(ObjectSocket client) throws ConnectionException {
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
        AuthClient auth = new AuthClient(communicator, executor, rootAccess);

        return auth.makeLogin();
    }
}
