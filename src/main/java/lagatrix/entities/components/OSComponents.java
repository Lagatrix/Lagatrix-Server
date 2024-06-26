package lagatrix.entities.components;

/**
 * This enum represents the information of Linux OS.
 * 
 * @author javierfh03
 * @since 0.1
 */
public enum OSComponents {
    DISTRIBUTION("Distribution", "grep -w 'PRETTY_NAME'"),
    DISTRIBUTION_FAMILY("Distribution family", "grep -wE 'ID_LIKE|ID'"),
    DISTRIBUTION_NAME("Distribution name", "grep -w 'ID'"),
    DISTRIBUTION_VERSION("Distribution version", "grep -w 'VERSION_ID'"),
    KERNEL("Kernel", "-r"),
    HOSTNAME("Hostname", "-n");
    

    private String name;
    private String value;

    /**
     * The constructor of enum.
     *
     * @param name The name of the component.
     * @param value His value.
     */
    private OSComponents(String name, String value) {
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
