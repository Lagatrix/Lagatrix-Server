package lagatrix.server.exceptions.manager;

import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.exceptions.LagatrixException;

/**
 * This exception repersents all errors related to the managers.
 * 
 * @author javier
 * @since 1.0
 */
public class ManagerException extends LagatrixException{

    public ManagerException(String errorMessage) {
        super(errorMessage);
    }
    
    /**
     * This method generate the exception message.
     * 
     * @param manager The class of manager who use.
     * @param component The component who manipulate.
     * @param action The action who realice.
     * @param response The response line of error command.
     * @return The message.
     */
    public static String getMessage(Class manager, String component, ActionsEnum action, String response) {
        if (component == null && response == null) {
            return String.format("When %s in %s", manager.getClass().
                    getSimpleName(), action.getCorrectName());
        }
        
        if (component == null) {
            return String.format("When %s in %s, result command: %s", 
                manager.getClass().getSimpleName(), action.getCorrectName(), response);
        } else if (response == null) {
            return String.format("When %s in %s with component %s", 
                manager.getClass().getSimpleName(), action.getCorrectName(), component);
        }
        
        return String.format("When %s in %s with component %s, result command: %s", 
                manager.getClass().getSimpleName(), action.getCorrectName(), component, response);
    }
    
    /**
     * This method generate the exception message.
     * 
     * @param manager The class of manager who use.
     * @param action The action who realice.
     * @param response The response line of error command.
     * @return The message.
     */
    public static String getMessage(Class manager, ActionsEnum action, String response) {
        return getMessage(manager, null, action, response);
    }
    
    /**
     * This method generate the exception message.
     * 
     * @param manager The class of manager who use.
     * @param component The component who manipulate.
     * @param action The action who realice.
     * @return The message.
     */
    public static String getMessage(Class manager, String component, ActionsEnum action) {
        return getMessage(manager, component, action, null);
    }
    
    /**
     * This method generate the exception message.
     * 
     * @param manager The class of manager who use.
     * @param action The action who realice.
     * @return The message.
     */
    public static String getMessage(Class manager, ActionsEnum action) {
        return getMessage(manager, null, action, null);
    }

}
