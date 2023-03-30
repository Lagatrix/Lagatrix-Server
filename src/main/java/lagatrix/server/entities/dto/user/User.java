package lagatrix.server.entities.dto.user;

/**
 * THis entity represents an Linux user.
 *
 * @author javierfh03
 * @since 0.1
 */
public class User {
    private String username, home, shell, group, password;
    private boolean isRoot;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getShell() {
        return shell;
    }

    public void setShell(String shell) {
        this.shell = shell;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public boolean isIsRoot() {
        return isRoot;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", home=" + home + ", shell=" + shell + ", group=" + group + ", password=" + password + ", isRoot=" + isRoot + '}';
    }
}
