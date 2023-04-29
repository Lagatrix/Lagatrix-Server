package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.manager.OSManager;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class determine the request of OS information.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestOS extends RequestManager {
    
    private OSManager manager;

    public RequestOS(AESCommunicator communicator, CommandExecutor executor, LogContoller logger) {
        super(communicator, executor, logger);
        this.manager = new OSManager(executor);
    }

    @Override
    public synchronized void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        
        if (request.getAction() == ActionsEnum.GET){
            response.setResponse(manager.obtainOSInformation());
            logger.info(communicator.getClientIp(), request.getAction(), "operative system");
        }
        
        communicator.sendResponse(response);
    }
}
