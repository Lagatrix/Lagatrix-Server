package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.manager.CPUManager;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class determine the request of CPU.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RequestCPU extends RequestManager {

    private CPUManager manager;

    public RequestCPU(AESCommunicator communicator, CommandExecutor executor, boolean isRaspberry) {
        super(communicator, executor);
        manager = CPUManager(executor, isRaspberry);
    }

    @Override
    public void determineRequest(Request request) throws LagatrixException {
        Response response = new Response();
        char c;

        // Determine if obtain CPU info, temperature or use.
        if (request.getAction() == ActionsEnum.GET) {
            if (request.getParams().length > 0) {
                c = (char) request.getParams()[0];

                if (c == 'U' || c == 'u') {
                    response.setResponse((Float) manager.obtainUse());
                } else if (c == 'T' || c == 't') {
                    response.setResponse((Float) manager.obtainTemperature());
                }
            } else {
                response.setResponse(manager.obtainCPU());
            }
        }

        communicator.sendResponse(response);
    }

    private CPUManager CPUManager(CommandExecutor executor, boolean raspberry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
