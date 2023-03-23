package lagatrix.server.exceptions.manager;

/**
 * This exception repersents all errors related to the partitions.
 *
 * @author javier
 * @since 1.0
 */
public class PartitionException extends ManagerException{
    
    public PartitionException(String component) {
        super(String.format("Can't obtain partition component: %s", component));
    }
    
}
