package lagatrix.server.connection.communicators;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.connection.BadClassFormatException;
import lagatrix.server.exceptions.connection.ConnectionInOutException;
import lagatrix.server.exceptions.connection.ObtainRequestException;
import lagatrix.server.exceptions.connection.SendResponseException;

/**
 * This class manage the Rquest and Response objects with RSA encryption.
 *
 * @author javier
 * @since 1.0
 */
public class AESCommunicator implements CommunicatorBase {
    
    private Socket socket;
    private SecretKey key;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    /**
     * The constructor of the class.
     * 
     * @param socket The client socket.
     * @param key The AES key.
     * @throws ConnectionInOutException If have an I/O error when create 
     * connection.
     */
    public AESCommunicator(Socket socket, SecretKey key) throws ConnectionInOutException {
        this.key = key;
        this.socket = socket;
        
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessage(this.getClass(), 
                    "AES problem I/O when open connection", socket.getInetAddress().toString()));
        }
    }

    @Override
    public Request obtainRequest() throws ObtainRequestException, BadClassFormatException {
        Cipher encryptCipher;
        SealedObject sealedObject;
        try {
            encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            encryptCipher.init(Cipher.DECRYPT_MODE, key);
            
            sealedObject = (SealedObject) in.readObject();
        
            return (Request) sealedObject.getObject(encryptCipher);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IOException | IllegalBlockSizeException ex) {
            throw new ObtainRequestException("AES");
        } catch (ClassNotFoundException | BadPaddingException ex) {
            throw new BadClassFormatException(BadClassFormatException.getMessage(this.getClass(), 
                    "unexpected class", socket.getInetAddress().toString()));
        }
    }

    @Override
    public void sendResponse(Response response) throws SendResponseException {
        Cipher encryptCipher;
        SealedObject sealedObject;
        
        try {
            encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            
            sealedObject = new SealedObject( (Serializable) response, encryptCipher);
            
            out.writeObject(sealedObject);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IOException | IllegalBlockSizeException ex) {
            throw new SendResponseException("AES");
        }  
    }

    @Override
    public void close() throws ConnectionInOutException {
        try {
            out.close();
            in.close();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessage(this.getClass(), 
                    "AES problem I/O when close connection", socket.getInetAddress().toString()));
        }
    }

}
