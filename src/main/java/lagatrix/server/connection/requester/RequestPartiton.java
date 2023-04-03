package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.manager.PartitionManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class determine the request of partitions.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestPartiton extends RequestManager {
    
    private PartitionManager manager;

    public RequestPartiton(AESCommunicator communicator, CommandExecutor executor) {
        super(communicator, executor);
        this.manager = new PartitionManager(executor);
    }

    @Override
    public void determineRequest(Request request) throws LagatrixException {
        Response response = new Response(manager.getPartitions());
        
        response.setCorrectResult(true);
        communicator.sendResponse(response);
    }

}
