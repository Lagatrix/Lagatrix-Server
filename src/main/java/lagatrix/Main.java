package lagatrix;

import lagatrix.connection.OpenConnection;
import lagatrix.exceptions.manager.software.PackageManagerException;

public class Main {

    public static void main(String[] args) throws PackageManagerException {
        OpenConnection connection = new OpenConnection();
        
        connection.openFiles();
        connection.startConnection();
    }
}
