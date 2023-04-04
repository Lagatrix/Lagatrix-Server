package lagatrix.server.entities.dto.os;

import java.io.Serializable;
import lagatrix.server.entities.components.PackageManagerComponents;

/**
 * This entity represents a class of data of Linux OS.
 *
 * @author javierfh03
 * @since 0.1
 */
public class OSInformation implements Serializable {
    
    private String distribution, distributionName, distributionFamily, kernel, hostname;
    private int distributionVersion;
    private PackageManagerComponents packageManager;

    public OSInformation() {
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getDistributionName() {
        return distributionName;
    }

    public void setDistributionName(String distributionName) {
        this.distributionName = distributionName;
    }

    public String getDistributionFamily() {
        return distributionFamily;
    }

    public void setDistributionFamily(String distributionFamily) {
        this.distributionFamily = distributionFamily;
    }

    public String getKernel() {
        return kernel;
    }

    public void setKernel(String kernel) {
        this.kernel = kernel;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getDistributionVersion() {
        return distributionVersion;
    }

    public void setDistributionVersion(int distributionVersion) {
        this.distributionVersion = distributionVersion;
    }

    public PackageManagerComponents getPackageManager() {
        return packageManager;
    }

    public void setPackageManager(PackageManagerComponents packageManager) {
        this.packageManager = packageManager;
    }

    @Override
    public String toString() {
        return "OSInformation{" + "distribution=" + distribution + ", distributionName=" + distributionName + ", distributionFamily=" + distributionFamily + ", kernel=" + kernel + ", hostname=" + hostname + ", distributionVersion=" + distributionVersion + ", packageManager=" + packageManager + '}';
    }
}
