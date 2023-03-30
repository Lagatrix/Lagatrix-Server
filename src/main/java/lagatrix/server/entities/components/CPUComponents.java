package lagatrix.server.entities.components;

/**
 * This enum represents the components of CPU.
 * 
 * @author javierfh03
 * @since 0.1
 */
public enum CPUComponents {
    MODEL("Model", "Model"),
    CORES("Cores", "^CPU(s):"),
    THREADS("Threads per core", "Thread"),
    L3("L3 Cache memory", "L3"),
    MIN_SPEED("Minimun speed", "CPU min"),
    MAX_SPEED("Max speed", "CPU max");
    
    private String name;
    private String value;

    /**
     * The constructor of enum.
     *
     * @param name The name of the component.
     * @param value His value.
     */
    private CPUComponents(String name, String value) {
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
