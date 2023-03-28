package lagatrix.server.tools.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import lagatrix.server.exceptions.command.CommandBadExitCodeException;
import lagatrix.server.exceptions.command.CommandInOutException;

/**
 * This class exec a command, he puts stdin and gets the input of the command. 
 * Also handles output errors.
 * 
 * @author javier
 * @since 1.0
 */
public class CommandExecutor {
    
    /**
     * Te constructor of the class, no require parameters.
     */
    public CommandExecutor(){ 
    }
    
    /**
     * This method exect a linux command.
     * 
     * @param command The command to exec.
     * @param executeRoot If use sudo to exec command.
     * @param stdin Optional stdins to the command.
     * @return An CommandResponse object with status code and lines of input.
     * @throws CommandInOutException If occurs an I/O error.
     * @throws CommandBadExitCodeException If the command 
     * gets an different status code from 0.
     */
    public CommandResponse executeCommand(String command, boolean executeRoot, String... stdin) throws CommandInOutException, CommandBadExitCodeException {
        Process process;
        CommandResponse response = new CommandResponse();
        String definitiveCommand = (executeRoot) ? "sudo -S " + command : command;
        
        try {
            process = new ProcessBuilder("/bin/bash", "-c", definitiveCommand).start();
            
            insertArgs(process.getOutputStream(), stdin);
            readLinesResponse(response, process.getInputStream());
            
            response.setExitCode(process.waitFor());
            
            if (response.getExitCode() != 0){
                getException(response.getExitCode(), command);
            }
        } catch (IOException | InterruptedException ex) {
            throw new CommandInOutException(String.format("Error in I/O command: %s", command));
        }
        
        return response;
    }
    
    /**
     * This method exect a linux command with not sudo privileges.
     * 
     * @param command The command to exec.
     * @param stdin Optional stdins to the command.
     * @return An CommandResponse object with status code and lines of input.
     * @throws CommandInOutException If occurs an I/O error.
     * @throws CommandBadExitCodeException If the command gets an different 
     * status code from 0.
     */
    public CommandResponse executeCommand(String command, String... stdin) throws CommandInOutException, CommandBadExitCodeException{
        return executeCommand(command, false, stdin);
    }
    
    /**
     * This method insert stdin into command.
     * 
     * @param os The OuputStream of process.
     * @param stdin The stdins to enter.
     * @throws IOException If occurs an I/O error.
     */
    private void insertArgs(OutputStream os, String... stdin) throws IOException{
        for (String string : stdin) {
            os.write((string + "\n").getBytes());
        }
        os.flush();
    }
    
    /**
     * This method read the lines of input command and save it into the 
     * CommandResponse object.
     * 
     * @param response The response.
     * @param is The InputStream of the process.
     * @throws IOException If occurs an I/O error.
     */
    private void readLinesResponse(CommandResponse response, InputStream is) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        
        while((line = reader.readLine()) != null){
                response.addLine(line);
            }
    }
    
    /**
     * This method determine the description of error if the commad return an 
     * non 0 exit value.
     * 
     * @param statusCode The status code who return the command.
     * @param command The command who it executed.
     * @throws CommandBadExitCodeException The exception with description.
     */
    private static void getException (int statusCode, String command) throws CommandBadExitCodeException{
        String description;
        
        switch (statusCode) {
            case 1:
                description = String.format("general error with command: %s", command);
                break;
            case 2:
                description = String.format("bad usage with command: %s", command);
                break;
            case 126:
                description = String.format("permision error with command: %s", command);
                break;
            case 127:
                description = String.format("this command not exist: %s", command);
                break;
            default:
                description = String.format("special error %d of command: %s", statusCode, command);
        }
        
        throw new CommandBadExitCodeException(description, statusCode);
    }
}
