package lagatrix.server.tools.temperature;

import lagatrix.server.exceptions.manager.UseException;
import lagatrix.server.manager.hardware_status.use.UseCPU;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.hawrdare_status.DevicesEnum;
import lagatrix.server.tools.hawrdare_status.UseDeviceDetector;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * This test checks UseDeviceDetector class.
 * 
 * @author javier
 */
public class UseDeviceDetectorTest {
    
    private CommandExecutor executor;
    
    public UseDeviceDetectorTest() {
        executor = new CommandExecutor();
    }
    
    @Test
    public void getRaspberryCPUTempManagerTest() throws UseException{
        UseDeviceDetector detector = new UseDeviceDetector(DevicesEnum.CPU, executor);
        
        assertEquals(UseCPU.class, detector.getManager().getClass());
    }
}
