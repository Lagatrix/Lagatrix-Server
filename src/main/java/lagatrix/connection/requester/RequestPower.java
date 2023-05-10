package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.tools.command.CommandExecutor;

/**
 * Make power actions in the server.
 * 
 * @author javier03
 * @since 0.3
 */
public class RequestPower extends RequestManager {

    public RequestPower(AESCommunicator communicator, CommandExecutor executor, LogContoller logger) {
        super(communicator, executor, logger);
    }

    @Override
    public synchronized void determineRequest(Request request) throws LagatrixException {
        if (request.getParams()[0].equals("off")) {
            executor.executeCommand("poweroff", true);
        } else if (request.getParams()[0].equals("restart")){
            executor.executeCommand("reboot", true);
        } else {
            communicator.sendResponse(new Response());
        }
    }
    
}
