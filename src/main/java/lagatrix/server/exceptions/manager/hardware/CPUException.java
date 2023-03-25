package lagatrix.server.exceptions.manager.hardware;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the CPU.
 * 
 * @author javier
 * @since 1.0
 */
public class CPUException extends ManagerException {
    private String component;

    public CPUException(String component) {
        super(String.format("Cant obtain component: %s", component));
        this.component = component;
    }

    public String getComponent() {
        return component;
    }
}
