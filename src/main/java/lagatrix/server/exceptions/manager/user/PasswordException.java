package lagatrix.server.exceptions.manager.user;

/**
 * This exception repersents errors related to the user password.
 *
 * @author javier
 * @since 1.0
 */
public class PasswordException extends UserException{

    public PasswordException() {
        super("Password");
    }

}
