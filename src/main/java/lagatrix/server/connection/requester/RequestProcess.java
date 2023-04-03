package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.manager.ProcessManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class determine the request of process.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestProcess extends RequestManager {
    
    private ProcessManager manager;

    public RequestProcess(AESCommunicator communicator, CommandExecutor executor) {
        super(communicator, executor);
        this.manager = new ProcessManager(executor);
    }

    @Override
    public void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        
        // Determine the request of process.
        if (request.getAction() == ActionsEnum.GET){
            response = new Response(manager.getProcess(), true);
        } else if (request.getAction() == ActionsEnum.DELETE) {
            manager.killProcess((int) request.getParams()[0]);
            response.setCorrectResult(true);
        }
        
        communicator.sendResponse(response);
    }
}
