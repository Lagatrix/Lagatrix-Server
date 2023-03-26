package lagatrix.server.exceptions.manager.user;

/**
 * This exception repersents all errors related if an user components already 
 * exist.
 *
 * @author javier
 * @since 1.0
 */
public class ExistException extends UserException{

    public ExistException(String component) {
        super(String.format("%s exist", component));
    }

}
