package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.manager.RAMManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class determine the request of RAM.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestRAM extends RequestManager {
    
    private RAMManager manager;

    public RequestRAM(AESCommunicator communicator, CommandExecutor executor) {
        super(communicator, executor);
        this.manager = new RAMManager(executor);
    }

    @Override
    public void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        
        // Determine if get use or RAM info.
        if (request.getAction() == ActionsEnum.INSERT) {
            if (request.getParams().length < 1) {
                response.setResponse(manager.obtainUse());
            } else {
                response.setResponse(manager.obtainRAM());
            }
        }
        
        communicator.sendResponse(response);    
    }

}
