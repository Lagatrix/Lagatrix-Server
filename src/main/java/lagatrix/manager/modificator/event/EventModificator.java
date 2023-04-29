package lagatrix.manager.modificator.event;

import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.command.CommandException;
import lagatrix.exceptions.manager.event.EventException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class modify event in the root crontab file.
 *
 * @author javierfh03
 * @since 0.1
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
     * This method modify an event of the crontab, the format of event is like
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
            throw new EventException(EventException.getMessage(
                    this.getClass(), ActionsEnum.MODIFY, ex.getMessage()));
        }
    } 
}
