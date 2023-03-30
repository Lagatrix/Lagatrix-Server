package lagatrix.server.tools.detectors;

import lagatrix.server.tools.detectors.DevicesEnum;
import lagatrix.server.tools.detectors.TemperatureDeviceDetector;
import lagatrix.server.manager.information.hardware.hardware_status.temperature.cpu.NormalCPUTemperature;
import lagatrix.server.manager.information.hardware.hardware_status.temperature.cpu.RaspberryPiTemperature;
import lagatrix.server.tools.command.CommandExecutor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This test checks TemperatureDeviceDetector class.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class TemperatureDeviceDetectorTest {
    
    private CommandExecutor executor;

    public TemperatureDeviceDetectorTest() {
        executor = new CommandExecutor();
    }
    
    @Test
    public void getRaspberryCPUTempManagerTest() {
        TemperatureDeviceDetector detector = new TemperatureDeviceDetector(DevicesEnum.CPU, executor, true);
        
        assertEquals(RaspberryPiTemperature.class, detector.getManager().getClass());
    }
    
    @Test
    public void getNormalCPUTempManagerTest(){
        TemperatureDeviceDetector detector = new TemperatureDeviceDetector(DevicesEnum.CPU, executor, false);
        
        assertEquals(NormalCPUTemperature.class, detector.getManager().getClass());
    }
}
