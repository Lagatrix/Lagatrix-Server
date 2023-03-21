package lagatrix.server.exceptions.manager;

import lagatrix.server.exceptions.LagatrixServerException;

/**
 * This exception repersents all errors related to the CPU.
 * 
 * @author javier
 * @since 1.0
 */
public class CPUException extends LagatrixServerException {

    public CPUException(String component) {
        super(String.format("Cant obtain component: %s", component));
    }

}
