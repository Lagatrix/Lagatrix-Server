package lagatrix.server.manager.information.user;

import lagatrix.server.entities.components.UserComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.user.UserException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain information of Linux user.
 *
 * @author javier
 * @since 1.0
 */
public class UserInfo {
    
    private CommandExecutor executor;
    
    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public UserInfo(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method obtain the name of user.
     * 
     * @param numUser
     * @return The name of user.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainUsername(int numUser) throws UserException{
        return executeCommand(numUser, UserComponents.USERNAME).getFirstLine();
    }
    
    /**
     * This method obtain the main group of user.
     * 
     * @param numUser
     * @return The main group of user.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainMainGroup(int numUser) throws UserException{
        return executeCommand(numUser, UserComponents.GROUP).getFirstLine();
    }
    
    /**
     * This method obtain the home path of user.
     * 
     * @param numUser
     * @return The home path of user.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainHomePath(int numUser) throws UserException{
        return executeCommand(numUser, UserComponents.HOME).getFirstLine();
    }
    
    /**
     * This method obtain the shell of user.
     * 
     * @param numUser
     * @return The shell of user
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainShell(int numUser) throws UserException{
        return executeCommand(numUser, UserComponents.SHELL).getFirstLine();
    }
    
    /**
     * This method exec the uname command. Gets the component you want to get.
     * 
     * @param component The component who want to get.
     * @return The response of command.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(int numUser, UserComponents component) throws UserException {
        String command = String.format("cat /etc/passwd | awk -F : '{if($3 > 999 && $3 < 65534) print $%d}' | sed -n %dp", 
                component.getValueInfo(), numUser);
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new UserException(component.getName());
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new UserException(component.getName());
        }
        
        return response;
    }
}
