package lagatrix.server;

import lagatrix.server.connection.OpenConnection;
import lagatrix.server.exceptions.manager.software.PackageManagerException;

public class Main {

    public static void main(String[] args) throws PackageManagerException {
        OpenConnection connection = new OpenConnection();
        
        connection.openFiles();
        connection.startConnection();
    }
}
