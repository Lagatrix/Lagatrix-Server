package lagatrix.server.connection.client;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.connection.BadClassFormatException;
import lagatrix.server.exceptions.connection.ObtainRequestException;
import lagatrix.server.exceptions.connection.SendResponseException;
import lagatrix.server.manager.UserManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class auth de client with socket.
 *
 * @author javier
 * @since 0.2
 */
public class AuthClient {
    
    private AESCommunicator communicator;
    private CommandExecutor executor;
    private int attempt;

    /**
     * The constructor of the class.
     * 
     * @param communicator The AES Communicator.
     * @param executor The command executor.
     */
    public AuthClient(AESCommunicator communicator, CommandExecutor executor) {
        this.communicator = communicator;
        this.executor = executor;
        this.attempt = 3;
    }
    
    /**
     * This methos make the login of the user, it have 3 attempts.
     * 
     * @throws ObtainRequestException If can't obtain the request of client.
     * @throws BadClassFormatException If the request is invalid.
     * @throws SendResponseException If have an problem when send the response.
     */
    public void makeLogin() throws ObtainRequestException, BadClassFormatException, SendResponseException {
        Request request;
        UserManager manager = new UserManager(executor);
        
        while (attempt > 0) {
            request = communicator.obtainRequest();
            
            if (manager.authUser((String) request.getParams()[0], (String) request.getParams()[1])){
                if (manager.isRoot((String) request.getParams()[0])){
                    communicator.sendResponse(new Response("Correct login", true));
                } else {
                    communicator.sendResponse(new Response("User not have root permissions", false));
                }
            } else {
                communicator.sendResponse(new Response("User or password is not valid", false));
            }
            
            attempt--;
        }
    }
    
}
