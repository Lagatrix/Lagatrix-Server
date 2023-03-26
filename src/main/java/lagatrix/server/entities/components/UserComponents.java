package lagatrix.server.entities.components;

/**
 * This enum represents the information of Linux user.
 * 
 * @author javier
 */
public enum UserComponents {
    USERNAME("Username", 1, "-l"),
    HOME("Home path", 6, "-d"),
    GROUP("Main group", 4, "-G"),
    SHELL("Password", 7, "-p");
    
    
    private String name, valueUserMod;
    private int valueInfo;

    /**
     * The constructor of enum.
     *
     * @param name The name of the component.
     * @param valueInfo His value in /etc/passwd.
     * @param valueUserMod His value with usermod.
     */
    private UserComponents(String name, int value, String valueUserMod) {
        this.name = name;
        this.valueInfo = value;
        this.valueUserMod = valueUserMod;
    }

    public String getName() {
        return name;
    }

    public int getValueInfo() {
        return valueInfo;
    }

    public String getValueUserMod() {
        return valueUserMod;
    }
}
