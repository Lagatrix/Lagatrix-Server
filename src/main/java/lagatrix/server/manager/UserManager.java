package lagatrix.server.manager;

import java.util.LinkedHashSet;
import java.util.Set;
import lagatrix.server.entities.dto.user.User;
import lagatrix.server.exceptions.manager.user.UserException;
import lagatrix.server.manager.deletion.user.UserDeletion;
import lagatrix.server.manager.information.user.AuthUser;
import lagatrix.server.manager.information.user.RootPermissionInfo;
import lagatrix.server.manager.information.user.UserInfo;
import lagatrix.server.manager.insertion.user.UserInsertion;
import lagatrix.server.manager.modificator.user.PasswordModificator;
import lagatrix.server.manager.modificator.user.UserModificator;
import lagatrix.server.tools.command.CommandExecutor;

/**
 * This class manage users of Linux system.
 *
 * @author javierfh03
 * @since 0.1
 */
public class UserManager {
    
    private UserDeletion deletion;
    private UserInfo information;
    private UserModificator modificator;
    private UserInsertion insertion;
    private PasswordModificator password;
    private RootPermissionInfo root;
    private AuthUser auth;

    /**
     * The constructor of the class
     *
     * @param executor The executor command.
     */
    public UserManager(CommandExecutor executor) {
        this.deletion = new UserDeletion(executor);
        this.information = new UserInfo(executor);
        this.modificator = new UserModificator(executor);
        this.insertion = new UserInsertion(executor);
        this.password = new PasswordModificator(executor);
        this.root = new RootPermissionInfo(executor);
        this.auth = new AuthUser(executor);
    }
    
    /**
     * This methos obtain usables users of the system.
     * 
     * @return An set with users.
     * @throws UserException If can't obtain the users.
     */
    public Set<User> getUsers() throws UserException{
        Set<User> users = new LinkedHashSet<>();
        User user;
        int index = 1;
        
        while (true) {
            user = new User();
            
            try {
                user.setUsername(information.obtainUsername(index));
                user.setGroup(information.obtainMainGroup(index));
                user.setHome(information.obtainHomePath(index));
                user.setIsRoot(root.isRoot(user.getUsername()));
                user.setShell(information.obtainShell(index));
                
                users.add(user);
                index++;
            } catch (UserException ex) {
                if (users.isEmpty()) {
                    throw ex;
                } else {
                    return users;
                }
            }
        }
    }
    
    /**
     * This method insert user in the system.
     * 
     * @param user The user who insert.
     * @throws UserException If can't insert the user.
     */
    public void insertUser(User user) throws UserException {
        insertion.insertUser(user.getUsername(), user.getHome(), user.getShell(), user.getGroup());
        password.modifyPassword(user.getUsername(), user.getPassword());
    }
    
    /**
     * This method modify an user in the system.
     * 
     * @param username The name of user who modify.
     * @param newUser The changes in user.
     * @throws UserException If can't modify the user.
     */
    public void modifyUser(String username, User newUser) throws UserException {
        if (newUser.getPassword() != null){
            password.modifyPassword(username, newUser.getPassword());
        }
        
        modificator.modifyMainGroup(username, newUser.getGroup());
        modificator.modifyShell(username, newUser.getShell());
        modificator.modifyHome(username, newUser.getHome());
        modificator.modifyUsername(username, newUser.getUsername());
    }
    
    /**
     * This method delete an user of the system.
     * 
     * @param username The username of user who delete.
     * @throws UserException If can't delete the user.
     */
    public void deleteUser(String username) throws UserException {
        deletion.deleteUser(username);
    }
    
    /**
     * This method auth an user.
     * 
     * @param username The user to check.
     * @param password The password of user.
     * @return If the auth is correct.
     */
    public boolean authUser(String username, String password) {
        return auth.authUser(username, password);
    }
}
