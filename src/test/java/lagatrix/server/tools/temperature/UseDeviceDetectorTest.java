package lagatrix.server.tools.temperature;

import lagatrix.server.manager.information.hardware.hardware_status.use.UseCPU;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.hawrdare_status.DevicesEnum;
import lagatrix.server.tools.hawrdare_status.UseDeviceDetector;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * This test checks UseDeviceDetector class.
 * 
 * @author javier
 * @since 1.0
 */
public class UseDeviceDetectorTest {
    
    private CommandExecutor executor;
    
    public UseDeviceDetectorTest() {
        executor = new CommandExecutor();
    }
    
    @Test
    public void getRaspberryCPUTempManagerTest() {
        UseDeviceDetector detector = new UseDeviceDetector(DevicesEnum.CPU, executor);
        
        assertEquals(UseCPU.class, detector.getManager().getClass());
    }
}
