package lagatrix.manager.modificator.software;

import lagatrix.entities.components.PackageManagerComponents;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.software.PackageManagerException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class update the packages of Linux.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class PackageModificator {
    
    private CommandExecutor executor;
    private PackageManagerComponents component;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     * @param component The package manager to use.
     */
    public PackageModificator(CommandExecutor executor, PackageManagerComponents component) {
        this.executor = executor;
        this.component = component;
    }
    
    /**
     * This method upgrade an package.
     * 
     * @param packageName The name of the package who upgrade.
     * @throws PackageManagerException If a problem occurs with the execution 
     * of the command.
     */
    public void upgradePackage(String packageName) throws PackageManagerException{
         String command = String.format("%s %s %s %s", 
                component.getCommand(), component.getUpgrade(), component.getNoConfirm(), packageName);
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new PackageManagerException(PackageManagerException.getMessageFailPackage("upgrade", packageName));
        }
    }
    
    /**
     * This method upgrade all packages of the system.
     * 
     * @throws PackageManagerException If a problem occurs with the execution 
     * of the command.
     */
    public void upgradeAllPackages() throws PackageManagerException{
         String command = String.format("%s %s %s", 
                component.getCommand(), component.getUpgrade(), component.getNoConfirm());
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new PackageManagerException(PackageManagerException.getMessageFailAllPackages("upgrade"));
        }
    }
    
    /**
     * This method upgrade all packages of the system.
     * 
     * @throws PackageManagerException If a problem occurs with the execution 
     * of the command.
     */
    public void updateRepositories() throws PackageManagerException{
         String command = String.format("%s %s %s", 
                component.getCommand(), component.getUpdate(), component.getNoConfirm());
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new PackageManagerException(PackageManagerException.getMessageFailAllPackages("update"));
        }
    }
}
