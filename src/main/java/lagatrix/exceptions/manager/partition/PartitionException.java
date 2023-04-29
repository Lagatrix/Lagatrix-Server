package lagatrix.exceptions.manager.partition;

import lagatrix.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the partitions.
 *
 * @author javier
 * @since 1.0
 */
public class PartitionException extends ManagerException{
    
    public PartitionException(String errorMessage) {
        super(errorMessage);
    }
    
}
