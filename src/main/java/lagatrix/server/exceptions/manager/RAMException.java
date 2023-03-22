package lagatrix.server.exceptions.manager;

/**
 * This exception repersents all errors related to the RAM.
 *
 * @author javier
 * @since 1.0
 */
public class RAMException extends ManagerException {
    private String component;

    public RAMException(String component) {
        super(String.format("Cant obtain component of RAM: %s", component));
        this.component = component;
    }

    public String getComponent() {
        return component;
    }
}
