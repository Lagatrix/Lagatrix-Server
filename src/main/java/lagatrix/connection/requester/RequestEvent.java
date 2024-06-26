package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.entities.dto.event.Event;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.manager.EventManager;
import lagatrix.tools.command.CommandExecutor;

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
    public synchronized void determineRequest(Request request) throws LagatrixException {
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
                message = String.format("the event who removed %s", request.getParams()[0].toString());
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
        
        makeLog(message, request.getAction(), "event");
        communicator.sendResponse(response);
    }

}
