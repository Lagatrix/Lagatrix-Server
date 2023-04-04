package lagatrix.server.exceptions;

/**
 * This class respresents not supported actions.
 *
 * @author javierfh03
 * @since 0.2
 */
public class NotSupportedOperation extends LagatrixException {
    
    public NotSupportedOperation(String description) {
        super(description);
    }
}
