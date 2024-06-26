package lagatrix.tools.detectors;

import lagatrix.manager.information.hardware.hardware_status.use.UseCPU;
import lagatrix.manager.information.hardware.hardware_status.use.UseManager;
import lagatrix.manager.information.hardware.hardware_status.use.UseRAM;
import lagatrix.tools.command.CommandExecutor;
import static lagatrix.tools.detectors.DevicesEnum.RAM;

/**
 * This class obtains the use manager of device.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class UseDeviceDetector {
    private UseManager manager;
    private DevicesEnum device;
    private CommandExecutor executor;

    
    /**
     * The constructor of the class.
     * 
     * @param device The device who obtain the manager.
     * @param executor An command executor class.
     */
    public UseDeviceDetector(DevicesEnum device, CommandExecutor executor) {
        this.device = device;
        this.executor = executor;
        this.manager = obtainTemperatureManager();
    }

    /**
     * This method get the manager.
     * 
     * @return The manager.
     */
    public UseManager getManager() {
        return manager;
    }
    
    /**
     * Find the use manager.
     * 
     * @return The manager, return null if not find it.
     */
    private UseManager obtainTemperatureManager(){
        switch (device) {
            case CPU:
                return new UseCPU(executor);
            case RAM:
                return new UseRAM(executor);
            case GPU:
                throw new UnsupportedOperationException("Not use supported");
        }
        
        return null;
    }
}
