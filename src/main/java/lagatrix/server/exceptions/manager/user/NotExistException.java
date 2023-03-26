package lagatrix.server.exceptions.manager.user;

/**
 * This exception repersents all errors related if an user components not exist.
 *
 * @author javier
 * @since 1.0
 */
public class NotExistException extends UserException{

    public NotExistException(String component) {
        super(String.format("%s not exist", component));
    }

}
