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
    private int statusCode;

    public CommandResponse() {
        lines = new ArrayList<>();
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    public void addLine(String line){
        lines.add(line);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<String> getLines() {
        return lines;
    }
}
