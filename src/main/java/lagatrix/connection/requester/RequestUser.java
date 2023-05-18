package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.entities.dto.user.User;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.manager.UserManager;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class determine the request of user.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestUser extends RequestManager {
    
    private UserManager manager;

    public RequestUser(AESCommunicator communicator, CommandExecutor executor, LogContoller logger) {
        super(communicator, executor, logger);
        this.manager = new UserManager(executor);
    }

    @Override
    public synchronized void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        String message = null;
        
        // Determine the request of user.
        switch (request.getAction()){
            case INSERT:
                manager.insertUser((User) request.getParams()[0]);
                message = String.format("the new user is %s", request.getParams()[0].toString());
                response.setCorrectResult(true);
                break;
            case GET:
                response.setResponse(manager.getUsers());
                break;
            case DELETE:
                manager.deleteUser((String) request.getParams()[0]);
                message = String.format("the user remomed is %s", request.getParams()[0].toString());
                response.setCorrectResult(true);
                break;
            case MODIFY:
                manager.modifyUser((String) request.getParams()[0], (User) request.getParams()[1]);
                message = String.format("the user who changed is %s", 
                        request.getParams()[0]);
                response.setCorrectResult(true);
                break;
        }
        
        makeLog(message, request.getAction(), "user");
        communicator.sendResponse(response);
    }

}
