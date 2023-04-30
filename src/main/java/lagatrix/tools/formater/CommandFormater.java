package lagatrix.tools.formater;

/**
 * This class is used to format command with special characters.
 *
 * @author javierfh03
 * @since 0.3
 */
public class CommandFormater {
    
    /**
     * Fortam the command if have special characters.
     * 
     * @param command The command who use.
     * @return The command formated.
     */
    public static String sedEventFormater(String command) {
        StringBuilder sb = new StringBuilder("");
        char commandChar;
        
        for (int i = 0; i < command.length(); i++) {
            commandChar = command.charAt(i);
            
            if (commandChar == '/' || commandChar == '*') {
                sb.append('\\');
            }
            
            sb.append(commandChar);
        }
        
        return sb.toString();
    }
    
}
