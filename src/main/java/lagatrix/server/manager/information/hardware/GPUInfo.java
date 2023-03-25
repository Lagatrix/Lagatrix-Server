package lagatrix.server.manager.information.hardware;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.hardware.GPUException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain the information of a GPU in Linux with lspci command.
 *
 * @author javier
 * @since 1.0
 */
public class GPUInfo {
    
private CommandExecutor executor;

    /**
     * The constructor of the class.
     * 
     * @param executor The command executor.
     */
    public GPUInfo(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method obtain the model of the GPU.
     * 
     * @return Model of GPU.
     * @throws GPUException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainModel() throws GPUException{
        return executeCommand("Device").getFirstLine();
    }
    
    /**
     * This method obtain the vendor of the GPU.
     * 
     * @return Vendor of GPU.
     * @throws GPUException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainVendor() throws GPUException{
        return executeCommand("Vendor").getFirstLine();
    }
    
    /**
     * This method exec the lspci command. Gets the component you want to get.
     * 
     * @param component The component who want to get.
     * @return The response of command.
     * @throws GPUException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(String component) throws GPUException {
        String command = String.format("lspci -vmm | grep '\\sVGA' -A2 | grep '%s' | awk -F : '{print $2}' | xargs ", component);
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new GPUException(component);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new GPUException(component);
        }
        
        return response;
    }
}
