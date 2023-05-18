package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.connection.Request;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class is used as a template to create the request handling classes.
 *
 * @author javier
 * @since 0.2
 */
public abstract class RequestManager {
    
    protected AESCommunicator communicator;
    protected CommandExecutor executor;
    protected LogContoller logger;

    /**
     * The constructor of the class.
     * 
     * @param communicator The communicator to communicate with client.
     * @param executor The command executor.
     * @param logger The logger controller to save the actions.
     */
    public RequestManager(AESCommunicator communicator, CommandExecutor executor, LogContoller logger) {
        this.communicator = communicator;
        this.executor = executor;
        this.logger = logger;
    }
    
    /**
     * This method determine what actions the class should do based on the 
     * request.
     * 
     * @param request The request to process.
     * @throws LagatrixException If an error ocurrs.
     */
    public abstract void determineRequest(Request request) throws LagatrixException;
    
    /**
     * Make the log if the client make an action.
     * 
     * @param message The massage who display.
     * @param action The action who make.
     * @param request The request class who invoque it.
     */
    public void makeLog(String message, ActionsEnum action, String request) {
        if (action != ActionsEnum.GET) {
            logger.info(communicator.getClientIp(), action, request, message);
        }
    }
}
