package lagatrix.server.manager.insertion.user;

import lagatrix.server.entities.components.UserComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.user.ExistException;
import lagatrix.server.exceptions.manager.user.HomeException;
import lagatrix.server.exceptions.manager.user.NotExistException;
import lagatrix.server.exceptions.manager.user.UserException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class insert an user in the system.
 *
 * @author javier
 * @since 1.0
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
     * @param password The password of user.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public void insertUser(String name, String home, String shell, String password) throws UserException{
        insertUser(name, home, shell, password, null);
    }
    
    /**
     * This method add a user in the system.
     * 
     * @param name The name of user.
     * @param home The home of user.
     * @param shell The shell of user.
     * @param password The password of user.
     * @param mainGroup The main group of user, has to exist.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public void insertUser(String name, String home, String shell, String password, String mainGroup) throws UserException{
        executeCommand(name, home, shell, password, mainGroup);
    }
    
    /**
     * This method exec the useradd command.
     * 
     * @param name The name of the user.
     * @param home The home of the user.
     * @param shell
     * @param password
     * @param mainGroup
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    private void executeCommand(String name, String home, String shell, String password, String mainGroup) throws UserException {
        String command = String.format("sudo useradd %s -m -d %s  -s %s -p %s ", name, home, shell, password);
        
        // Check if you don't want to be added to existing group.
        if (mainGroup != null){
            command = String.format("%s -G %s", command, mainGroup);
        }
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            switch (ex.getStatusCode()) {
                case 6:
                    throw new NotExistException(UserComponents.GROUP.getName());
                case 9:
                    throw new ExistException(UserComponents.USERNAME.getName());
                default:
                    throw new UserException(String.format("Can't add user %s", name));
            }
        }
    }
}
