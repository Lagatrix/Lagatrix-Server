package lagatrix.manager.information.user;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.user.UserException;
import lagatrix.tools.command.CommandExecutor;
import lagatrix.tools.command.CommandResponse;

/**
 * This class obtain information of Linux group.
 *
 * @author javierfh03
 * @since 0.3
 */
public class GroupInfo {
    
    private CommandExecutor executor;
    
    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public GroupInfo(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * Obtain the name of group of user.
     * 
     * @param numGroup The id of group.
     * @return The name of group.
     * @throws UserException In can't obtain the name of the group.
     */
    public String obtainGroup(int numGroup) throws UserException {
        String command = String.format("cat /etc/group | grep %d | awk -F : '{print $1}'",
                numGroup);
        String msgError = UserException.getMessage(this.getClass(), 
                "name group", ActionsEnum.GET);
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new UserException(msgError);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new UserException(msgError);
        }
        
        return response.getFirstLine();
    }
}
