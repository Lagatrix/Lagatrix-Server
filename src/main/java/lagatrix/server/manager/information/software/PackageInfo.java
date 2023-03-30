package lagatrix.server.manager.information.software;

import lagatrix.server.entities.components.PackageManagerComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.software.PackageManagerException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class obtain information of packages in Linux.
 * 
 * @author javier
 * @since 0.1
 */
public class PackageInfo {
    
    private CommandExecutor executor;
    private PackageManagerComponents component;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     * @param component The package manager to use.
     */
    public PackageInfo(CommandExecutor executor, PackageManagerComponents component) {
        this.executor = executor;
        this.component = component;
    }
    
    /**
     * This method check if package is instaled.
     * 
     * @param packageName The name of the package.
     * @return If is instaled.
     * @throws PackageManagerException If a problem occurs with the execution 
     * of the command.
     */
    public boolean isInstaled(String packageName) throws PackageManagerException{
        String command = String.format("%s %s %s %s", 
                component.getCommand(), component.getList(), component.getNoConfirm(), packageName);
        
        try {
            executor.executeCommand(command, true);
            
            return true;
        } catch (CommandException ex) {
            throw new PackageManagerException(PackageManagerException.getMessageFailPackage("Is instaled", packageName));
        }
    }
}