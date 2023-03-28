package lagatrix.server.manager.modificator.event;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.event.EventException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class modify event in the root crontab file.
 *
 * @author javier
 * @since 1.0
 */
public class EventModificator {
        
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public EventModificator(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method modify an event of the crontab., the format of event is like
     * this: * * * * * command
     * 
     * @param eventOld The old event.
     * @param eventNew The new event.
     * @throws EventException If a problem occurs with the execution of the 
     * command.
     */
    public void modifyEvent(String eventOld, String eventNew) throws EventException {
        String command = String.format("crontab -l | sed '/%s/c %s' | sudo crontab -", eventOld, eventNew);
        
        try {
            executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new EventException("Can't modify event");
        }
    } 
}
