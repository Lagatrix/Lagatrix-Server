package lagatrix.server.manager;

import lagatrix.server.entities.dto.os.OSInformation;
import lagatrix.server.exceptions.NotValidFamilyException;
import lagatrix.server.exceptions.manager.os.OSException;
import lagatrix.server.manager.information.os.DistributionInfo;
import lagatrix.server.manager.information.os.UnameInfo;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.detectors.PackageManagerDetector;

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
        os.setDistributionVersion(informationDistribution.obtainDistributionVersion());
        os.setHostname(informationUname.obtainHostname());
        os.setKernel(informationUname.obtainKernel());
        os.setPackageManager(PackageManagerDetector.detectPackageManager(os.getDistributionFamily()));

        return os;
    }
}
