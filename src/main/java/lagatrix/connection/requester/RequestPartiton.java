package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.manager.PartitionManager;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class determine the request of partitions.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestPartiton extends RequestManager {
    
    private PartitionManager manager;

    public RequestPartiton(AESCommunicator communicator, CommandExecutor executor, LogContoller logger) {
        super(communicator, executor, logger);
        this.manager = new PartitionManager(executor);
    }

    @Override
    public synchronized void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        
        if (request.getAction() == ActionsEnum.GET) {
            response.setResponse(manager.getPartitions());
        }
        
        communicator.sendResponse(response);
    }

}
