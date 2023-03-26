package lagatrix.server.manager.information.hardware.hardware_status.use;

import lagatrix.server.exceptions.manager.hardware.status.UseException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class obtain the use of CPU with ps aux.
 * 
 * @author javier
 * @since 1.0
 */
public class UseCPU extends UseManager {

    public UseCPU(CommandExecutor executor) {
        super.executor = executor;
    }

    @Override
    public float obtainUse() throws UseException {
        return executeUseCommand("ps aux --no-headers | awk '{s+=$3}END{print s*0.10}'", "CPU");
    }

}
