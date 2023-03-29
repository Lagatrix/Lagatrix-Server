package lagatrix.server.manager.insertion.software;

import java.util.logging.Level;
import java.util.logging.Logger;
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.components.PackageManagerComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.event.EventException;
import lagatrix.server.exceptions.manager.software.PackageManagerException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class install packege in the system.
 * 
 * @author javier
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
    
    public void installCommand(String packageName) throws PackageManagerException{
        String command = String.format("%s %s %s %s", 
                component.getCommand(), component.getInstall(), component.getNoConfirm(), packageName);
        
        try {
            executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new PackageManagerException(String.format(
                    "Error when %s the package %s", "install", packageName));
        } 
    }
}
