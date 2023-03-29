package lagatrix.server.manager.information.event;

import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.components.EventComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.event.EventException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain information of events in root crontab file.
 *
 * @author javier
 * @since 1.0
 */
public class EventInfo {
    
 private CommandExecutor executor;
    
    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public EventInfo(CommandExecutor executor) {
        this.executor = executor;
    }
    
    /**
     * This method obtain the minute who exec event.
     * 
     * @param numEvent The number of event to get.
     * @return The minute of exec.
     * @throws EventException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainMinute(int numEvent) throws EventException{
        return executeCommand(numEvent, EventComponents.MINUTE).getFirstLine();
    }
    
    /**
     * This method obtain the hour who exec event.
     * 
     * @param numEvent The number of event to get.
     * @return The hour of exec.
     * @throws EventException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainHour(int numEvent) throws EventException{
        return executeCommand(numEvent, EventComponents.HOUR).getFirstLine();
    }
    
    /**
     * This method obtain the day of month who exec event.
     * 
     * @param numEvent The number of event to get.
     * @return The day of month of exec.
     * @throws EventException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainDayMonth(int numEvent) throws EventException{
        return executeCommand(numEvent, EventComponents.DAY_MONTH).getFirstLine();
    }
    
    /**
     * This method obtain the month who exec event.
     * 
     * @param numEvent The number of event to get.
     * @return The month of exec.
     * @throws EventException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainMonth(int numEvent) throws EventException{
        return executeCommand(numEvent, EventComponents.MONTH).getFirstLine();
    }
    
    /**
     * This method obtain the day of week who exec event.
     * 
     * @param numEvent The number of event to get.
     * @return The day of week who of exec.
     * @throws EventException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainDayWeek(int numEvent) throws EventException{
        return executeCommand(numEvent, EventComponents.DAY_WEEK).getFirstLine();
    }
    
    /**
     * This method obtain the command of event.
     * 
     * @param numEvent The number of event to get.
     * @return The command of event.
     * @throws EventException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainCommand(int numEvent) throws EventException{
        return executeCommand(numEvent, EventComponents.COMMAND).getFirstLine();
    }
    
    /**
     * This method exec the crontab -l command. Gets the component you want to 
     * get.
     * 
     * @param numEvent The number of event to get.
     * @param component The component who want to get.
     * @return The response of command.
     * @throws UserException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(int numEvent, EventComponents component) throws EventException {
        String command = String.format("crontab -l | grep -v \"#\" | cut -d\" \" -f%s | sed -n %dp", 
                component.getValue(), numEvent);
        String msgError = EventException.getMessage(
                    this.getClass(), component.getName(), ActionsEnum.GET);
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command, true); 
        } catch (CommandException ex) {
            throw new EventException(msgError);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new EventException(msgError);
        }
        
        return response;
    }
}
