package lagatrix.server.exceptions.file;

import lagatrix.server.exceptions.LagatrixException;

/**
 * This exception is used in file exceptions.
 *
 * @author javierfh03
 * @since 0.2
 */
public class FileException extends LagatrixException {

    public FileException(String description) {
        super(description);
    }

}
