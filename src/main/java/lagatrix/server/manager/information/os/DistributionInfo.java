package lagatrix.server.manager.information.os;

import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.components.OSComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.os.OSException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain trivial information of distribution of OS.
 *
 * @author javierfh03
 * @since 0.1
 */
public class DistributionInfo {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public DistributionInfo(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method obtain the complete name distribution of the machine like: 
     * Debian 11 Bullseye.
     * 
     * @return The distribution.
     * @throws OSException If can't obtain the distribution.
     */
    public String obtainDistribution() throws OSException{
        return executeCommand(OSComponents.DISTRIBUTION).getFirstLine();
    }
    
    /**
     * This method obtain the family of the distribution of the machine.
     * 
     * @return The family of distribution.
     * @throws OSException If can't obtain the distribution.
     */
    public String obtainFamilyDistribution() throws OSException{
        return executeCommand(OSComponents.DISTRIBUTION_FAMILY).getFirstLine();
    }
    
    /**
     * This method obtain the only name distribution of the machine. 
     * 
     * @return The distribution.
     * @throws OSException If can't obtain the distribution.
     */
    public String obtainDistributionName() throws OSException{
        return executeCommand(OSComponents.DISTRIBUTION_NAME).getFirstLine();
    }
    
    /**
    * This method obtain the only version distribution of the machine.
     * 
     * @return The version.
     * @throws OSException If can't obtain the distribution.
     */
    public int obtainDistributionVersion() throws OSException{
        return Integer.parseInt(executeCommand(OSComponents.DISTRIBUTION_VERSION).getFirstLine());
    }
    
    /**
     * This method exec the cat command /etc/os-release. Gets the component 
     * you want to get.
     * 
     * @param component The component who want to get.
     * @return The response of command.
     * @throws OSException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(OSComponents component) throws OSException {
        String command = String.format("cat /etc/os-release | %s | awk -F = '{print $2}' | xargs", component.getValue());
        String msgError = OSException.getMessage(this.getClass(), component.getName(), ActionsEnum.GET);
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new OSException(msgError);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new OSException(msgError);
        }
        
        return response;
    }
}
