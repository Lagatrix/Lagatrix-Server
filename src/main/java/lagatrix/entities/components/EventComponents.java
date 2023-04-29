package lagatrix.entities.components;

/**
 * This enum represents the components of event.
 * 
 * @author javierfh03
 * @since 0.1
 */
public enum EventComponents {
    MINUTE("Minute", "1"),
    HOUR("Hour", "2"),
    DAY_MONTH("Day month", "3"),
    MONTH("Month", "4"),
    DAY_WEEK("Day week", "5"),
    COMMAND("Command", "6");
    
    private String name;
    private String value;

    /**
     * The constructor of enum.
     *
     * @param name The name of the component.
     * @param value His value.
     */
    private EventComponents(String name, String value) {
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
