package lagatrix.server.exceptions.manager.user;

/**
 * This exception repersents all errors related if an user can't establish home.
 *
 * @author javier
 * @since 1.0
 */
public class HomeException extends UserException{

    public HomeException(String component) {
        super(String.format("%s can't establish", component));
    }

}
