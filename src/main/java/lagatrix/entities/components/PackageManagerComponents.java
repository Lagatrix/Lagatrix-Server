package lagatrix.entities.components;

/**
 * This enum represents the components of package manager.
 * 
 * @author javierfh03
 * @since 0.1
 */
public enum PackageManagerComponents {
    APT("apt", new String[] {"install", "purge", "update", "upgrade", "autoremove", "list --installed | grep"}, "-y"),
    YUM("yum", new String[] {"install", "remove", "update", "upgrade", "autoremove", "list installed"}, "-y"),
    ZYPPER("zypper", new String[] {"install", "purge", "refresh", "update", "clean --all", "search -i"}, "-n"),
    PACMAN("pacman", new String[] {"-S", "-R", "-Sy", "-Syu", "-Qdtq", "-Q"}, "--noconfirm");

    private String command;
    private String[] options;
    private String noConfirm;

    /**
     * The constructor of enum.
     *
     * @param command The package manager command who exec.
     * @param options The options of package manager commands.
     * @param noConfirm The no confirm option of command.
     */
    private PackageManagerComponents(String command, String[] options, String noConfirm) {
        this.command = command;
        this.options = options;
        this.noConfirm = noConfirm;
    }

    public String getCommand() {
        return command;
    }

    public String getInstall() {
        return options[0];
    }
    
    public String getUninstall() {
        return options[1];
    }
    
    public String getUpdate() {
        return options[2];
    }
    
    public String getUpgrade() {
        return options[3];
    }
    
    public String getClean() {
        return options[4];
    }
    
    public String getList() {
        return options[5];
    }

    public String getNoConfirm() {
        return noConfirm;
    }
}
