package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.entities.dto.event.Event;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.file.log.LogContoller;
import lagatrix.server.manager.EventManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class determine the request of event.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestEvent extends RequestManager {
    
    private EventManager manager;

    public RequestEvent(AESCommunicator communicator, CommandExecutor executor, LogContoller logger) {
        super(communicator, executor, logger);
        this.manager = new EventManager(executor);
    }
    
    @Override
    public void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        String message = null;
        
        // Determine the request of event.
        switch (request.getAction()){
            case INSERT:
                manager.insertEvent((Event) request.getParams()[0]);
                response.setCorrectResult(true);
                message = String.format("the new event is %s", request.getParams()[0].toString());
                break;
            case GET:
                response.setResponse(manager.getEvents());
                break;
            case DELETE:
                manager.deleteEvent((Event) request.getParams()[0]);
                message = String.format("the event who remomed %s", request.getParams()[0].toString());
                response.setCorrectResult(true);
                break;
            case MODIFY:
                manager.modifyEvent((Event) request.getParams()[0], (Event) request.getParams()[1]);
                message = String.format("the event who changed is %s to %s", 
                        request.getParams()[0].toString(), 
                        request.getParams()[1].toString());
                response.setCorrectResult(true);
                break;
        }
        
        logger.info(communicator.getClientIp(), request.getAction(), "event", message);
        communicator.sendResponse(response);
    }

}
