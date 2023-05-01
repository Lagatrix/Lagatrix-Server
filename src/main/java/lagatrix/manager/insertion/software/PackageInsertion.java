package lagatrix.manager.insertion.software;

import lagatrix.entities.components.PackageManagerComponents;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.software.PackageManagerException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class install packege in the system.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class PackageInsertion {
    
    private CommandExecutor executor;
    private PackageManagerComponents component;
    
    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     * @param component The package manager to use.
     */
    public PackageInsertion(CommandExecutor executor, PackageManagerComponents component) {
        this.executor = executor;
        this.component = component;
    }
    
    /**
     * This method install an package into the system.
     * 
     * @param packageName The name of the package who install.
     * @throws PackageManagerException If a problem occurs with the execution of the 
     * command.
     */
    public void installPackage(String packageName) throws PackageManagerException{
        String command = String.format("%s %s %s %s", 
                component.getCommand(), component.getInstall(), component.getNoConfirm(), packageName);
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new PackageManagerException(PackageManagerException
                    .getMessageFailPackage("install", packageName));
        } 
    }
}
