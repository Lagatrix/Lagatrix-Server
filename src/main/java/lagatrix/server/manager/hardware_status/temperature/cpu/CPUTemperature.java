package lagatrix.server.manager.hardware_status.temperature.cpu;

import lagatrix.server.exceptions.manager.TemperatureException;
import lagatrix.server.manager.hardware_status.temperature.TemperatureManager;

/**
 * This class is used to onglobar the methods to obtain the temperature of any 
 * CPU.
 * 
 * @author javier
 * @since 1.0
 */
public abstract class CPUTemperature extends TemperatureManager {
    
    @Override
    public abstract float obtainTemperature() throws TemperatureException;

}
