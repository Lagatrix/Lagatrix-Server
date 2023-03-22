package lagatrix.server.manager;

import lagatrix.server.entities.devices.GPU;
import lagatrix.server.exceptions.manager.GPUException;
import lagatrix.server.manager.information.GPUInfo;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class obtain uses, information of GPU in Linux.
 *
 * @author javier
 * @since 1.0
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
