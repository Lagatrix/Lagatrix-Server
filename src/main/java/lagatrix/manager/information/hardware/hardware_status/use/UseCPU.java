package lagatrix.manager.information.hardware.hardware_status.use;

import lagatrix.exceptions.manager.hardware.status.UseException;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class obtain the use of CPU with ps aux.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class UseCPU extends UseManager {

    public UseCPU(CommandExecutor executor) {
        super.executor = executor;
    }

    @Override
    public float obtainUse() throws UseException {
        return executeUseCommand("ps aux --no-headers | awk '{s+=$3}END{print s*0.10}'");
    }

}
