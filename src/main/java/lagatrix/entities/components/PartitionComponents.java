package lagatrix.entities.components;

/**
 * This enum represents the components of the partition.
 *
 * @author javierfh03
 * @since 0.1
 */
public enum PartitionComponents {
    FILE_SYSTEM("File System", 1),
    SIZE("Size", 2),
    USED("Used", 3),
    AVATIBLE("Avatible", 4),
    PERCENTAGE_USE("Percetntage use", 5),
    MOUNT_IN("Mount in", 6);

    private String name;
    private int value;

    /**
     * The constructor of enum.
     *
     * @param name The name of the component.
     * @param value His value.
     */
    private PartitionComponents(String name, int value) {
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
