package lagatrix.server.connection.communicators;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.connection.AlgorithmException;
import lagatrix.server.exceptions.connection.BadClassFormatException;
import lagatrix.server.exceptions.connection.ConnectionInOutException;

/**
 * This class manage the Request and Response objects with RSA encryption.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RSACommunicator implements CommunicatorBase {
    
    private Socket socket;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    /**
     * The constructor of the class.
     * 
     * @param socket The client socket.
     * @param pair The pair of RSA keys.
     * @throws ConnectionInOutException If have an I/O error when create 
     * connection.
     */
    public RSACommunicator(Socket socket, KeyPair pair) throws ConnectionInOutException {
        this.socket = socket;
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
        
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("RSA", ActionsEnum.OPEN));
        }
    }

    @Override
    public Request obtainRequest() throws ConnectionInOutException, AlgorithmException, BadClassFormatException {
        Cipher encryptCipher;
        SealedObject sealedObject;
        
        try {
            encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

            sealedObject = (SealedObject) in.readObject();

            return (Request) sealedObject.getObject(encryptCipher);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException ex) {
            throw new AlgorithmException(AlgorithmException.getMessageAlgorithm(ex, "RSA"));
        } catch (ClassNotFoundException | BadPaddingException ex) {
            throw new BadClassFormatException("unexpected class");
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("RSA", ActionsEnum.RECEIVE));
        }
        
    }

    @Override
    public void sendResponse(Response response) throws ConnectionInOutException, AlgorithmException {
        Cipher encryptCipher;
        SealedObject sealedObject;
        
        try {
            encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            sealedObject = new SealedObject( (Serializable) response, encryptCipher);
            
            out.writeObject(sealedObject);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException ex) {
            throw new AlgorithmException("RSA");
        }  catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("RSA", ActionsEnum.SEND));
        }
    }

    @Override
    public void close() throws ConnectionInOutException {
        try {
            out.close();
            in.close();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("RSA", ActionsEnum.CLOSE));
        }
    }

    @Override
    public void closeClient() throws ConnectionInOutException {
        try {
            close();
            socket.close();
        } catch (IOException ex) {
            throw new ConnectionInOutException(ConnectionInOutException.getMessageIO("RSA", ActionsEnum.CLOSE));
        }
    }

}
