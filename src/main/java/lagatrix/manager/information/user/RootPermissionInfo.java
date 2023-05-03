package lagatrix.manager.information.user;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.user.UserException;
import lagatrix.tools.command.CommandExecutor;
import lagatrix.tools.command.CommandResponse;

/**
 * This class determine if an user have root permission.
 *
 * @author javierfh03
 * @since 0.1
 */
public class RootPermissionInfo {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public RootPermissionInfo(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method check if user have a root permisson.
     * 
     * @param username The user to check.
     * @return If have a root permisson.
     */
    public boolean isRoot(String username) {
        try {
            executeCommand(username);
        } catch (UserException ex) {
            return false;
        }
        
        return true;
    }
    
    /**
     * This method exec the groups command. Gets the component you want to get.
     * 
     * @param component The component who want to get.
     * @return The response of command.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(String username) throws UserException {
        String command = String.format("groups %s | grep 'sudo\\|wheel'", username);
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new UserException(UserException.getMessage(this.getClass(), ActionsEnum.GET));
        }
        
        return response;
    }
}
