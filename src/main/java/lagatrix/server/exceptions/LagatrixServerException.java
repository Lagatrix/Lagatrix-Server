package lagatrix.server.exceptions;

/**
 * This exception repersents any exception of the program. All exception extends 
 * of this class.
 * 
 * @author javier
 * @since 1.0
 */
public class LagatrixServerException extends Exception{

    public LagatrixServerException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("Exception %s -> %s", getClass(), super.getMessage());
    }
}
