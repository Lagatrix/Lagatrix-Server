package lagatrix.server.exceptions.manager.user;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the users.
 *
 * @author javier
 * @since 1.0
 */
public class UserException extends ManagerException{
    
    public UserException(String component) {
        super(String.format("Error handling user component: %s", component));
    }
    
}
