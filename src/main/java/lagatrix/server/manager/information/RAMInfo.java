package lagatrix.server.manager.information;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.RAMException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain the information of a RAM in Linux with lsmem command.
 *
 * @author javier
 * @since 1.0
 */
public class RAMInfo {
    private CommandExecutor executor;

    /**
     * The constructor of the class.
     * 
     * @param executor The command executor.
     */
    public RAMInfo(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method obtain the total memory of the RAM. It return String because
     * it get the unity of storage.
     * 
     * @return Size of RAM.
     * @throws RAMException If a problem occurs with the execution of the 
     * command.
     */
    public int obtainMemory() throws RAMException {
        return Integer.parseInt(executeCommand("Total online memory").getFirstLine());
    }
    
    /**
     * This method obtain the total memory of the RAM. It return String because
     * it get the unity of storage.
     * 
     * @return Unity of storage of RAM.
     * @throws RAMException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainMemoryUnit() throws RAMException {
        return executeCommand("UnitStorage").getFirstLine();
    }
    
    /**
     * This method exec the lsmem command. Gets the component you want to get.
     * 
     * @param component The component who want to get.
     * @return The response of command.
     * @throws RAMException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(String component) throws RAMException {
        CommandResponse response = null;
        String filter = (!component.equals("UnitStorage")) ? "'[A-Z]' '{print $1}'" : "'[0-9].' '{print $2}'";
        
        try {
            response = executor.executeCommand(String.format("LC_ALL=C lsmem | grep 'Total online memory' "
                    + "| awk -F : '{print $2}' | awk -F %s | xargs", filter)); 
        } catch (CommandException ex) {
            throw new RAMException(component);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new RAMException(component);
        }
        
        return response;
    }
}
