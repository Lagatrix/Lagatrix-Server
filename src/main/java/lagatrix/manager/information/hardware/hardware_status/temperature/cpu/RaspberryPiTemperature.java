package lagatrix.manager.information.hardware.hardware_status.temperature.cpu;

import lagatrix.exceptions.manager.hardware.status.TemperatureException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class obtain the temperature in raspberry pi.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class RaspberryPiTemperature extends CPUTemperature {

    public RaspberryPiTemperature(CommandExecutor executor) {
        super.executor = executor;
    }
    
    @Override
    public float obtainTemperature() throws TemperatureException {
        return super.executeTemperatureCommand("vcgencmd measure_temp | awk -F = '{print $2}' | awk -F \\' '{print $1}'");
    }
    
}
