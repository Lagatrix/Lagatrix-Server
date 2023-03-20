package lagatrix.server.exceptions.command;

import lagatrix.server.exceptions.LagatrixServerException;

/**
 * This exception repersents all errors related to the execution of the 
 * commands.
 * 
 * @author javier
 * @since 1.0
 */
public class CommandException extends LagatrixServerException {
    private int statusCode;

    public CommandException(String description, int statusCode) {
        super(description);
        this.statusCode = statusCode;
    }

    public CommandException(String description) {
        this(description, 1);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
