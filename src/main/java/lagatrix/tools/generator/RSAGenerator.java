package lagatrix.tools.generator;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import lagatrix.exceptions.connection.AlgorithmException;

/**
 * This clas generate RSA keys pair.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RSAGenerator {
    
    /**
     * This method create an RSA keys.
     * 
     * @return The pair.
     * @throws AlgorithmException If can't create the RSA keys.
     */
    public static KeyPair generatePair() throws AlgorithmException {
        KeyPairGenerator keyGen;
        
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
            
            keyGen.initialize(4092);
            return keyGen.genKeyPair();
        } catch (NoSuchAlgorithmException ex) {
           throw new AlgorithmException("Can't create RSA keys");
        }
    }
}
