package lagatrix.server.manager.insertion.event;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.event.EventException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class and an event in the root crontab file.
 *
 * @author javier
 * @since 1.0
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
            throw new EventException("Can't insert event");
        }
    } 
}
