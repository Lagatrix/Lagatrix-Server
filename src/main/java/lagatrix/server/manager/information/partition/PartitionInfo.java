package lagatrix.server.manager.information.partition;

import lagatrix.server.entities.actions.ActionsEnum;
import lagatrix.server.entities.components.PartitionComponents;
import lagatrix.server.exceptions.command.CommandException;
import lagatrix.server.exceptions.manager.partition.PartitionException;
import lagatrix.server.tools.command.CommandExecutor;
import lagatrix.server.tools.command.CommandResponse;

/**
 * This class obtain the information of a partition in Linux with df command.
 *
 * @author javierfh03
 * @since 0.1
 */
public class PartitionInfo {
    
    private CommandExecutor executor;

    /**
     * The constructor of the class
     *
     * @param executor The executor of the commands.
     */
    public PartitionInfo(CommandExecutor executor) {
        this.executor = executor;
    }

    /**
     * This method obtain the file system of partition.
     * 
     * @param numPartition The num of partiton who get the information.
     * @return The file system of partiton.
     * @throws PartitionException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainFileSystem(int numPartition) throws PartitionException{
        return executeCommand(numPartition, PartitionComponents.FILE_SYSTEM).getFirstLine();
    }
    
    /**
     * This method obtain the size of partition.
     * 
     * @param numPartition The num of partiton who get the information.
     * @return The size of partiton.
     * @throws PartitionException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainSize(int numPartition) throws PartitionException{
        return executeCommand(numPartition, PartitionComponents.SIZE).getFirstLine();
    }
    
    /**
     * This method obtain the avatible capacity of partition.
     * 
     * @param numPartition The num of partiton who get the information.
     * @return The avatible capacity of partiton.
     * @throws PartitionException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainAvatible(int numPartition) throws PartitionException{
        return executeCommand(numPartition, PartitionComponents.AVATIBLE).getFirstLine();
    }
    
    /**
     * This method obtain the percentage of use of partition.
     * 
     * @param numPartition The num of partiton who get the information.
     * @return The percentage use of partiton.
     * @throws PartitionException If a problem occurs with the execution of the 
     * command.
     */
    public int obtainPercentageUse(int numPartition) throws PartitionException{
        return Integer.parseInt(executeCommand(numPartition, PartitionComponents.PERCENTAGE_USE)
                .getFirstLine().replace("%", ""));
    }
    
    /**
     * This method obtain the used storage of partition.
     * 
     * @param numPartition The num of partiton who get the information.
     * @return The used storage of partition.
     * @throws PartitionException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainUsedStorage(int numPartition) throws PartitionException{
        return executeCommand(numPartition, PartitionComponents.USED).getFirstLine();
    }
    
    /**
     * This method obtain the path of mount of partition.
     * 
     * @param numPartition The num of partiton who get the information.
     * @return The path of mount.
     * @throws PartitionException If a problem occurs with the execution of the 
     * command.
     */
    public String obtainMountIn(int numPartition) throws PartitionException{
        return executeCommand(numPartition, PartitionComponents.MOUNT_IN).getFirstLine();
    }
    
    /**
     * This method exec the df command. Gets the component you want to get.
     * 
     * @param numPartition The num of partiton who get the information.
     * @param component The component who want to get.
     * @return The response of command.
     * @throws PartitionException If a problem occurs with the execution of the 
     * command.
     */
    private CommandResponse executeCommand(int numPartition, PartitionComponents component) throws PartitionException {
        String command = String.format("df -h | awk '{print $%d}' | sed -n %dp", component.getValue(), ++numPartition);
        String msgError = PartitionException.getMessage(this.getClass(), component.getName(), ActionsEnum.GET);
        CommandResponse response = null;
        
        try {
            response = executor.executeCommand(command); 
        } catch (CommandException ex) {
            throw new PartitionException(msgError);
        }
        
        // Check if the command not have output.
        if (response.getFirstLine().length() < 1){
            throw new PartitionException(msgError);
        }
        
        return response;
    }
}
