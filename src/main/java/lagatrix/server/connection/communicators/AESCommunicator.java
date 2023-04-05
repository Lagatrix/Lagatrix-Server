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
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.connection.AlgorithmException;
import lagatrix.server.exceptions.connection.BadClassFormatException;
import lagatrix.server.exceptions.connection.ConnectionInOutException;

/**
 * This class manage the Rquest and Response objects with AES encryption.
 *
 * @author javierfh03
 * @since 0.2
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
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("AES", ActionsEnum.OPEN));
        }
    }

    @Override
    public Request obtainRequest() throws ConnectionInOutException, AlgorithmException, BadClassFormatException {
        Cipher encryptCipher;
        SealedObject sealedObject;
        try {
            encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            encryptCipher.init(Cipher.DECRYPT_MODE, key);
            
            sealedObject = (SealedObject) in.readObject();
        
            return (Request) sealedObject.getObject(encryptCipher);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException ex) {
            throw new AlgorithmException(AlgorithmException.getMessageAlgorithm(ex, "AES"));
        } catch (ClassNotFoundException | BadPaddingException ex) {
            throw new BadClassFormatException("unexpected class");
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("AES", ActionsEnum.RECEIVE));
        }
    }

    @Override
    public void sendResponse(Response response) throws ConnectionInOutException, AlgorithmException {
        Cipher encryptCipher;
        SealedObject sealedObject;
        
        try {
            encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            
            sealedObject = new SealedObject( (Serializable) response, encryptCipher);
            
            out.writeObject(sealedObject);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException ex) {
            throw new AlgorithmException("AES");
        }  catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("AES", ActionsEnum.SEND));
        }
    }

    @Override
    public void close() throws ConnectionInOutException {
        try {
            out.close();
            in.close();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("AES", ActionsEnum.CLOSE));
        }
    }

    @Override
    public void closeClient() throws ConnectionInOutException {
        try {
            close();
            socket.close();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("AES", ActionsEnum.CLOSE));
        }
    }

}
