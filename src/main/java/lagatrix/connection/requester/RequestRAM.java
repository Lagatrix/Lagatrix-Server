package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.manager.RAMManager;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class determine the request of RAM.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestRAM extends RequestManager {
    
    private RAMManager manager;

    public RequestRAM(AESCommunicator communicator, CommandExecutor executor, LogContoller logger) {
        super(communicator, executor, logger);
        this.manager = new RAMManager(executor);
    }

    @Override
    public synchronized void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        
        // Determine if get use or RAM info.
        if (request.getAction() == ActionsEnum.GET) {
            if (request.getParams().length > 0) {
                response.setResponse(manager.obtainUse());
            } else {
                response.setResponse(manager.obtainRAM());
                logger.info(communicator.getClientIp(), request.getAction(), "RAM");
            }
        }
        
        communicator.sendResponse(response);    
    }

}
