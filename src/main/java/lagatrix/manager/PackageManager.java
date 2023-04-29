package lagatrix.manager;

import lagatrix.entities.components.PackageManagerComponents;
import lagatrix.exceptions.manager.software.PackageManagerException;
import lagatrix.manager.deletion.software.PackageDeletion;
import lagatrix.manager.information.software.PackageInfo;
import lagatrix.manager.insertion.software.PackageInsertion;
import lagatrix.manager.modificator.software.PackageModificator;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class interact with the package manager of the system.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class PackageManager {
    
    private PackageDeletion deletion;
    private PackageInfo information;
    private PackageModificator modificator;
    private PackageInsertion insertion;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     * @param component The package manager to use.
     */
    public PackageManager(CommandExecutor executor, PackageManagerComponents component) {
        this.deletion = new PackageDeletion(executor, component);
        this.information = new PackageInfo(executor, component);
        this.modificator = new PackageModificator(executor, component);
        this.insertion = new PackageInsertion(executor, component);
    }
    
    /**
     * This method install an package into the system.
     * 
     * @param packageName The name of the package who install.
     * @throws PackageManagerException If not install.
     */
    public void installPackage(String packageName) throws PackageManagerException{
        insertion.installPackage(packageName);
    }
    
    /**
     * This method upgrade an package.
     * 
     * @param packageName The name of the package who install.
     * @throws PackageManagerException If not upgrade.
     */
    public void upgradePackage(String packageName) throws PackageManagerException{
        modificator.upgradePackage(packageName);
    }
    
    /**
     * This method uninstall an package.
     * 
     * @param packageName The name of the package who uninstall.
     * @throws PackageManagerException If not uninstall.
     */
    public void uninstallPackage(String packageName) throws PackageManagerException{
        deletion.uninstallPackage(packageName);
    }
    
    /**
     * This method upgrade all packages of system.
     * 
     * @throws PackageManagerException If the upgrade fail.
     */
    public void upgradePackages() throws PackageManagerException{
        modificator.upgradeAllPackages();
    }
    
    /**
     * This method update repositories of system.
     * 
     * @throws PackageManagerException If the update fail.
     */
    public void updateRepositories() throws PackageManagerException{
        modificator.updateRepositories();
    }
    
    /**
     * This method check if package is instaled.
     * 
     * @param packageName If is instaled.
     * @return If the package is instaled.
     */
    public boolean isPackageInstaled(String packageName) {
        try {
            return information.isInstaled(packageName);
        } catch (PackageManagerException ex) {
            return false;
        }
    }
}
