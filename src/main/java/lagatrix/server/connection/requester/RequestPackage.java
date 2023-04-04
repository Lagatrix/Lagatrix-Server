package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.components.PackageManagerComponents;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.manager.PackageManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class determine the request of package manager.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestPackage extends RequestManager {
    
    private PackageManager manager;

    public RequestPackage(AESCommunicator communicator, CommandExecutor executor, PackageManagerComponents component) {
        super(communicator, executor);
        this.manager = new PackageManager(executor, component);
    }

    @Override
    public void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        
        // Determine the request of package manager.
        switch (request.getAction()) {
            case INSERT:
                manager.installPackage((String) request.getParams()[0]);
                break;
            case MODIFY:
                switch ((String) request.getParams()[0]){
                    case "update":
                        manager.updateRepositories();
                        break;
                    case "upgrade":
                        manager.upgradePackages();
                        break;
                    default:
                        manager.upgradePackage((String) request.getParams()[0]);
                }
                break;
            case DELETE:
                manager.uninstallPackage((String) request.getParams()[0]);
                break;
            case GET:
                response.setCorrectResult((Boolean) manager.isPackageInstaled((String) request.getParams()[0]));
                break;
        }
        
        communicator.sendResponse(response);
    }

}
