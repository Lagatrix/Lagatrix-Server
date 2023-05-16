package lagatrix.manager;

import java.util.LinkedHashSet;
import java.util.Set;
import lagatrix.entities.dto.event.Event;
import lagatrix.exceptions.manager.event.EventException;
import lagatrix.manager.deletion.event.EventDeletion;
import lagatrix.manager.information.event.EventInfo;
import lagatrix.manager.insertion.event.EventInsertion;
import lagatrix.manager.modificator.event.EventModificator;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class manage events of root crontab file.
 *
 * @author javierfh03
 * @since 0.1
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
                return events;
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
    public void deleteEvent(Event event) throws EventException {
        deletion.deleteEvent(event.toString());
    }
}
