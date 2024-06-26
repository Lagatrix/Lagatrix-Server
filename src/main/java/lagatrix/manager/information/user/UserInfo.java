package lagatrix.manager.information.user;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.components.UserComponents;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.user.UserException;
import lagatrix.tools.command.CommandExecutor;
import lagatrix.tools.command.CommandResponse;

/**
 * This class obtain information of Linux user.
 *
 * @author javierfh03
 * @since 0.1
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
    public String obtainIdMainGroup(int numUser) throws UserException{
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
     * This method exec the cat in /etc/passwd command. Gets the component you 
     * want to get.
     * 
     * @param component The component who want to get.
     * @return The response of command.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(int numUser, UserComponents component) throws UserException {
        String command = String.format("cat /etc/passwd | awk -F : '{if($3 > 999 && $3 < 65534) print $%d}' | sed -n %dp", 
                component.getValueInfo(), numUser);
        String msgError = UserException.getMessage(this.getClass(), 
                component.getName(), ActionsEnum.GET);
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
        
        return response;
    }
}
