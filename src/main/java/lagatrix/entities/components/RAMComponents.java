package lagatrix.entities.components;

/**
 * This enum represents the components of RAM.
 * 
 * @author javierfh03
 * @since 0.1
 */
public enum RAMComponents {
    CAPACITY("Memory capacity", "'[A-Z]' '{print $1}'"),
    UNIT_CAPACITY("UnitCapacity", "'[0-9].' '{print $2}'");
    
    
    private String name;
    private String filter;

    /**
     * The constructor of enum.
     *
     * @param name The name of the component.
     * @param filter The filter to obtain this info.
     */
    private RAMComponents(String name, String filter) {
        this.name = name;
        this.filter = filter;
    }

    public String getName() {
        return name;
    }

    public String getFilter() {
        return filter;
    }
}
