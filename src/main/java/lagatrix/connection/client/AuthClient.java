package lagatrix.connection.client;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.connection.ConnectionException;
import lagatrix.manager.UserManager;
import lagatrix.tools.command.CommandExecutor;

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
    private boolean rootAccess;

    /**
     * The constructor of the class.
     * 
     * @param communicator The AES Communicator.
     * @param executor The command executor.
     * @param rootAccess If root can access.
     */
    public AuthClient(AESCommunicator communicator, CommandExecutor executor, boolean rootAccess) {
        this.communicator = communicator;
        this.executor = executor;
        this.attempt = 3;
        this.rootAccess = rootAccess;
    }
    
    /**
     * This methos make the login of the user, it have 3 attempts.
     * 
     * @return If auth the user.
     * @throws ConnectionException If have ant error connection..
     */
    public boolean makeLogin() throws ConnectionException {
        Request request;
        UserManager manager = new UserManager(executor);
        
        while (attempt > 0) {
            request = communicator.obtainRequest();
            
            if (manager.authUser((String) request.getParams()[0], (String) request.getParams()[1])){
                if (rootAccess) {
                    if (((String) request.getParams()[0]).equals("root")) {
                        communicator.sendResponse(new Response("Root can't access", true));
                        return true;
                    }
                }
                
                if (manager.isRoot((String) request.getParams()[0])){
                    communicator.sendResponse(new Response("Correct login", true));
                    return true;
                } else {
                    communicator.sendResponse(new Response("User not have root permissions", false));
                }
            } else {
                communicator.sendResponse(new Response("User or password is not valid", false));
            }
            
            attempt--;
        }
        return false;
    }
    
}
