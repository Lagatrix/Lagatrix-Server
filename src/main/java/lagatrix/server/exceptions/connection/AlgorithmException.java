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

}
