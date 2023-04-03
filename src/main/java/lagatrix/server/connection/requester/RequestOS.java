package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.manager.OSManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class determine the request of OS information.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestOS extends RequestManager {
    
    private OSManager manager;

    public RequestOS(AESCommunicator communicator, CommandExecutor executor) {
        super(communicator, executor);
        this.manager = new OSManager(executor);
    }

    @Override
    public void determineRequest(Request request) throws LagatrixException {
        Response response = new Response(manager.obtainOSInformation());
        
        response.setCorrectResult(true);
        communicator.sendResponse(response);
    }
}
