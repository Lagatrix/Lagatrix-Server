package lagatrix.manager.deletion.user;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.user.UserException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class delete users of the system with deluser command.
 *
 * @author javierfh03
 * @since 0.1
 */
public class UserDeletion {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public UserDeletion(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method delete an user of the system.
     * 
     * @param username The username who delete.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public void deleteUser(String username) throws UserException {
        String command = String.format("deluser %s", username);
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new UserException(UserException.getMessage(
                    this.getClass(), ActionsEnum.GET, ex.getMessage()));
        }
    } 
}
