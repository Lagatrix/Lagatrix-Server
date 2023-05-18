package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.manager.ProcessManager;
import lagatrix.tools.command.CommandExecutor;

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
    public synchronized void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        
        // Determine the request of process.
        if (request.getAction() == ActionsEnum.GET){
            response.setResponse(manager.getProcess());
        } else if (request.getAction() == ActionsEnum.DELETE) {
            manager.killProcess((Integer) request.getParams()[0]);
            response.setCorrectResult(true);
            
            makeLog(String.format("the PID of process who kill is %d", request.getParams()[0])
                    , request.getAction(), "process");
        }
        communicator.sendResponse(response);
    }
}
