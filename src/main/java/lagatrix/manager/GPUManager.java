package lagatrix.manager;

import lagatrix.entities.dto.hardware.GPU;
import lagatrix.exceptions.manager.hardware.GPUException;
import lagatrix.manager.information.hardware.GPUInfo;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class obtain information of GPU in Linux.
 *
 * @author javierfh03
 * @since 0.1
 */
public class GPUManager {
    
    private GPUInfo information;
    
    /**
     * The constructor of the class
     *
     * @param executor The executor of the class.
     */
    public GPUManager(CommandExecutor executor) {
        this.information = new GPUInfo(executor);
    }

    /**
     * This method obtain the GPU of the system.
     *
     * @return The GPU info in entity.
     * @throws GPUException If you can't get some information from GPU.
     */
    public GPU obtainGPU() throws GPUException {
        GPU gpu = new GPU();

        gpu.setModel(information.obtainModel());
        gpu.setVendor(information.obtainVendor());

        return gpu;
    }
}
