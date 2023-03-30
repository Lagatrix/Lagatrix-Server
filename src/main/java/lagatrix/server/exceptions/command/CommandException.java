package lagatrix.server.exceptions.command;

import lagatrix.server.exceptions.LagatrixException;

/**
 * This exception repersents all errors related to the execution of the 
 * commands.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class CommandException extends LagatrixException {
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

    @Override
    public String getMessage() {
        return super.getMessage(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
