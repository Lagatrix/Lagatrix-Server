package lagatrix.server.tools.hawrdare_status;

import lagatrix.server.manager.information.hardware.hardware_status.temperature.TemperatureManager;
import lagatrix.server.manager.information.hardware.hardware_status.temperature.cpu.NormalCPUTemperature;
import lagatrix.server.manager.information.hardware.hardware_status.temperature.cpu.RaspberryPiTemperature;
import lagatrix.server.tools.command.CommandExecutor;
import static lagatrix.server.tools.hawrdare_status.DevicesEnum.CPU;
import static lagatrix.server.tools.hawrdare_status.DevicesEnum.DISK;
import static lagatrix.server.tools.hawrdare_status.DevicesEnum.GPU;

/**
 * This class obtains the temperature manager of device.
 *
 * @author javier
 * @since 1.0
 */
public class TemperatureDeviceDetector {
    private TemperatureManager manager;
    private DevicesEnum device;
    private CommandExecutor executor;

    /**
     * The constructor of the class, detects the device andd if is raspberry and
     * find the manager.
     * 
     * @param device The device who obtain the manager.
     * @param executor An command executor class.
     * @param isRaspberry If the device is an raspberry.
     */
    public TemperatureDeviceDetector(DevicesEnum device, CommandExecutor executor, boolean isRaspberry) {
        this.device = device;
        this.executor = executor;
        this.manager = obtainTemperatureManager(isRaspberry);
    }

    /**
     * This method get the manager.
     * 
     * @return The manager.
     */
    public TemperatureManager getManager() {
        return manager;
    }
    
    /**
     * Find the temperatura manager.
     * 
     * @param isRaspberry If is an raspberry device.
     * @return The manager.
     */
    private TemperatureManager obtainTemperatureManager(boolean isRaspberry){
        return (isRaspberry) ? obtainRaspberryTemperatureManager() : obtainDefaultTemperatureManager();
    }
    
    /**
     * Obtain the temperature manager of default devices.
     * 
     * @return The manager, return null if not find it.
     */
    private TemperatureManager obtainDefaultTemperatureManager(){
        switch (device){
            case GPU:
                throw new UnsupportedOperationException("Not temperature supported");
            case CPU:
                return new NormalCPUTemperature(this.executor);
            case DISK:
                throw new UnsupportedOperationException("Not temperature supported");
        }
        
        return null;
    }
    
    /**
     * Obtain the temperature manager of raspberry devices.
     * 
     * @return The manager.
     */
    private TemperatureManager obtainRaspberryTemperatureManager(){
        switch (device){
            case GPU:
                throw new UnsupportedOperationException("Not temperature supported");
            case CPU:
                return new RaspberryPiTemperature(this.executor);
            case DISK:
                throw new UnsupportedOperationException("Not temperature supported");
        }
        
        return null;
    }
}
