package lagatrix.server.connection.requester;

import lagatrix.server.connection.communicators.AESCommunicator;
import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.connection.Request;
import lagatrix.server.entities.connection.Response;
import lagatrix.server.exceptions.LagatrixException;
import lagatrix.server.file.log.LogContoller;
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

    public RequestCPU(AESCommunicator communicator, CommandExecutor executor, LogContoller logger) {
        super(communicator, executor, logger);
        manager = new CPUManager(executor);
    }

    @Override
    public synchronized void determineRequest(Request request) throws LagatrixException {
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
                logger.info(communicator.getClientIp(), request.getAction(), "CPU");
            }
        }

        communicator.sendResponse(response);
    }
}
