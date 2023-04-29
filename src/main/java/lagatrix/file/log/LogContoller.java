package lagatrix.file.log;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import lagatrix.entities.actions.ActionsEnum;
import lagatrix.exceptions.LagatrixException;
import lagatrix.exceptions.file.CantCreateFile;
import lagatrix.exceptions.file.FileException;
import lagatrix.exceptions.file.FileIOException;
import static lagatrix.exceptions.file.FileIOException.getMessageIO;

/**
 * This class manage the log file of the program.
 *
 * @author javierfh03
 * @since 0.2
 */
public class LogContoller {
    
    private Logger logger;
    private File logFile;

    public LogContoller() {
        LocalDateTime date = LocalDateTime.now();  
        
        logFile = new File(String.format("/var/log/lagatrix/lagatrix_%s.log", 
                date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"))));
        logger = Logger.getLogger("Lagatrix");
    }

    /**
     * This method open the log file.
     * 
     * @throws FileException If have problem when open the file.
     */
    public void open() throws FileException {
        FileHandler fh;
        
        create();
        
        try {
            fh = new FileHandler(logFile.getAbsolutePath());
            fh.setFormatter(new SimpleFormatter());
            
            logger.addHandler(fh);
        } catch (IOException ex) {
            throw new FileIOException(getMessageIO(logFile.getName()));
        } catch (SecurityException ex) {
             throw new FileException("Header security error in logger");
        }
    }

    /**
     * Display an action of the client in the program.
     * 
     * @param ip The ip of the client.
     * @param action The action who realice.
     * @param request The resource who use.
     */
    public synchronized void info(String ip, ActionsEnum action, String request) {
        info(ip, action, request, null);
    }
    
    /**
     * Display an action of the client in the program.
     * 
     * @param ip The ip of the client.
     * @param action The action who realice.
     * @param request The resource who use.
     * @param description The description of action, is optional.
     */
    public synchronized void info(String ip, ActionsEnum action, String request, String description) {
        String base = String.format("[%s] Make an %s action in the %s", 
                ip, action.getCorrectName(), request);
        
        if (description == null) {
            logger.info(base);
        } else {
            logger.info(String.format("%s, %s", base, description));
        }
    }
    
    /**
     * Display exception if the error is minor.
     * 
     * @param ip The ip of client who fail.
     * @param exception The exception who appear.
     */
    public synchronized void warning(String ip, LagatrixException exception) {
        logger.warning(String.format("[%s] %s", ip, exception.toString()));
    }
    
    /**
     * Display exception if the error blocks the program.
     * 
     * @param ip The ip of client who fail.
     * @param exception The exception who appear.
     */
    public void error(String ip, LagatrixException exception) {
        logger.severe(String.format("[%s] %s", ip, exception.toString()));
    }
    
    /**
     * Create the log file.
     * 
     * @throws CantCreateFile If can't create the log.
     */
    private void create() throws CantCreateFile {
        try {
            // Create the directory and create the new file.
            logFile.getParentFile().mkdirs();
            logFile.createNewFile();
        } catch (IOException ex) {
            throw new CantCreateFile(String.format("Can't create the log file because: %s", 
                    ex.getMessage()));
        }
    }
}
