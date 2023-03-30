package lagatrix.server.exceptions;

/**
 * This excpetion appear when teh package manager does not support the family 
 * of distribution.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class NotValidFamilyException extends LagatrixException {
    
    public NotValidFamilyException(String family) {
        super(String.format("Fatal error, the family %s of distribution is not supported.", family));
    }
    
}
