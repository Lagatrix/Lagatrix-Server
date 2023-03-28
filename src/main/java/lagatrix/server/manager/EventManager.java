package lagatrix.server.manager;

import java.util.LinkedHashSet;
import java.util.Set;
import lagatrix.server.entities.dto.event.Event;
import lagatrix.server.exceptions.manager.event.EventException;
import lagatrix.server.manager.deletion.event.EventDeletion;
import lagatrix.server.manager.information.event.EventInfo;
import lagatrix.server.manager.insertion.event.EventInsertion;
import lagatrix.server.manager.modificator.event.EventModificator;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class manage events of root crontab file.
 *
 * @author javier
 * @since 1.0
 */
public class EventManager {
    
    private EventInfo information;
    private EventModificator modificator;
    private EventInsertion insertion;
    private EventDeletion deletion;

    /**
     * The constructor of the class
     *
     * @param executor The executor command.
     */
    public EventManager(CommandExecutor executor) {
        this.information = new EventInfo(executor);
        this.modificator = new EventModificator(executor);
        this.insertion = new EventInsertion(executor);
        this.deletion = new EventDeletion(executor);
    }
    
    /**
     * This methos obtain events of the root crontab file..
     * 
     * @return An set with events.
     * @throws EventException If can't obtain the events.
     */
    public Set<Event> getEvents() throws EventException{
        Set<Event> events = new LinkedHashSet<>();
        Event event;
        int index = 1;
        
        while (true) {
            event = new Event();
            
            try {
                event.setMinute(information.obtainMinute(index));
                event.setHour(information.obtainHour(index));
                event.setDayMonth(information.obtainDayMonth(index));
                event.setMonth(information.obtainMonth(index));
                event.setDayWeek(information.obtainDayWeek(index));
                event.setCommand(information.obtainCommand(index));
                
                events.add(event);
                index++;
            } catch (EventException ex) {
                if (events.isEmpty()) {
                    throw ex;
                } else {
                    return events;
                }
            }
        }
    }
    
    /**
     * This method insert event in the system.
     * 
     * @param event The event who insert
     * @throws EventException If can't insert the event.
     */
    public void insertEvent(Event event) throws EventException {
        insertion.insertEvent(event.toString());
    }
    
    /**
     * This method modify an user in the system.
     * 
     * @param eventOld The old event to change.
     * @param eventNew The event changed.
     * @throws EventException If can't modify the event.
     */
    public void modifyEvent(Event eventOld, Event eventNew) throws EventException {
        modificator.modifyEvent(eventOld.toString(), eventNew.toString());
    }
    
    /**
     * This method delete an event.
     * 
     * @param event The username of user who delete.
     * @throws EventException If can't delete the event.
     */
    public void deleteUser(Event event) throws EventException {
        deletion.deleteEvent(event.toString());
    }
}
