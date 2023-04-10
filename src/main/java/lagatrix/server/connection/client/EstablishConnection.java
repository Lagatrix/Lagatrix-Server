package lagatrix.server.connection.client;

import java.net.Socket;
import java.security.KeyPair;
import javax.crypto.SecretKey;
import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.connection.communicators.PlainCommunicator;
import lagatrix.server.connection.communicators.RSACommunicator;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.connection.AlgorithmException;
import lagatrix.server.exceptions.connection.BadClassFormatException;
import lagatrix.server.exceptions.connection.ConnectionInOutException;
import lagatrix.server.tools.generator.RSAGenerator;

/**
 * This method establish the communication with the client exchanging the RSA 
 * and AES keys.
 *
 * @author javierfh03
 * @since 0.2
 */
public class EstablishConnection {

    private Socket socket;
    private KeyPair pair;

    /**
     * The constructor of the class.
     * 
     * @param socket The socket of the client.
     * @throws AlgorithmException If can't create an pair.
     */
    public EstablishConnection(Socket socket) throws AlgorithmException {
        this.socket = socket;
        this.pair = RSAGenerator.generatePair();
    }
    
    /**
     * This method send teh generated RSA to the client.
     * 
     * @throws ConnectionInOutException If the connection not set.
     * @throws AlgorithmException If an error occurs with the key.
     */
    public void sendRSA() throws ConnectionInOutException, AlgorithmException {
        PlainCommunicator pc = new PlainCommunicator(socket);
        Response res = new Response();
        
        res.setResponse(pair.getPublic());
        
        pc.sendResponse(res);
        pc.close();
    }
    
    /**
     * This method obtain the AES key of the client and return the 
     * AESCommunicator object.
     * 
     * @return The AESCommunicator.
     * @throws ConnectionInOutException If the connection not set.
     * @throws AlgorithmException If an error occurs with the key.
     * @throws BadClassFormatException If the client sends an invalid class.
     */
    public AESCommunicator obtainAES() throws ConnectionInOutException, AlgorithmException, BadClassFormatException {
        RSACommunicator rsac = new RSACommunicator(socket, pair);
        Request request;
        
        request = rsac.obtainRequest();
        rsac.close();
        
        return new AESCommunicator(socket, (SecretKey) request.getParams()[0]);
    }
}
