package lagatrix.entities.dto.hardware;

import java.io.Serializable;

/**
 * This entity reperents a GPU.
 *
 * @author javierfh03
 * @since 0.1
 */
public class GPU implements Serializable {
    
    private String model, vendor;

    public GPU() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "GPU{" + "model=" + model + ", vendor=" + vendor + '}';
    }
}
