package lagatrix.manager.deletion.process;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.process.ProcessException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class kill an process.
 *
 * @author javierfh03
 * @since 0.1
 */
public class ProcessDeletion {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public ProcessDeletion(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method kill a process.
     * 
     * @param PID The PID of process to kill.
     * @throws ProcessException If a problem occurs with the execution of the 
     * command.
     */
    public void killProcess(int PID) throws ProcessException {
        String command = String.format("kill %d", PID);
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new ProcessException(ProcessException.getMessage(
                    this.getClass(), ActionsEnum.DELETE, ex.getMessage()));
        }
    }
}
