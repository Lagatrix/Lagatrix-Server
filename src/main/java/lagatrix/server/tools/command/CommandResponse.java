package lagatrix.server.tools.command;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an response of Linux command.
 * 
 * @author javier
 * @since 1.0
 */
public class CommandResponse {
    private List<String> lines;
    private int exitCode;

    /**
     * The constructor of the class.
     */
    public CommandResponse() {
        lines = new ArrayList<>();
    }

    /**
     * Establishes the exit code of the command.
     * 
     * @param exitCode The exit code.
     */
    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }
    
    /**
     * Adds an line in the list of output of command.
     * 
     * @param line 
     */
    public void addLine(String line){
        lines.add(line);
    }

    /**
     * Return the exit code of the command.
     * 
     * @return 
     */
    public int getExitCode() {
        return exitCode;
    }
    
    /**
     * This metod obtain the first line of the response, if the response is 
     * clean it return an empty string.
     * 
     * @return 
     */
    public String getFirstLine(){
        if (!lines.isEmpty()) {
            return lines.get(0);
        } else {
            return "";
        }
    }

    /**
     * Return the list of the output.
     * 
     * @return The list of output.
     */
    public List<String> getLines() {
        return lines;
    }
}
