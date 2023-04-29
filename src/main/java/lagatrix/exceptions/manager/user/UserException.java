package lagatrix.exceptions.manager.user;

import lagatrix.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the users.
 *
 * @author javier
 * @since 1.0
 */
public class UserException extends ManagerException{
    
    public UserException(String errorMessage) {
        super(errorMessage);
    }
    
}
