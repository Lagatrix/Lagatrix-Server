package lagatrix.server.entities.dto.os;

/**
 * This entity represents a class of data of Linux OS.
 *
 * @author javier
 * @since 1.0
 */
public class OSInformation {
    private String distribution, distributionName, distributionFamily, kernel, hostname;
    private int distributionVersion;

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

    @Override
    public String toString() {
        return "OSInformation{" + "distribution=" + distribution + ", distributionName=" + distributionName + ", distributionFamily=" + distributionFamily + ", kernel=" + kernel + ", hostname=" + hostname + ", distributionVersion=" + distributionVersion + '}';
    }
}
