package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.file.log.LogContoller;
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

    public RequestProcess(AESCommunicator communicator, CommandExecutor executor, LogContoller logger) {
        super(communicator, executor, logger);
        this.manager = new ProcessManager(executor);
    }

    @Override
    public void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        String message = null;
        
        // Determine the request of process.
        if (request.getAction() == ActionsEnum.GET){
            response.setResponse(manager.getProcess());
        } else if (request.getAction() == ActionsEnum.DELETE) {
            manager.killProcess((int) request.getParams()[0]);
            message = String.format("the PID of process who kill is %d", request.getParams()[0]);
            response.setCorrectResult(true);
        }
        
        logger.info(communicator.getClientIp(), request.getAction(), "process", message);
        communicator.sendResponse(response);
    }
}
