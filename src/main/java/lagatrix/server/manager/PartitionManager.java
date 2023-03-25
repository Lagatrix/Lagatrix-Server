package lagatrix.server.manager;

import java.util.LinkedHashSet;
import java.util.Set;
import lagatrix.server.entities.devices.Partition;
import lagatrix.server.exceptions.manager.PartitionException;
import lagatrix.server.manager.information.partition.PartitionInfo;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class obtain the partitions of Linux.
 *
 * @author javier
 * @since 1.0
 */
public class PartitionManager {
    
    private PartitionInfo information;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the class.
     */
    public PartitionManager(CommandExecutor executor) {
        this.information = new PartitionInfo(executor);
    }
    
    /**
     * This method obtain all partitions of system.
     * 
     * @return A set with partitions.
     * @throws PartitionException If no partition is obtained, it will be taken 
     * as an error.
     */
    public Set<Partition> getPartitions() throws PartitionException{
        Set<Partition> partitions = new LinkedHashSet<>();
        Partition partition;
        int index = 1;
        
        while (true) {
            try {
                partition = new Partition();
                
                partition.setAvatible(information.obtainAvatible(index));
                partition.setFileSystem(information.obtainFileSystem(index));
                partition.setMountPath(information.obtainMountIn(index));
                partition.setPercernage(information.obtainPercentageUse(index));
                partition.setSize(information.obtainSize(index));
                partition.setUsedStorage(information.obtainUsedStorage(index));
                
                partitions.add(partition);
                index++;
            } catch (PartitionException e) {
                if (partitions.isEmpty()){
                    throw e;
                } else {
                    return partitions;
                }
            }
        }
    }
}
