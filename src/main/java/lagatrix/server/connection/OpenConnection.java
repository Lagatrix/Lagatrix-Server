package lagatrix.server.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Map;
import lagatrix.server.exceptions.connection.ConnectionInOutException;
import lagatrix.server.exceptions.file.FileException;
import lagatrix.server.exceptions.file.InvalidConfException;
import lagatrix.server.file.config.ConfigController;
import lagatrix.server.file.log.LogContoller;

/**
 * This class prepare the necessary components of connection and make it.
 * 
 * @author javierfh03
 * @since 0.2
 */
public class OpenConnection {
    
    private LogContoller logger;
    private ConfigController config;

    public OpenConnection() {
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
    public void startConnection () {
        ServerSocket socket;
        Map<String, Object> properties;
        
        try {
            properties = config.obtainProperties();
            socket = new ServerSocket(Integer.parseInt(properties.get("port").toString()), 50,
                    (InetAddress) properties.get("ip"));
            
            new ConnectionListener(socket, logger).start();
        } catch (IOException ex) {
            logger.error(new ConnectionInOutException("Can't open the server socket"));
            System.exit(4);
        } catch (InvalidConfException ex) {
            logger.error(ex);
            System.exit(5);
        }
    }
}
