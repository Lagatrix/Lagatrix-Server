package lagatrix.exceptions.file;

import java.io.IOException;

/**
 * This exception repersents a file with I/O problems.
 *
 * @author javierfh03
 * @since 0.2
 */
public class FileIOException extends FileException {

    public FileIOException(String description) {
        super(description);
    }

    /**
     * This method obtain the error message.
     * 
     * @param file The file who have I/O error.
     * @return The message.
     */
    public static String getMessageIO(String file) {
        return String.format("Problem I/O in file %s", file);
    }
}
