package lagatrix.server.manager.deletion.process;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.process.ProcessException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class kill an process.
 *
 * @author javier
 * @since 1.0
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
            throw new ProcessException("Kill process");
        }
    }
}
