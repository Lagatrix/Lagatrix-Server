package lagatrix.server.manager;

import lagatrix.server.entities.dto.hardware.RAM;
import lagatrix.server.exceptions.manager.hardware.RAMException;
import lagatrix.server.exceptions.manager.hardware.status.UseException;
import lagatrix.server.manager.information.hardware.hardware_status.use.UseManager;
import lagatrix.server.manager.information.hardware.RAMInfo;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.detectors.DevicesEnum;
import lagatrix.server.tools.detectors.UseDeviceDetector;

/**
 * This class obtain uses and information of RAM in Linux.
 *
 * @author javierfh03
 * @since 0.1
 */
public class RAMManager {
    
    private RAMInfo information;
    private UseManager use;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the class.
     */
    public RAMManager(CommandExecutor executor) {
        this.information = new RAMInfo(executor);
        this.use = new UseDeviceDetector(DevicesEnum.RAM, executor).getManager();
    }

    /**
     * This method obtain the RAM of the system.
     *
     * @return The RAM info in entity.
     * @throws RAMException If you can't get some information from RAM.
     */
    public RAM obtainRAM() throws RAMException {
        RAM ram = new RAM();
        
        ram.setCapacity(information.obtainCapacity());
        ram.setUnitCapacity(information.obtainUnitCapacity());
        
        return ram;
    }

    /**
     * This method obtain the use of the RAM.
     *
     * @return The percentaje of use,
     * @throws UseException If can't obtain the use.
     */
    public float obtainUse() throws UseException {
        return use.obtainUse();
    }
}
