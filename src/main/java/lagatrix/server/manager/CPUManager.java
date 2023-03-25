package lagatrix.server.manager;

import lagatrix.server.entities.dto.hardware.CPU;
import lagatrix.server.exceptions.manager.hardware.CPUException;
import lagatrix.server.exceptions.manager.TemperatureException;
import lagatrix.server.exceptions.manager.UseException;
import lagatrix.server.manager.hardware_status.temperature.TemperatureManager;
import lagatrix.server.manager.hardware_status.use.UseManager;
import lagatrix.server.manager.information.hardware.CPUInfo;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.hawrdare_status.DevicesEnum;
import lagatrix.server.tools.hawrdare_status.TemperatureDeviceDetector;
import lagatrix.server.tools.hawrdare_status.UseDeviceDetector;

/**
 * This class obtain uses, information and temperature of CPU in Linux.
 *
 * @author javier
 * @since 1.0
 */
public class CPUManager {

    private CPUInfo information;
    private TemperatureManager temperature;
    private UseManager use;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the class.
     * @param isRaspberry If the machine is raspberry.
     */
    public CPUManager(CommandExecutor executor, boolean isRaspberry) {
        this.information = new CPUInfo(executor);
        this.temperature = new TemperatureDeviceDetector(DevicesEnum.CPU, executor, isRaspberry).getManager();
        this.use = new UseDeviceDetector(DevicesEnum.CPU, executor).getManager();
    }

    /**
     * This method obtain the CPU of the system.
     *
     * @return The CPU info in entity.
     * @throws CPUException If you can't get some information from CPU.
     */
    public CPU obtainCPU() throws CPUException {
        CPU cpu = new CPU();

        cpu.setModel(information.obtainModel());
        cpu.setCores(information.obtainCores());
        cpu.setMaxSpeed(information.obtainMaxSpeed());
        cpu.setMinSpeed(information.obtainMinSpeed());
        cpu.setThreads(information.obtainThreads() * cpu.getCores());

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
