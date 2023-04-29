package lagatrix.manager.insertion.event;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.event.EventException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class insert event in the root crontab file.
 *
 * @author javierfh03
 * @since 0.1
 */
public class EventInsertion {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public EventInsertion(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method insert an event of the crontab.
     * 
     * @param event The event to add, format like this: * * * * * command
     * @throws EventException If a problem occurs with the execution of the 
     * command.
     */
    public void insertEvent(String event) throws EventException {
        String command = String.format("(sudo crontab -l && echo \"%s\") | sudo crontab -", event);
        
        try {
            executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new EventException(EventException.getMessage(
                    this.getClass(), ActionsEnum.INSERT, ex.getMessage()));
        }
    } 
}
