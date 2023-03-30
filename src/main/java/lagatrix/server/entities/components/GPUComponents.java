package lagatrix.server.entities.components;

/**
 * This enum represents the components of GPU.
 * 
 * @author javierfh03
 * @since 0.1
 */
public enum GPUComponents {
    MODEL("Model", "Model"),
    VENDOR("Vendor", "Vendor:");
    
    private String name;
    private String value;

    /**
     * The constructor of enum.
     *
     * @param name The name of the component.
     * @param value His value.
     */
    private GPUComponents(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
