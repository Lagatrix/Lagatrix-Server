package lagatrix.tools.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import lagatrix.exceptions.command.CommandBadExitCodeException;
import lagatrix.exceptions.command.CommandInOutException;

/**
 * This class exec a command, he puts stdin and gets the input of the command. 
 * Also handles output errors.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class CommandExecutor {
    
    /**
     * The constructor of the class, no require parameters.
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
        String definitiveCommand = (executeRoot) ? String.format("sudo -S %s", command) : command;
        
        try {
            process = new ProcessBuilder("/bin/bash", "-c", definitiveCommand).start();
            
            insertArgs(process.getOutputStream(), stdin);
            
            response.setExitCode(process.waitFor());
            
            if (response.getExitCode() != 0){
                readLinesResponse(response, process.getErrorStream());
                getException(response.getExitCode(), response);
            }
            
            readLinesResponse(response, process.getInputStream());
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
     * @param command The response of command.
     * @throws CommandBadExitCodeException The exception with description.
     */
    private static void getException (int statusCode, CommandResponse command) throws CommandBadExitCodeException{
        String description;
        
        switch (statusCode) {
            case 1:
                description = String.format("general error: %s", command.getFirstLine());
                break;
            case 2:
                description = String.format("bad usage: %s", command.getFirstLine());
                break;
            case 126:
                description = String.format("permision error: %s", command.getFirstLine());
                break;
            case 127:
                description = String.format("command not exist: %s", command.getFirstLine());
                break;
            default:
                description = String.format("special error %d: %s", statusCode, command.getFirstLine());
        }
        
        throw new CommandBadExitCodeException(description, statusCode);
    }
}
