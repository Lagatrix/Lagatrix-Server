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
        String simpleMessage = String.format("When %s in '%s' class", action.getCorrectName(), 
                manager.getSimpleName());
        String responseMessage = String.format("result command: %s", response);
        String componentMessage = String.format("with component '%s'",component);
        
        if (component == null && response == null) {
            return simpleMessage;
        }
        
        if (component == null) {
            return String.format("%s with %s", simpleMessage, responseMessage);
        } else if (response == null) {
            return String.format("%s %s", simpleMessage, componentMessage);
        }
        
        return String.format("%s %s %s", simpleMessage, componentMessage, responseMessage);
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
