package lagatrix.server.exceptions.command;

/**
 * This exception repersents a command who exit code is not 0.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class CommandBadExitCodeException extends CommandException {
    
    public CommandBadExitCodeException(String description, int statusCode) {
        super(description, statusCode);
    }
    
}
