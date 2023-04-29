package lagatrix.manager;

import lagatrix.entities.dto.hardware.CPU;
import lagatrix.exceptions.manager.hardware.CPUException;
import lagatrix.exceptions.manager.hardware.status.TemperatureException;
import lagatrix.exceptions.manager.hardware.status.UseException;
import lagatrix.manager.information.hardware.hardware_status.temperature.TemperatureManager;
import lagatrix.manager.information.hardware.hardware_status.use.UseManager;
import lagatrix.manager.information.hardware.CPUInfo;
import lagatrix.tools.command.CommandExecutor;
import lagatrix.tools.detectors.DevicesEnum;
import lagatrix.tools.detectors.RaspberryDetector;
import lagatrix.tools.detectors.TemperatureDeviceDetector;
import lagatrix.tools.detectors.UseDeviceDetector;

/**
 * This class obtain uses, information and temperature of CPU in Linux.
 *
 * @author javierfh03
 * @since 0.1
 */
public class CPUManager {

    private CPUInfo information;
    private TemperatureManager temperature;
    private UseManager use;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the class.
     */
    public CPUManager(CommandExecutor executor) {
        boolean isRaspberry = RaspberryDetector.isRaspberry(executor);
                
        this.information = new CPUInfo(executor);
        
        this.temperature = new TemperatureDeviceDetector(DevicesEnum.CPU, executor, isRaspberry).getManager();
        this.use = new UseDeviceDetector(DevicesEnum.CPU, executor).getManager();
    }

    /**
     * This method obtain the CPU of the system.
     *
     * @return The CPU info in entity.
     * @throws CPUException If you can't get important information from CPU.
     */
    public CPU obtainCPU() throws CPUException {
        CPU cpu = new CPU();

        // Important information.
        cpu.setModel(information.obtainModel());
        cpu.setCores(information.obtainCores());
        cpu.setThreads(information.obtainThreads() * cpu.getCores());
        
        // Not important information.
        cpu.setMaxSpeed(information.obtainMaxSpeed());
        cpu.setMinSpeed(information.obtainMinSpeed());
        cpu.setCacheMemory(information.obtainCacheMemory());

        return cpu;
    }

    /**
     * This method obtain the temperature of the CPU.
     *
     * @return The temperature in celsius.
     * @throws TemperatureException If can't obtain the temperature.
     */
    public float obtainTemperature() throws TemperatureException {
        return temperature.obtainTemperature();
    }

    /**
     * This method obtain the use of the CPU.
     *
     * @return The percentaje of use,
     * @throws UseException If can't obtain the use.
     */
    public float obtainUse() throws UseException {
        return use.obtainUse();
    }

}
