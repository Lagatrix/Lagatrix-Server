package lagatrix.server.manager.information.os;

import lagatrix.server.entities.devices.components.OSComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.OSException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain information of os with uname command.
 *
 * @author javier
 * @since 1.0
 */
public class UnameInfo {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public UnameInfo(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method obtain the hostname of the machine.
     * 
     * @return The hostname.
     * @throws OSException If can't get the hostname.
     */
    public String obtainHostname() throws OSException{
        return executeCommand(OSComponents.HOSTNAME).getFirstLine();
    }
    
    /**
     * This method obtain the version of kernel of Linux of machine.
     * 
     * @return The version of kernel of Linux.
     * @throws OSException If can't get the kernel.
     */
    public String obtainKernel() throws OSException{
        return executeCommand(OSComponents.KERNEL).getFirstLine();
    }
    
    /**
     * This method exec the uname command. Gets the component you want to get.
     * 
     * @param component The component who want to get.
     * @return The response of command.
     * @throws OSException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(OSComponents component) throws OSException {
        String command = String.format("uname %s", component.getValue());
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new OSException(component.getName());
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new OSException(component.getName());
        }
        
        return response;
    }
}
