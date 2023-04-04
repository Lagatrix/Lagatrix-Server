package lagatrix.server.connection.client;

import java.net.Socket;
import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.connection.requester.RequestCPU;
import lagatrix.server.connection.requester.RequestGPU;
import lagatrix.server.connection.requester.RequestManager;
import lagatrix.server.connection.requester.RequestOS;
import lagatrix.server.connection.requester.RequestPackage;
import lagatrix.server.connection.requester.RequestPartiton;
import lagatrix.server.connection.requester.RequestProcess;
import lagatrix.server.connection.requester.RequestRAM;
import lagatrix.server.connection.requester.RequestUser;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.dto.event.Event;
import lagatrix.server.entities.dto.hardware.*;
import lagatrix.server.entities.dto.os.OSInformation;
import lagatrix.server.entities.dto.partition.Partition;
import lagatrix.server.entities.dto.process.UnixProcess;
import lagatrix.server.entities.dto.user.User;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.exceptions.NotSupportedOperation;
import lagatrix.server.manager.OSManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class manage the client request.
 *
 * @author javierfh03
 * @since 0.2
 */
public class ClientManager extends Thread {
    
    private Socket client;
    private AESCommunicator communicator;
    private CommandExecutor executor;
    private OSInformation osInfo;

    public ClientManager(Socket client, AESCommunicator communicator) {
        this.client = client;
        this.communicator = communicator;
        
        this.executor = new CommandExecutor();
    }
    
    @Override
    public void run() {
        Request request;
        RequestManager manager;
        
        // If can't obtain the os information.
        try {
            this.osInfo = new OSManager(executor).obtainOSInformation();
        } catch (LagatrixException ex) {
            System.exit(1);
        }
        
        /* The loop of request, if the client send an null object request, 
        the communication eds. */
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
    private RequestManager determineRequester(Class classItem) throws NotSupportedOperation {
        CPU.class.getSimpleName();
        
        // Determine the request.
        if (classItem.equals(CPU.class)){
            return new RequestCPU(communicator, executor);
        } else if (classItem.equals(RAM.class)){
            return new RequestRAM(communicator, executor);
        } else if (classItem.equals(OSInformation.class)){
            return new RequestOS(communicator, executor);
        } else if (classItem.equals(GPU.class)){
            return new RequestGPU(communicator, executor);
        } else if (classItem.equals(Partition.class)){
            return new RequestPartiton(communicator, executor);
        } else if (classItem.equals(UnixProcess.class)){
            return new RequestProcess(communicator, executor);
        } else if (classItem.equals(User.class)){
            return new RequestUser(communicator, executor);
        } else if (classItem.equals(Event.class)){
            return new RequestPackage(communicator, executor, osInfo.getPackageManager());
        }
        
        throw new NotSupportedOperation(String.format("The request of class '%s' is not valid with client %s", 
                classItem.getSimpleName(), client.getInetAddress().toString()));
    } 
    
}
