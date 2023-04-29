package lagatrix.exceptions.file;

/**
 * This error represents if the config file have not valid fields.
 *
 * @author javierfh03
 * @since 0.2
 */
public class InvalidConfException extends FileException {

    public InvalidConfException(String description) {
        super(description);
    }

}
