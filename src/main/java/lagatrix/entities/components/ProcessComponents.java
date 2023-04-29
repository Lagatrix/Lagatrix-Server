package lagatrix.entities.components;

/**
 * This enum represents the information of process.
 * 
 * @author javierfh03
 * @since 0.1
 */
public enum ProcessComponents {
    PID("PID", 2),
    COMMAND("Command", 11),
    USER("User", 1),
    USE_CPU("Use CPU", 3),
    USE_MEM("Use RAM", 4);
    
    private String name;
    private int value;

    /**
     * The constructor of enum.
     *
     * @param name The name of the component.
     * @param value His value.
     */
    private ProcessComponents(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
