package lagatrix.exceptions.file;

/**
 * This exception reperents if can't create an file.
 *
 * @author javierfh03
 * @since 0.2
 */
public class CantCreateFile extends FileException {

    public CantCreateFile(String description) {
        super(description);
    }

}
