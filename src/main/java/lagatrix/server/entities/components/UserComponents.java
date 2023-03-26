package lagatrix.server.entities.components;

/**
 * This enum represents the information of Linux user.
 * 
 * @author javier
 */
public enum UserComponents {
    USERNAME("Username", 1),
    HOME("Home path", 6),
    GROUP("Main group", 4),
    SHELL("Password", 7);
    
    
    private String name;
    private int value;

    /**
     * The constructor of enum.
     *
     * @param name The name of the component.
     * @param value His value.
     */
    private UserComponents(String name, int value) {
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
