package lagatrix.connection.requester;

import lagatrix.connection.communicators.AESCommunicator;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.entities.connection.Request;
import lagatrix.entities.connection.Response;
import lagatrix.exceptions.LagatrixException;
import lagatrix.file.log.LogContoller;
import lagatrix.manager.CPUManager;
import lagatrix.tools.command.CommandExecutor;

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
