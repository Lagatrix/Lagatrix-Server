package lagatrix.exceptions.manager.hardware.status;

import lagatrix.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the temperature of devices.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class TemperatureException extends ManagerException {
    
    public TemperatureException(String errorMessage) {
        super(errorMessage);
    }
    
}
