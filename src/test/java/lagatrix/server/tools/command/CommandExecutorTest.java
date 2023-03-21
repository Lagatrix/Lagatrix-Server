package lagatrix.server.tools.command;

import lagatrix.server.exceptions.command.CommandBadExitCodeException;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.command.CommandInOutException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This tests check if the CommandExecutor founds.
 *
 * @author javier
 * @since 1.0
 */
public class CommandExecutorTest {
    private CommandExecutor executor;
    
    public CommandExecutorTest() {
        executor = new CommandExecutor();
    }
    
    @Test
    public void normalTest() throws CommandInOutException, CommandBadExitCodeException{
        CommandResponse response = executor.executeCommand("ls");
        
        assertEquals(0, response.getExitCode());
    }
    
    @Test
    public void stdinTest() throws CommandInOutException, CommandBadExitCodeException{
        CommandResponse response = executor.executeCommand("ls", true, "docker"); // Sudo Password.
        
        System.out.println(response.getLines().get(0));
        assertEquals(0, response.getExitCode());
    }
    
    @Test
    public void notExistingCommand() {
        try {
            executor.executeCommand("nocommand");
        } catch (CommandException ex) {
            assertEquals(127, ex.getStatusCode());
        }
    }
    
    @Test
    public void badUseCommandTest() {
        try {
            executor.executeCommand("ls not");
        } catch (CommandException ex) {
            assertEquals(2, ex.getStatusCode());
        }
    }
    
    @Test
    public void generalErrorTest() {
        try {
            executor.executeCommand("ls -q");
        } catch (CommandException ex) {
            assertEquals(1, ex.getStatusCode());
        }
    }
    
    @Test
    public void stdinErrorTest() {
        try {
            executor.executeCommand("ls", "not");
        } catch (CommandException ex) {
            assertEquals(CommandInOutException.class, ex.getClass());
        }
    }
}
