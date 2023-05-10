package lagatrix.connection.client;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.connection.requester.RequestCPU;
import lagatrix.connection.requester.RequestEvent;
import lagatrix.connection.requester.RequestGPU;
import lagatrix.connection.requester.RequestManager;
import lagatrix.connection.requester.RequestOS;
import lagatrix.connection.requester.RequestPackage;
import lagatrix.connection.requester.RequestPartiton;
import lagatrix.connection.requester.RequestProcess;
import lagatrix.connection.requester.RequestRAM;
import lagatrix.connection.requester.RequestUser;
import lagatrix.entities.components.PackageManagerComponents;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.entities.dto.event.Event;
import lagatrix.entities.dto.hardware.CPU;
import lagatrix.entities.dto.hardware.GPU;
import lagatrix.entities.dto.hardware.RAM;
import lagatrix.entities.dto.os.OSInformation;
import lagatrix.entities.dto.partition.Partition;
import lagatrix.entities.dto.process.UnixProcess;
import lagatrix.entities.dto.user.User;
import lagatrix.exceptions.LagatrixException;
import lagatrix.exceptions.NotSupportedOperation;
import lagatrix.exceptions.connection.ConnectionException;
import lagatrix.exceptions.connection.ConnectionInOutException;
import lagatrix.file.log.LogContoller;
import lagatrix.tools.command.CommandExecutor;

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
     * @param packageManager The package manager of the system.
     */
    public ClientManager(AESCommunicator communicator, LogContoller logger, CommandExecutor executor, PackageManagerComponents packageManager) {
        this.communicator = communicator;
        this.executor = executor;
        this.logger = logger;
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
                synchronized (this) {
                    request = communicator.obtainRequest();
                }

                if (request.getObjectWhoRequest() != null) {
                    manager = determineRequester(request.getObjectWhoRequest());
                    manager.determineRequest(request);
                } else {
                    communicator.sendResponse(new Response(null, true));
                    communicator.close();
                    break;
                }
            } catch (ConnectionInOutException ex) {
                makeWarning(ex);
                break;
            } catch (LagatrixException ex) {
                makeWarning(ex);
            } catch (RuntimeException ex) {
                makeWarning(new LagatrixException(ex.getMessage()));
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
        // Determine the request.
        if (classItem.equals(CPU.class)) {
            return new RequestCPU(communicator, executor, logger);
        } else if (classItem.equals(RAM.class)) {
            return new RequestRAM(communicator, executor, logger);
        } else if (classItem.equals(OSInformation.class)) {
            return new RequestOS(communicator, executor, logger);
        } else if (classItem.equals(GPU.class)) {
            return new RequestGPU(communicator, executor, logger);
        } else if (classItem.equals(Partition.class)) {
            return new RequestPartiton(communicator, executor, logger);
        } else if (classItem.equals(UnixProcess.class)) {
            return new RequestProcess(communicator, executor, logger);
        } else if (classItem.equals(User.class)) {
            return new RequestUser(communicator, executor, logger);
        } else if (classItem.equals(Event.class)) {
            return new RequestEvent(communicator, executor, logger);
        } else if (classItem.equals(PackageManagerComponents.class)) {
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
