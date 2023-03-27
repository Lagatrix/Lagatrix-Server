package lagatrix.server.manager;

import java.util.LinkedHashSet;
import java.util.Set;
import lagatrix.server.entities.dto.process.UnixProcess;
import lagatrix.server.exceptions.manager.process.ProcessException;
import lagatrix.server.manager.information.process.ProcessInfo;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class obtain information of process in Linux.
 *
 * @author javier
 * @since 1.0
 */
public class ProcessManager {
    
    private ProcessInfo information;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the class.
     */
    public ProcessManager(CommandExecutor executor) {
        this.information = new ProcessInfo(executor);
    }
    
    /**
     * This method obtain all process of system.
     * 
     * @return A set with process.
     * @throws ProcessException If no process is obtained, it will be taken 
     * as an error.
     */
    public Set<UnixProcess> getProcess() throws ProcessException {
        Set<UnixProcess> processs = new LinkedHashSet<>();
        UnixProcess process;
        int index = 1;
        
        while (true) {
            try {
                process = new UnixProcess();
                
                process.setCommand(information.obtainCommand(index));
                process.setPID(information.obtainPID(index));
                process.setUseCPU(information.obtainUseCPU(index));
                process.setUseRAM(information.obtainUseRAM(index));
                process.setUsername(information.obtainUser(index));
                
                processs.add(process);
                index++;
            } catch (ProcessException e) {
                if (processs.isEmpty()){
                    throw e;
                } else {
                    return processs;
                }
            }
        }
    }
}
