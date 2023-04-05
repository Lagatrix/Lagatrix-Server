package lagatrix.server.exceptions.connection;

/**
 * This exception uses when have an error related to the cypher.
 *
 * @author javierfh03
 * @since 0.2
 */
public class AlgorithmException extends ConnectionException {

    public AlgorithmException(String description) {
        super(description);
    }

    /**
     * This method obtain the error message.
     * 
     * @param excpetion The exception of cypher who excepted.
     * @param algorithm The algorithm who used.
     * @return The message.
     */
    public static String getMessageAlgorithm(Exception excpetion, String algorithm) {
        return String.format("%s have an this error when cypher: %s", algorithm, 
                excpetion.getClass().getSimpleName());
    }
}
