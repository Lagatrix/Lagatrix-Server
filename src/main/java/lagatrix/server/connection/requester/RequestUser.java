package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.entities.dto.user.User;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.manager.UserManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class determine the request of user.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestUser extends RequestManager {
    
    private UserManager manager;

    public RequestUser(AESCommunicator communicator, CommandExecutor executor) {
        super(communicator, executor);
        this.manager = new UserManager(executor);
    }

    @Override
    public void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        
        // Determine the request of user.
        switch (request.getAction()){
            case INSERT:
                manager.insertUser((User) request.getParams()[0]);
                response.setCorrectResult(true);
                break;
            case GET:
                response = new Response(manager.getUsers());
                break;
            case DELETE:
                manager.deleteUser((String) request.getParams()[0]);
                response.setCorrectResult(true);
                break;
            case MODIFY:
                manager.modifyUser((String) request.getParams()[0], (User) request.getParams()[1]);
                response.setCorrectResult(true);
                break;
        }
        
        communicator.sendResponse(response);
    }

}
