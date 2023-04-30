package lagatrix.manager;

import java.util.LinkedHashSet;
import java.util.Set;
import lagatrix.entities.dto.process.UnixProcess;
import lagatrix.exceptions.manager.process.ProcessException;
import lagatrix.manager.deletion.process.ProcessDeletion;
import lagatrix.manager.information.process.ProcessInfo;
import lagatrix.tools.command.CommandExecutor;

/**
 * This class obtain information of process in Linux.
 *
 * @author javierfh03
 * @since 0.1
 */
public class ProcessManager {
    
    private ProcessInfo information;
    private ProcessDeletion deletion;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the class.
     */
    public ProcessManager(CommandExecutor executor) {
        this.information = new ProcessInfo(executor);
        this.deletion = new ProcessDeletion(executor);
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
    
    /**
     * This method kill a process.
     * 
     * @param PID The PID of process to kill.
     * @throws ProcessException If no process is obtained, it will be taken 
     * as an error.
     */
    public void killProcess (int PID) throws ProcessException{
        deletion.killProcess(PID);
    }
}
