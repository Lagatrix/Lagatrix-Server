package lagatrix.manager.deletion.software;

import lagatrix.entities.components.PackageManagerComponents;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.software.PackageManagerException;
import lagatrix.tools.command.CommandExecutor;

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
         String commandPackage = String.format("%s %s %s %s", 
                component.getCommand(), component.getUninstall(), component.getNoConfirm(), packageName),
         commandClean = String.format("%s %s %s", 
                component.getCommand(),  component.getClean(), component.getNoConfirm());
        
        try {
            executor.executeCommand(commandPackage, true);
            executor.executeCommand(commandClean, true);
        } catch (CommandException ex) {
            throw new PackageManagerException(PackageManagerException.getMessageFailPackage("uninstall", packageName));
        }
    }
}
