package lagatrix.server.exceptions.command;

/**
 * This exception repersents a command with I/O problems.
 *
 * @author javierfh03
 * @since 0.1
 */
public class CommandInOutException extends CommandException {

    public CommandInOutException(String description) {
        super(description);
    }

}
