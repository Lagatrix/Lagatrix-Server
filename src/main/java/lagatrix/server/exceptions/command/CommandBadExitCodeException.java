package lagatrix.server.exceptions.command;

/**
 * This exception repersents a command who exit code is not 0.
 * 
 * @author javier
 * @since 1.0
 */
public class CommandBadExitCodeException extends CommandException {
    
    public CommandBadExitCodeException(String description, int statusCode) {
        super(description, statusCode);
    }
    
}
