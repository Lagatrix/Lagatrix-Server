package lagatrix.tools.detectors;

import java.util.regex.Pattern;
import lagatrix.entities.components.PackageManagerComponents;
import lagatrix.exceptions.NotValidFamilyException;

/**
 * This class determines the package manager of the system.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class PackageManagerDetector {
    
    /**
     * This method determine the package manager of distribution.
     * 
     * @param distributionFamily The family of distribution.
     * @return The package manager.
     * @throws NotValidFamilyException If the family is not suported. 
     */
    public static PackageManagerComponents detectPackageManager(String distributionFamily) throws NotValidFamilyException{
        Pattern patternYum = Pattern.compile(".*(rhel|centos|fedora).*");
        Pattern patternApt = Pattern.compile(".*debian.*");
        Pattern patternPacman = Pattern.compile(".*arch.*");
        Pattern patternZypper = Pattern.compile(".*opensuse.*");
        
        if (patternApt.matcher(distributionFamily).find()) {
            return PackageManagerComponents.APT;
        } else if (patternYum.matcher(distributionFamily).find()) {
            return PackageManagerComponents.YUM;
        } else if (patternZypper.matcher(distributionFamily).find()) {
            return PackageManagerComponents.ZYPPER;
        } else if (patternPacman.matcher(distributionFamily).find()) {
            return PackageManagerComponents.PACMAN;
        }
        
        throw new NotValidFamilyException(distributionFamily);
    }
}
