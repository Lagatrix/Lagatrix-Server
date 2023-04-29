package lagatrix.manager.information.hardware.hardware_status.temperature.cpu;

import lagatrix.exceptions.manager.hardware.status.TemperatureException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class obtain the temperature in default machines with lm-sensors.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class NormalCPUTemperature extends CPUTemperature{

    public NormalCPUTemperature(CommandExecutor executor) {
        super.executor = executor;
    }

    @Override
    public float obtainTemperature() throws TemperatureException {
        return super.executeTemperatureCommand("sensors | grep Core |  awk '{s+=$3; i++}END{print s/i}'");
    }

}
