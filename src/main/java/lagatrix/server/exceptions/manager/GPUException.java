package lagatrix.server.exceptions.manager;

/**
 * This exception repersents all errors related to the GPU.
 *
 * @author javier
 * @since 1.0
 */
public class GPUException extends ManagerException {
    private String component;

    public GPUException(String component) {
        super(String.format("Cant obtain component: %s", component));
        this.component = component;
    }

    public String getComponent() {
        return component;
    }
}
