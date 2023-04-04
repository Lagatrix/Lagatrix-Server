package lagatrix.server.manager.information.user;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class auth an user.
 *
 * @author javierfh03
 * @since 0.2
 */
public class AuthUser {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public AuthUser(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method auth an user.
     * 
     * @param username The user to check.
     * @param password The password of user.
     * @return If the auth is correct.
     */
    public boolean authUser(String username, String password) {
        String command = String.format("su %s -c whoami", username);
        
        try {
            executor.executeCommand(command, password); 
        } catch (CommandException ex) {
            return false;
        }
        
        return true;
    }
}
