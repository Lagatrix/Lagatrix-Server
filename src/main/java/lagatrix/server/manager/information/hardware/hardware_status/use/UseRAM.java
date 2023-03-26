package lagatrix.server.manager.information.hardware.hardware_status.use;

import lagatrix.server.exceptions.manager.hardware.status.UseException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class obtain the use of RAM with ps aux.
 *
 * @author javier
 * @since 1.0
 */
public class UseRAM extends UseManager{

    public UseRAM(CommandExecutor executor) {
        super.executor = executor;
    }

    @Override
    public float obtainUse() throws UseException {
        return executeUseCommand("ps aux --no-headers | awk '{s+=$4}END{print s}'", "RAM");
    }

}
