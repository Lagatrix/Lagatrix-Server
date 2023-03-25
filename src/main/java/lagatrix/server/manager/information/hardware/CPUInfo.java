package lagatrix.server.manager.information.hardware;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.hardware.CPUException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain the information of a CPU in Linux with lscpu command.
 * 
 * @author javier
 * @since 1.0
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
        return executeCommand("Model").getFirstLine();
    }
    
    /**
     * This method obtain the amount of cores of the CPU.
     * 
     * @return The amount of cores.
     * @throws CPUException If a problem occurs with the execution of the 
     * command.
     */
    public int obtainCores() throws CPUException{
        return Integer.parseInt(executeCommand("^CPU(s):").getFirstLine());
    }
    
    /**
     * This method obtain the L3 cache memory.
     * 
     * @return The capacity of L3 memory.
     * @throws CPUException If a problem occurs with the execution of the 
     * command.
     */
    public int obtainCacheMemory() throws CPUException{
        return Integer.parseInt(executeCommand("L3").getFirstLine());
    }
    
    /**
     * This method obtain the amount of threads of the CPU.
     * 
     * @return The amount of threads.
     * @throws CPUException If a problem occurs with the execution of the 
     * command. 
     */
    public int obtainThreads() throws CPUException{
        return Integer.parseInt(executeCommand("Thread").getFirstLine());
    }
    
    /**
     * This method obtain the min speed of CPU in Mhz
     * 
     * @return The min speed in Mhz.
     * @throws CPUException If a problem occurs with the execution of the 
     * command.
     */
    public float obtainMinSpeed() throws CPUException{
        return Float.parseFloat(executeCommand("CPU min").getFirstLine());
    }
    
    /**
     * This method obtain the max speed of CPU in Mhz
     * 
     * @return The max speed in Mhz.
     * @throws CPUException If a problem occurs with the execution of the 
     * command.
     */
    public float obtainMaxSpeed() throws CPUException{
        return Float.parseFloat(executeCommand("CPU max").getFirstLine());
    }
    
    /**
     * This method exec the lscpu command. Gets the component you want to get.
     * 
     * @param component The component who want to get.
     * @return Th response of command.
     * @throws CPUException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(String component) throws CPUException {
        String command = String.format("LC_ALL=C lscpu | grep '%s' | awk -F : '{print $2}' | xargs", component);
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new CPUException(component);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new CPUException(component);
        }
        
        return response;
    }
}
