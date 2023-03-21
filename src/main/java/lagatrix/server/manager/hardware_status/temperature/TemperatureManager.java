package lagatrix.server.manager.hardware_status.temperature;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.TemperatureException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class is used to onglobar the methods to obtain the temperature of any 
 * device.
 * 
 * @author javier
 * @since 1.0
 */
public abstract class TemperatureManager {
    protected CommandExecutor executor;
    
    /**
     * This method obtain the temperature of devices who they have a temperature 
     * detector.
     * 
     * @return The temperature in celsius.
     * @throws TemperatureException If can't obtain the temperature.
     */
    public abstract float obtainTemperature() throws TemperatureException;
    
    protected final float executeTemperatureCommand(String command) throws TemperatureException {
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new TemperatureException("Can't obtain temperature.");
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new TemperatureException("Can't obtain temperature.");
        }
        
        return Float.parseFloat(response.getFirstLine());
    }
}
