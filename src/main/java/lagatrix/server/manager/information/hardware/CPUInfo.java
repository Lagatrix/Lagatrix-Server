package lagatrix.server.manager.information.hardware;

import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.components.CPUComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.hardware.CPUException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain the information of a CPU in Linux with lscpu command.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class CPUInfo {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class.
     * 
     * @param executor The command executor.
     */
    public CPUInfo(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method obtain the model of the CPU.
     * 
     * @return Model of CPU.
     * @throws CPUException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainModel() throws CPUException{
        return executeCommand(CPUComponents.MODEL).getFirstLine();
    }
    
    /**
     * This method obtain the amount of cores of the CPU.
     * 
     * @return The amount of cores.
     * @throws CPUException If a problem occurs with the execution of the 
     * command.
     */
    public int obtainCores() throws CPUException{
        return Integer.parseInt(executeCommand(CPUComponents.CORES).getFirstLine());
    }
    
    /**
     * This method obtain the amount of threads of the CPU per core.
     * 
     * @return The amount of threads per core.
     * @throws CPUException If a problem occurs with the execution of the 
     * command. 
     */
    public int obtainThreads() throws CPUException{
        return Integer.parseInt(executeCommand(CPUComponents.THREADS).getFirstLine());
    }
    
    /**
     * This method obtain the L3 cache memory, if it cannot be obtained, 
     * 0 will be returned.
     * 
     * @return The capacity of L3 memory.
     */
    public String obtainCacheMemory() {
        try {
            return executeCommand(CPUComponents.L3).getFirstLine();
        } catch (CPUException ex) {
            return "unknown";
        }
    }
    
    /**
     * This method obtain the min speed of CPU in Mhz, if it cannot be obtained, 
     * 0 will be returned.
     * 
     * @return The min speed in Mhz.
     */
    public float obtainMinSpeed() {
        try {
            return Float.parseFloat(executeCommand(CPUComponents.MIN_SPEED).getFirstLine());
        } catch (CPUException ex) {
            return 0;
        }
    }
    
    /**
     * This method obtain the max speed of CPU in Mhz, if it cannot be obtained, 
     * 0 will be returned.
     * 
     * @return The max speed in Mhz.
     */
    public float obtainMaxSpeed() {
        try {
            return Float.parseFloat(executeCommand(CPUComponents.MAX_SPEED).getFirstLine());
        } catch (CPUException ex) {
            return 0;
        }
    }
    
    /**
     * This method exec the lscpu command. Gets the component you want to get.
     * 
     * @param component The component who want to get.
     * @return The response of command.
     * @throws CPUException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(CPUComponents component) throws CPUException {
        String command = String.format("LC_ALL=C lscpu | grep '%s' | awk -F : '{print $2}' | xargs", 
                component.getValue());
        String msgError = CPUException.getMessage(this.getClass(), 
                component.getName(), ActionsEnum.GET);
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new CPUException(msgError);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new CPUException(msgError);
        }
        
        return response;
    }
}
