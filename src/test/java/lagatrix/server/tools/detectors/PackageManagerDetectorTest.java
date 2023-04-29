package lagatrix.server.tools.detectors;

import lagatrix.tools.detectors.PackageManagerDetector;
import lagatrix.entities.components.PackageManagerComponents;
import lagatrix.exceptions.NotValidFamilyException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * This class test the package manager detector.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class PackageManagerDetectorTest {
    @Test
    public void getAPTTest() throws NotValidFamilyException{
        assertEquals(PackageManagerComponents.APT, 
                PackageManagerDetector.detectPackageManager("debian"));
    }
    
    @Test
    public void getZypperTest() throws NotValidFamilyException{
        assertEquals(PackageManagerComponents.ZYPPER, 
                PackageManagerDetector.detectPackageManager("opensuse"));
    }
    
    @Test
    public void getPacmanTest() throws NotValidFamilyException{
        assertEquals(PackageManagerComponents.PACMAN, 
                PackageManagerDetector.detectPackageManager("arch"));
    }
    
    @Test
    public void getYumTest() throws NotValidFamilyException{
        assertEquals(PackageManagerComponents.YUM, 
                PackageManagerDetector.detectPackageManager("rhel centos fedora"));
        assertEquals(PackageManagerComponents.YUM, 
                PackageManagerDetector.detectPackageManager("fedora"));
        assertEquals(PackageManagerComponents.YUM, 
                PackageManagerDetector.detectPackageManager("rhel fedora"));
    }
}
