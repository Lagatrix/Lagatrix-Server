package lagatrix.manager.deletion.event;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.event.EventException;
import lagatrix.tools.command.CommandExecutor;
import lagatrix.tools.formater.CommandFormater;

/**
 * This class delete event in the root crontab file.
 *
 * @author javierfh03
 * @since 0.1
 */
public class EventDeletion {
        
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public EventDeletion(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method delete an event of the crontab.
     * 
     * @param event The event to delete, format like this: * * * * * command
     * @throws EventException If a problem occurs with the execution of the 
     * command.
     */
    public void deleteEvent(String event) throws EventException {
        String command = String.format("crontab -l | grep -v \"%s\" | sudo crontab -", 
                CommandFormater.sedEventFormater(event));
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new EventException(EventException.getMessage(this.getClass(), 
                    ActionsEnum.DELETE, ex.getMessage()));
        }
    } 
}
