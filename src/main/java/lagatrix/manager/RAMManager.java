package lagatrix.manager;

import lagatrix.entities.dto.hardware.RAM;
import lagatrix.exceptions.manager.hardware.RAMException;
import lagatrix.exceptions.manager.hardware.status.UseException;
import lagatrix.manager.information.hardware.hardware_status.use.UseManager;
import lagatrix.manager.information.hardware.RAMInfo;
import lagatrix.tools.command.CommandExecutor;
import lagatrix.tools.detectors.DevicesEnum;
import lagatrix.tools.detectors.UseDeviceDetector;

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
