package lagatrix.server.connection.client;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.connection.requester.RequestCPU;
import lagatrix.server.connection.requester.RequestEvent;
import lagatrix.server.connection.requester.RequestGPU;
import lagatrix.server.connection.requester.RequestManager;
import lagatrix.server.connection.requester.RequestOS;
import lagatrix.server.connection.requester.RequestPackage;
import lagatrix.server.connection.requester.RequestPartiton;
import lagatrix.server.connection.requester.RequestProcess;
import lagatrix.server.connection.requester.RequestRAM;
import lagatrix.server.connection.requester.RequestUser;
import lagatrix.server.entities.components.PackageManagerComponents;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.entities.dto.event.Event;
import lagatrix.server.entities.dto.hardware.*;
import lagatrix.server.entities.dto.os.OSInformation;
import lagatrix.server.entities.dto.partition.Partition;
import lagatrix.server.entities.dto.process.UnixProcess;
import lagatrix.server.entities.dto.user.User;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.exceptions.NotSupportedOperation;
import lagatrix.server.exceptions.connection.ConnectionException;
import lagatrix.server.file.log.LogContoller;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class manage the client request.
 *
 * @author javierfh03
 * @since 0.2
 */
public class ClientManager extends Thread {
    
    private AESCommunicator communicator;
    private CommandExecutor executor;
    private LogContoller logger;
    private PackageManagerComponents packageManager;

    /**
     * Constructor of the class.
     * 
     * @param communicator The communicator with client.
     * @param logger The LogController to monitor the actions of the client.
     * @param executor The executor of commands.
     */
    public ClientManager(AESCommunicator communicator, LogContoller logger, CommandExecutor executor) {
        this.communicator = communicator;
        this.executor = executor;
        this.logger = logger;
    }

    public void setPackageManager(PackageManagerComponents packageManager) {
        this.packageManager = packageManager;
    }
    
    @Override
    public void run() {
        Request request;
        RequestManager manager;
        
        /* The loop of request, if the client send an null object request, 
        the communication ends. */
        while (true) {
            try {
                request = communicator.obtainRequest();
                
                if (request.getObjectWhoRequest() != null) {
                    manager = determineRequester(request.getObjectWhoRequest());
                    manager.determineRequest(request);
                } else {
                    communicator.closeClient();
                    break;
                }
            } catch (LagatrixException ex) {
                makeWarning(ex);
            }
        }
    }
    
    /**
     * This method determine the requester order by client.
     * 
     * @param classItem The entity who client request.
     * @return The requester.
     * @throws NotSupportedOperation If the classItem is not valid.
     */
    private synchronized RequestManager determineRequester(Class classItem) throws NotSupportedOperation {
        CPU.class.getSimpleName();
        
        // Determine the request.
        if (classItem.equals(CPU.class)){
            return new RequestCPU(communicator, executor, logger);
        } else if (classItem.equals(RAM.class)){
            return new RequestRAM(communicator, executor, logger);
        } else if (classItem.equals(OSInformation.class)){
            return new RequestOS(communicator, executor, logger);
        } else if (classItem.equals(GPU.class)){
            return new RequestGPU(communicator, executor, logger);
        } else if (classItem.equals(Partition.class)){
            return new RequestPartiton(communicator, executor, logger);
        } else if (classItem.equals(UnixProcess.class)){
            return new RequestProcess(communicator, executor, logger);
        } else if (classItem.equals(User.class)){
            return new RequestUser(communicator, executor, logger);
        } else if (classItem.equals(Event.class)){
            return new RequestEvent(communicator, executor, logger);
        } else if (classItem.equals(String.class)){
            return new RequestPackage(communicator, executor, packageManager, logger);
        }
        
        throw new NotSupportedOperation(String.format("The request of class '%s' is not valid", 
                classItem.getSimpleName()));
    } 
    
    /**
     * This method make the warning if error ocurred.
     * 
     * @param ex The exception who raise.
     */
    private synchronized void makeWarning(LagatrixException ex) {
        logger.warning(communicator.getClientIp(), ex);
        try {
            communicator.sendResponse(new Response(ex.toString(), false));
        } catch (ConnectionException ex1) {
            logger.warning(communicator.getClientIp(), ex1);
        }
    }
    
}
