package lagatrix.server.exceptions.command;

/**
 * This exception repersents a command with I/O problems.
 *
 * @author javier
 * @since 1.0
 */
public class CommandInOutException extends CommandException {

    public CommandInOutException(String description) {
        super(description);
    }

}
