package lagatrix.server.manager.deletion.event;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.event.EventException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class delete event in the root crontab file.
 *
 * @author javier
 * @since 1.0
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
        String command = String.format("crontab -l | grep -v \"%s\"  | sudo crontab -", event);
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new EventException("Can't delete event");
        }
    } 
}
