package lagatrix.manager.information.hardware.hardware_status.temperature.cpu;

import lagatrix.exceptions.manager.hardware.status.TemperatureException;
import lagatrix.manager.information.hardware.hardware_status.temperature.TemperatureManager;

/**
 * This class is used to onglobar the methods to obtain the temperature of any 
 * CPU.
 * 
 * @author javierfh03
 * @since 0.1
 */
public abstract class CPUTemperature extends TemperatureManager {
    
    @Override
    public abstract float obtainTemperature() throws TemperatureException;

}
