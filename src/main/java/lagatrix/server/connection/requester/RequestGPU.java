package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.file.log.LogContoller;
import lagatrix.server.manager.GPUManager;
import lagatrix.server.tools.command.CommandExecutor;

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
    public void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        
        if (request.getAction() == ActionsEnum.GET) {
            response.setResponse(manager.obtainGPU());
            logger.info(communicator.getClientIp(), request.getAction(), "GPU");
        }
        
        communicator.sendResponse(response);
    }

}
