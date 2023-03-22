package lagatrix.server.exceptions.manager;

/**
 *  This exception repersents all errors related to the use of devices.
 * 
 * @author javier
 * @since 1.0
 */
public class UseException extends ManagerException {

    public UseException(String component) {
        super(String.format("Can't obtain use of: %s", component));
    }

}
