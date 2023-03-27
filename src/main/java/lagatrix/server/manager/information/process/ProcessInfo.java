package lagatrix.server.manager.information.process;

import lagatrix.server.entities.components.ProcessComponent;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.process.ProcessException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain the information of process in Linux with ps command.
 *
 * @author javier
 * @since 1.0
 */
public class ProcessInfo {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public ProcessInfo(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method obtain the PID of process.
     * 
     * @param numProcess The number of process who get the PID.
     * @return
     * @throws ProcessException If a problem occurs with the execution of the 
     * command.
     */
    public int obtainPID(int numProcess) throws ProcessException {
        return Integer.parseInt(executeCommand(numProcess, ProcessComponent.PID).getFirstLine());
    }
    
    /**
     * This method obtain the command of process.
     * 
     * @param numProcess The number of process who get the command.
     * @return The command.
     * @throws ProcessException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainCommand(int numProcess) throws ProcessException {
        return executeCommand(numProcess, ProcessComponent.COMMAND).getFirstLine();
    }
    
    /**
     * This method obtain the user who exec of process.
     * 
     * @param numProcess The number of process who get the user.
     * @return The username of user.
     * @throws ProcessException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainUser(int numProcess) throws ProcessException {
        return executeCommand(numProcess, ProcessComponent.USER).getFirstLine();
    }
    
    /**
     * This method obtain the use of CPU of process.
     * 
     * @param numProcess The number of process who get the use of CPU.
     * @return The use of CPU.
     * @throws ProcessException If a problem occurs with the execution of the 
     * command.
     */
    public float obtainUseCPU(int numProcess) throws ProcessException {
        return Float.parseFloat(executeCommand(numProcess, ProcessComponent.USE_CPU).getFirstLine());
    }
    
    /**
     * This method obtain the use of memory of process.
     * 
     * @param numProcess The number of process who get the use of memory.
     * @return The use of memory.
     * @throws ProcessException If a problem occurs with the execution of the 
     * command.
     */
    public float obtainUseRAM(int numProcess) throws ProcessException {
        return Float.parseFloat(executeCommand(numProcess, ProcessComponent.USE_MEM).getFirstLine());
    }
    
    /**
     * This method exec the ps aux command. Gets the component you want to get.
     * 
     * @param numPartition The num of process who get the information.
     * @param component The component who want to get.
     * @return The response of command.
     * @throws PartitionException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(int numProcess, ProcessComponent component) throws ProcessException {
        String command = String.format("ps aux --no-headers | awk '{print $%s}' | sed -n %dp", component.getValue(), numProcess);
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new ProcessException(component.name());
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new ProcessException(component.getName());
        }
        
        return response;
    }
}
