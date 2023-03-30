package lagatrix.server.manager.information.hardware;

import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.components.RAMComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.hardware.RAMException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain the information of a RAM in Linux with lsmem command.
 *
 * @author javierfh03
 * @since 0.1
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
     * This method obtain the capacity of the RAM. It return String because
     * it get the unity of storage.
     * 
     * @return Size of RAM.
     * @throws RAMException If a problem occurs with the execution of the 
     * command.
     */
    public int obtainCapacity() throws RAMException {
        return Integer.parseInt(executeCommand(RAMComponents.CAPACITY).getFirstLine());
    }
    
    /**
     * This method obtain the unit capacity of the RAM. It return String because
     * it get the unity of storage.
     * 
     * @return Unity of storage of RAM.
     * @throws RAMException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainUnitCapacity() throws RAMException {
        return executeCommand(RAMComponents.UNIT_CAPACITY).getFirstLine();
    }
    
    /**
     * This method exec the lsmem command. Gets the component you want to get.
     * 
     * @param component The component who want to get.
     * @return The response of command.
     * @throws RAMException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(RAMComponents component) throws RAMException {
        CommandResponse response = null;
        String command = String.format("LC_ALL=C lsmem | grep 'Total online memory' "
                    + "| awk -F : '{print $2}' | awk -F %s | xargs", component.getFilter());
        String msgError = RAMException.getMessage(this.getClass(), 
                component.getName(), ActionsEnum.GET);
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new RAMException(msgError);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new RAMException(msgError);
        }
        
        return response;
    }
}
