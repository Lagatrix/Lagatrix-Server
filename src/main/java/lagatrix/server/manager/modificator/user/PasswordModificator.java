package lagatrix.server.manager.modificator.user;

import lagatrix.server.entities.components.UserComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.user.ExistException;
import lagatrix.server.exceptions.manager.user.HomeException;
import lagatrix.server.exceptions.manager.user.NotExistException;
import lagatrix.server.exceptions.manager.user.PasswordException;
import lagatrix.server.exceptions.manager.user.UserException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * 
 *
 * @author javier
 * @since 1.0
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
     * @param newPassword The new password-
     * @throws PasswordException If a problem occurs with the execution of the 
     * command.
     */
    public void modifyPassword(String username, String newPassword) throws PasswordException {
        String command = String.format("printf '%s\n%s' | passwd %s", newPassword, newPassword, username);
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new PasswordException();
        }
    }
}
