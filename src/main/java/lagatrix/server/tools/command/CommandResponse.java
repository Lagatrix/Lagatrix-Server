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

    public CommandResponse() {
        lines = new ArrayList<>();
    }

    public void setStatusCode(int exitCode) {
        this.exitCode = exitCode;
    }
    
    public void addLine(String line){
        lines.add(line);
    }

    public int getExitCode() {
        return exitCode;
    }
    
    public String getFirstLine(){
        return lines.get(0);
    }

    public List<String> getLines() {
        return lines;
    }
}
