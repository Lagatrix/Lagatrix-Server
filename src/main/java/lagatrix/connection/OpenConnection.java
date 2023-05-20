package lagatrix.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Map;
import lagatrix.exceptions.NotValidFamilyException;
import lagatrix.exceptions.connection.ConnectionInOutException;
import lagatrix.exceptions.file.FileException;
import lagatrix.exceptions.file.InvalidConfException;
import lagatrix.exceptions.manager.os.OSException;
import lagatrix.file.config.ConfigController;
import lagatrix.file.log.LogContoller;
import lagatrix.manager.OSManager;
import lagatrix.tools.command.CommandExecutor;
import sun.misc.Signal;

/**
 * This class prepare the necessary components of connection and make it.
 *
 * @author javierfh03
 * @since 0.2
 */
public class OpenConnection {

    private LogContoller logger;
    private ConfigController config;
    private ServerSocket socket;

    public OpenConnection() {
        // When SIGINT close the program it close the socket.
        Signal.handle(new Signal("INT"), signal -> close());
    }

    /**
     * This method open the required files to make the connection.
     */
    public void openFiles() {
        logger = new LogContoller();
        config = new ConfigController();

        try {
            config.open();
            logger.open();
        } catch (FileException ex) {
            System.err.println(ex.toString());
            System.exit(3);
        }
    }

    /**
     * This method start the connection.
     */
    public void startConnection() {
        Map<String, Object> properties;

        try {
            properties = config.obtainProperties();
            this.socket = new ServerSocket(Integer.parseInt(properties.get("port").toString()), 50,
                    (InetAddress) properties.get("ip"));

            ConnectionListener.listen(socket, logger, new OSManager(new CommandExecutor())
                    .obtainOSInformation().getPackageManager(), (boolean) properties.get("root"));
        } catch (IOException ex) {
            logger.error("localhost", new ConnectionInOutException(
                    String.format("Can't open the server socket because %s", ex)));
            System.exit(4);
        } catch (InvalidConfException ex) {
            logger.error("localhost", ex);
            System.exit(5);
        } catch (OSException ex) {
            logger.error("localhost", ex);
            System.exit(6);
        } catch (NotValidFamilyException ex) {
            logger.error("localhost", ex);
            System.exit(7);
        }
    }

    /**
     * This method close the socket.
     */
    private void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            logger.error("localhost", new ConnectionInOutException(
                    String.format("Can't open the server socket because %s", ex)));
            System.exit(4);
        }
        System.exit(0);
    }
}
