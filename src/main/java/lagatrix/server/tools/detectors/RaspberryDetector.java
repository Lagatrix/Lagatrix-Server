package lagatrix.server.tools.detectors;

import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class determine if an system is raspberry pi.
 *
 * @author javierfh03
 * @since 0.2
 */
public class RaspberryDetector {
    
    /**
     * This method determine if an system is raspberry pi.
     * 
     * @param executor The command executor.
     * @return If is raspberry.
     */
    public static boolean isRaspberry(CommandExecutor executor) {
        boolean isRaspberry;
        
        try {
            executor.executeCommand("raspi-config");
            isRaspberry = true;
        } catch (CommandException ex) {
            isRaspberry = (ex.getStatusCode() != 127);
        }
        
        return isRaspberry;
    }
}
