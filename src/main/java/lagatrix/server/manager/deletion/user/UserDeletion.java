package lagatrix.server.manager.deletion.user;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.user.UserException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class delete users of the system with deluser command.
 *
 * @author javier
 * @since 1.0
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
     * @param username
     * @throws UserException 
     */
    public void deleteUser(String username) throws UserException {
        String command = String.format("deluser %s", username);
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new UserException("Can't delete user");
        }
    } 
}
