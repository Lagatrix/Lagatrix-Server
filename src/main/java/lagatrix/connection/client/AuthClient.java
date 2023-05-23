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
        String user, password;
        
        while (attempt > 0) {
            request = communicator.obtainRequest();
            
            user = (String) request.getParams()[0];
            password = (String) request.getParams()[1];
            
            if (manager.authUser(user, password)){
                if (!rootAccess) {
                    if (user.equals("root")) {
                        communicator.sendResponse(new Response("Root no puede entrar", false));
                        continue;
                    }
                }
                
                if (manager.isRoot(user) || user.equals("root")){
                    communicator.sendResponse(new Response("Login correcto", true));
                    return true;
                } else {
                    communicator.sendResponse(new Response("El usuario no tiene permisos de root", false));
                }
            } else {
                communicator.sendResponse(new Response("Usuario o clave no correcta", false));
            }
            
            attempt--;
        }
        
        return false;
    }
    
}
