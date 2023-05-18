package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.components.PackageManagerComponents;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.manager.PackageManager;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class determine the request of package manager.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestPackage extends RequestManager {
    
    private PackageManager manager;

    public RequestPackage(AESCommunicator communicator, CommandExecutor executor, PackageManagerComponents component, LogContoller logger) {
        super(communicator, executor, logger);
        this.manager = new PackageManager(executor, component);
    }

    @Override
    public synchronized void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        String message = null;
        
        // Determine the request of package manager.
        switch (request.getAction()) {
            case INSERT:
                manager.installPackage((String) request.getParams()[0]);
                response.setCorrectResult(true);
                message = String.format("it install this package: %s", (String) request.getParams()[0]);
                break;
            case MODIFY:
                switch ((String) request.getParams()[0]){
                    case "update":
                        manager.updateRepositories();
                        message = "update the repositories";
                        break;
                    case "upgrade":
                        manager.upgradePackages();
                        message = "upgrade the repositories";
                        break;
                    default:
                        manager.upgradePackage((String) request.getParams()[0]);
                        message = String.format("it update this package: %s", (String) request.getParams()[0]);
                }
                response.setCorrectResult(true);
                break;
            case DELETE:
                manager.uninstallPackage((String) request.getParams()[0]);
                response.setCorrectResult(true);
                message = String.format("it remove this package: %s", (String) request.getParams()[0]);
                break;
            case GET:
                response.setResponse((Boolean) manager.isPackageInstaled((String) request.getParams()[0]));
                break;
        }
        
        makeLog(message, request.getAction(), "package manager");
        communicator.sendResponse(response);
    }

}
