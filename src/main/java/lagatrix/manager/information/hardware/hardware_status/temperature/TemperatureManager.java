package lagatrix.manager.information.hardware.hardware_status.temperature;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.hardware.status.TemperatureException;
import lagatrix.tools.command.CommandExecutor;
import lagatrix.tools.command.CommandResponse;

/**
 * This class is used to onglobar the methods to obtain the temperature of any 
 * device.
 * 
 * @author javierfh03
 * @since 0.1
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
    
    /**
     * This methos execute the commands to obtain devices temperatures.
     * 
     * @param command The command to exec.
     * @return The percentaje the use.
     * @throws TemperatureException If can't obtain the temperature.
     */
    protected final float executeTemperatureCommand(String command) throws TemperatureException {
        CommandResponse response = null;
        String msgError = TemperatureException.getMessage(this.getClass(), ActionsEnum.GET);
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new TemperatureException(msgError);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new TemperatureException(msgError);
        }
        
        return Float.parseFloat(response.getFirstLine());
    }
}
