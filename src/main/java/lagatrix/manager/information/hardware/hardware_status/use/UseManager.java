package lagatrix.manager.information.hardware.hardware_status.use;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.hardware.status.UseException;
import lagatrix.tools.command.CommandExecutor;
import lagatrix.tools.command.CommandResponse;

/**
 * This class is used to onglobar the methods to obtain the use of any 
 * device.
 *
 * @author javierfh03
 * @since 0.1
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
     * @return The percentaje the use.
     * @throws UseException If can't obtain the use.
     */
    protected final float executeUseCommand(String command) throws UseException {
        CommandResponse response = null;
        String msgError = UseException.getMessage(this.getClass(), ActionsEnum.GET);
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new UseException(msgError);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new UseException(msgError);
        }
        
        return Float.parseFloat(response.getFirstLine());
    }
}
