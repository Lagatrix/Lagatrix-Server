package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.manager.CPUManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class determine the request of CPU.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestCPU extends RequestManager {

    private CPUManager manager;
    
    public RequestCPU(AESCommunicator communicator, CommandExecutor executor) {
        super(communicator, executor);
    }
    
    @Override
    public void determineRequest(Request request) throws LagatrixException {
        char c = (Character) request.getParams()[0];
        Response response;
        
        // Determine if obtain CPU info, temperature or use.
        switch (c) {  
            case 'U' | 'u':
                response = new Response((Float) manager.obtainUse());
                break;
                
            case 'T' | 't':
                response = new Response((Float) manager.obtainTemperature());
                break;
                
            default:
                response = new Response(manager.obtainCPU());
        }
        
        response.setCorrectResult(true);
        
        communicator.sendResponse(response);
    }
}
