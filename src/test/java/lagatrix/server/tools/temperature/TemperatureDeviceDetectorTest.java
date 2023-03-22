package lagatrix.server.tools.temperature;

import lagatrix.server.tools.hawrdare_status.DevicesEnum;
import lagatrix.server.tools.hawrdare_status.TemperatureDeviceDetector;
import lagatrix.server.manager.hardware_status.temperature.cpu.NormalCPUTemperature;
import lagatrix.server.manager.hardware_status.temperature.cpu.RaspberryPiTemperature;
import lagatrix.server.tools.command.CommandExecutor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This test checks TemperatureDeviceDetector class.
 * 
 * @author javier
 * @since 1.0
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
