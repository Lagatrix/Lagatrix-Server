package lagatrix.server.manager.deletion.software;

import lagatrix.server.entities.components.PackageManagerComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.software.PackageManagerException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class uninstall packages of Linux.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class PackageDeletion {
    
    private CommandExecutor executor;
    private PackageManagerComponents component;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     * @param component The package manager to use.
     */
    public PackageDeletion(CommandExecutor executor, PackageManagerComponents component) {
        this.executor = executor;
        this.component = component;
    }
    
    /**
     * This method uninstall an package into the system.
     * 
     * @param packageName The name of the package who uninstall.
     * @throws PackageManagerException If a problem occurs with the execution 
     * of the command.
     */
    public void uninstallPackage(String packageName) throws PackageManagerException{
         String command = String.format("%s %s %s %s", 
                component.getCommand(), component.getUninstall(), component.getNoConfirm(), packageName);
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new PackageManagerException(PackageManagerException.getMessageFailPackage("uninstall", packageName));
        }
    }
}
