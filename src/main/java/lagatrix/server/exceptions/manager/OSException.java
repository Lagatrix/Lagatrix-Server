package lagatrix.server.exceptions.manager;

/**
 * This exception repersents all errors related to the information of OS.
 *
 * @author javier
 * @since 1.0
 */
public class OSException extends ManagerException{
    private String component;

    public OSException(String component) {
        super(String.format("Can't obtain OS component: %s", component));
        this.component = component;
    }

    public String getComponent() {
        return component;
    }
}
