package lagatrix.exceptions;

/**
 * This exception repersents any exception of the program. All exception extends 
 * of this class.
 * 
 * @author javier
 * @since 1.0
 */
public class LagatrixException extends Exception{

    public LagatrixException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", getClass().getSimpleName(), super.getMessage());
    }
}
