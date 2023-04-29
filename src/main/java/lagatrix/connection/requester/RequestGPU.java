package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.manager.GPUManager;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class determine the request of GPU.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestGPU extends RequestManager {
    
    private GPUManager manager;

    public RequestGPU(AESCommunicator communicator, CommandExecutor executor, LogContoller logger) {
        super(communicator, executor, logger);
        manager = new GPUManager(executor);
    }

    @Override
    public synchronized void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        
        if (request.getAction() == ActionsEnum.GET) {
            response.setResponse(manager.obtainGPU());
            logger.info(communicator.getClientIp(), request.getAction(), "GPU");
        }
        
        communicator.sendResponse(response);
    }

}
