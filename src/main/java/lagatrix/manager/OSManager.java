package lagatrix.manager;

import lagatrix.entities.dto.os.OSInformation;
import lagatrix.exceptions.NotValidFamilyException;
import lagatrix.exceptions.manager.os.OSException;
import lagatrix.manager.information.os.DistributionInfo;
import lagatrix.manager.information.os.UnameInfo;
import lagatrix.tools.command.CommandExecutor;
import lagatrix.tools.detectors.PackageManagerDetector;

/**
 * This class obtain information of the OS of Linux.
 *
 * @author javierfh03
 * @since 0.1
 */
public class OSManager {

    private DistributionInfo informationDistribution;
    private UnameInfo informationUname;
    
    /**
     * The constructor of the class
     *
     * @param executor The executor of the class.
     */
    public OSManager(CommandExecutor executor) {
        this.informationDistribution = new DistributionInfo(executor);
        this.informationUname = new UnameInfo(executor);
    }

    /**
     * This method obtain the information of OS of the system.
     *
     * @return The OS information entity.
     * @throws OSException If you can't get some information from OS.
     * @throws NotValidFamilyException If the family of OS is not supported.
     */
    public OSInformation obtainOSInformation() throws OSException, NotValidFamilyException {
        OSInformation os = new OSInformation();

        os.setDistribution(informationDistribution.obtainDistribution());
        os.setDistributionFamily(informationDistribution.obtainFamilyDistribution());
        os.setDistributionName(informationDistribution.obtainDistributionName());
        os.setHostname(informationUname.obtainHostname());
        os.setKernel(informationUname.obtainKernel());
        os.setPackageManager(PackageManagerDetector.detectPackageManager(os.getDistributionFamily()));
        
        // Unrelevant information.
        os.setDistributionVersion(informationDistribution.obtainDistributionVersion());

        return os;
    }
}
