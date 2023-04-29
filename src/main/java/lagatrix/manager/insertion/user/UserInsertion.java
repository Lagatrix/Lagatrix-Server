package lagatrix.manager.insertion.user;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.user.UserException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class insert an user in the system.
 *
 * @author javierfh03
 * @since 0.1
 */
public class UserInsertion {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public UserInsertion(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method add a user in the system, his main group will be the default 
     * group.
     * 
     * @param name The name of user.
     * @param home The home of user.
     * @param shell The shell of user.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public void insertUser(String name, String home, String shell) throws UserException{
        insertUser(name, home, shell, null);
    }
    
    /**
     * This method add a user in the system.
     * 
     * @param name The name of user.
     * @param home The home of user.
     * @param shell The shell of user.
     * @param mainGroup The main group of user, has to exist.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public void insertUser(String name, String home, String shell, String mainGroup) throws UserException{
        executeCommand(name, home, shell, mainGroup);
    }
    
    /**
     * This method exec the useradd command.
     * 
     * @param name The name of the user.
     * @param home The home of the user.
     * @param shell The shell of user.
     * @param mainGroup The main group of user, has to exist.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    private void executeCommand(String name, String home, String shell, String mainGroup) throws UserException {
        String command = String.format("sudo useradd %s -m -d %s  -s %s", name, home, shell);
        
        // Check if you don't want to be added to existing group.
        if (mainGroup != null){
            command = String.format("%s -G %s", command, mainGroup);
        }
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new UserException(UserException.getMessage(
                    this.getClass(), ActionsEnum.INSERT, ex.getMessage()));
        }
    }
}
