package lagatrix.server.file.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import lagatrix.server.exceptions.file.FileIOException;
import static lagatrix.server.exceptions.file.FileIOException.getMessageIO;
import lagatrix.server.exceptions.file.InvalidConfException;

/**
 * This class read teh conf file.
 *
 * @author javierfh03
 * @since 0.2
 */
public class ConfigController {
    
    private File confing;
    private Properties confLector;

    /**
     * The constructor of the class.
     */
    public ConfigController() {
        confing = new File("/etc/lagatrix.conf");
        confLector = new Properties();
    }

    /**
     * Open the file.
     * 
     * @throws FileIOException If can't open or find file.
     */
    public void open() throws FileIOException {
        try {
            confLector.load(new FileInputStream(confing));
        } catch (IOException ex) {
             throw new FileIOException(getMessageIO(confing.getName()));
        }
    }
    
    /**
     * Obtain the properties of the program.
     * 
     * @return The map with the properties.
     * @throws InvalidConfException If the properties are invalid.
     */
    public Map<String, Object> obtainProperties() throws InvalidConfException {
        Map<String, Object> properties = new HashMap<>();
        
        try {
            properties.put("ip", obtainIP());
            properties.put("port", obtainPort());
            properties.put("root", rootAccess());
            
            return properties;
        } catch (NullPointerException ex) {
            throw new InvalidConfException("Missing parameters");
        } catch (NumberFormatException ex) {
            throw new InvalidConfException("Port is not an number");
        }
    }
    
    /**
     * Obtain the IP who use the socket.
     * 
     * @return The IP.
     * @throws InvalidConfException If the propertie is invalid.
     */
    private InetAddress obtainIP() throws InvalidConfException {
        String ip = (String) confLector.get("ip");
        
        try {
            return InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            throw new InvalidConfException(String.format("Invalid IP: %s", ip));
        }
    }
    
    /**
     * Obtain the port who use the socket.
     * 
     * @return The port.
     * @throws InvalidConfException If the propertie is invalid.
     */
    private int obtainPort() throws InvalidConfException {
        int port = Integer.parseInt(confLector.get("port").toString());
        
        if (port > 1023 && port < 49152) {
            return port;
        } else {
            throw new InvalidConfException(String.format("Invalid port: %s", port));
        }
    }
    
    /**
     * Obtain if root can auth in the porgram.
     * 
     * @return If it can.
     * @throws InvalidConfException If the propertie is invalid.
     */
    private boolean rootAccess() throws InvalidConfException {
        String allowRoot = confLector.get("root_access").toString();
        
        if (allowRoot.equals("YES")) {
            return true;
        } else if (allowRoot.equals("NO")) {
            return false;
        } else {
            throw new InvalidConfException(String.format("Invalid root access: %s", allowRoot));
        }
    } 
}
