package lagatrix.server.tools.detectors;

import lagatrix.server.manager.information.hardware.hardware_status.use.UseCPU;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.detectors.DevicesEnum;
import lagatrix.server.tools.detectors.UseDeviceDetector;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * This test checks UseDeviceDetector class.
 * 
 * @author javierfh03
 * @since 0.1
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
