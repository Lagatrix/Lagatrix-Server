package lagatrix.server.exceptions.manager;

/**
 * This exception repersents all errors related to the temperature of devices.
 * 
 * @author javier
 * @since 1.0
 */
public class TemperatureException extends ManagerException {

    public TemperatureException(String component) {
        super(String.format("Can't obtain temperature of: %s", component));
    }

}
