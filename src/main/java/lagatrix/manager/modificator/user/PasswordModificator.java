package lagatrix.manager.modificator.user;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.user.UserException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class change the password of user.
 *
 * @author javierfh03
 * @since 0.1
 */
public class PasswordModificator {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public PasswordModificator(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method modify the password of user.
     * 
     * @param username The username of user to change.
     * @param newPassword The new password.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public void modifyPassword(String username, String newPassword) throws UserException {
        String command = String.format("printf '%s\\n%s' | sudo passwd %s", newPassword, newPassword, username);
        
        try {
            executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new UserException(UserException.getMessage(
                    this.getClass(), ActionsEnum.MODIFY, ex.getMessage()));
        }
    }
}
