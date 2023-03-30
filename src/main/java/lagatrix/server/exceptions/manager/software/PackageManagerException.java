package lagatrix.server.exceptions.manager.software;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the package manager.
 *
 * @author javier
 * @since 0.1
 */
public class PackageManagerException extends ManagerException {

    public PackageManagerException(String errorMessage) {
        super(errorMessage);
    }
    
    /**
     * This method obtain the message error when the command fail and there is a 
     * packete involved.
     * 
     * @param method The method who exec: Install, Update or Remove.
     * @param packageName The package who manage.
     * @return The message error.
     */
    public static String getMessageFailPackage(String method, String packageName){
        return String.format("Error when %s the package %s", method, packageName);
    }
    
    /**
     * This method obtain the message error when the command fail and and 
     * applies to all packages.
     * 
     * @param method The method who exec: Update or Upgrade.
     * @return The message error.
     */
    public static String getMessageFailAllPackages(String method){
        return String.format("Error when %s", method);
    }
}
