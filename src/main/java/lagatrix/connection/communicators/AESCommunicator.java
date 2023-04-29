package lagatrix.connection.communicators;

import java.io.IOException;
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
import lagatrix.connection.client.ObjectSocket;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.connection.AlgorithmException;
import lagatrix.exceptions.connection.BadClassFormatException;
import lagatrix.exceptions.connection.ConnectionInOutException;

/**
 * This class manage the Request and Response objects with AES encryption.
 *
 * @author javierfh03
 * @since 0.2
 */
public class AESCommunicator extends CommunicatorBase {
    
    private SecretKey key;

    /**
     * The constructor of the class.
     * 
     * @param socket The client socket.
     * @param key The AES key.
     * @throws ConnectionInOutException If have an I/O error when create 
     * connection.
     */
    public AESCommunicator(ObjectSocket socket, SecretKey key) throws ConnectionInOutException {
        super(socket);
        this.key = key;
        this.socket = socket;
    }

    @Override
    public Request obtainRequest() throws ConnectionInOutException, AlgorithmException, BadClassFormatException {
        Cipher encryptCipher;
        SealedObject sealedObject;
        try {
            encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            encryptCipher.init(Cipher.DECRYPT_MODE, key);
            
            sealedObject = (SealedObject) socket.getIn().readObject();
        
            return (Request) sealedObject.getObject(encryptCipher);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException ex) {
            throw new AlgorithmException(AlgorithmException.getMessageAlgorithm(ex, "AES"));
        } catch (ClassNotFoundException | BadPaddingException ex) {
            throw new BadClassFormatException();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO(
                    this.getClass(), ActionsEnum.RECEIVE, ex));
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
            
            socket.getOut().writeObject(sealedObject);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException ex) {
            throw new AlgorithmException("AES");
        }  catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO(
                    this.getClass(), ActionsEnum.SEND, ex));
        }
    }
}
