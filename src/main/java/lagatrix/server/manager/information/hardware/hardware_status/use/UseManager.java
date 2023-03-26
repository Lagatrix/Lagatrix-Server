package lagatrix.server.manager.information.hardware.hardware_status.use;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.hardware.status.UseException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class is used to onglobar the methods to obtain the use of any 
 * device.
 *
 * @author javier
 * @since 1.0
 */
public abstract class UseManager {
    protected CommandExecutor executor;
    
    /**
     * This method obtain the use of devices like CPU or RAM.
     * 
     * @return The percentaje the use.
     * @throws UseException If can't obtain the use.
     */
    public abstract float obtainUse() throws UseException;
    
    /**
     * This methos execute the commands to obtain devices uses.
     * 
     * @param command The command to exec.
     * @param device The device.
     * @return The percentaje the use.
     * @throws UseException If can't obtain the use.
     */
    protected final float executeUseCommand(String command, String device) throws UseException {
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new UseException(device);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new UseException(device);
        }
        
        return Float.parseFloat(response.getFirstLine());
    }
}