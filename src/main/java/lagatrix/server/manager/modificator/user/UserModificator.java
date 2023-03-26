package lagatrix.server.manager.modificator.user;

import lagatrix.server.entities.components.UserComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.user.ExistException;
import lagatrix.server.exceptions.manager.user.HomeException;
import lagatrix.server.exceptions.manager.user.NotExistException;
import lagatrix.server.exceptions.manager.user.UserException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class modify an Linux user with usermod command.
 *
 * @author javier
 * @since 1.0
 */
public class UserModificator {
    
    private CommandExecutor executor;
    
    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public UserModificator(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method modify the username of user.
     * 
     * @param username The username of user to change username.
     * @param newUsername The new username.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public void modifyUsername(String username, String newUsername) throws UserException{
        executeCommand(username, newUsername, UserComponents.USERNAME);
    }
    
    /**
     * This method modfy the home of user.
     * 
     * @param username The username of user to change the home.
     * @param newHome The path of the new home.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public void modifyHome(String username, String newHome) throws UserException{
        executeCommand(username, newHome, UserComponents.USERNAME);
    }
    
    /**
     * This method modfy the shell of user.
     * 
     * @param username The username of user to change the shell.
     * @param newShell The path of the new shell.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public void modifyShell(String username, String newShell) throws UserException{
        executeCommand(username, newShell, UserComponents.USERNAME);
    }
    
    /**
     * This method modfy the main group of user.
     * 
     * @param username The username of user to change the shell.
     * @param newMainGroup The new group.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public void modifyMainGroup(String username, String newMainGroup) throws UserException{
        executeCommand(username, newMainGroup, UserComponents.USERNAME);
    }
    
    /**
     * This method exec the uname command. Gets the component you want to get.
     * 
     * @param component The component who want to modify.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    private void executeCommand(String username, String newValue, UserComponents component) throws UserException {
        String command = String.format("usermod %s %s %s", component.getValueUserMod(), newValue, username);
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            switch (ex.getStatusCode()) {
                case 6:
                    throw new NotExistException(component.getName());
                case 9:
                    throw new ExistException(component.getName());
                case 12:
                    throw new HomeException(component.getName());
                default:
                    throw new UserException(component.getName());
            }
        }
    }
}
