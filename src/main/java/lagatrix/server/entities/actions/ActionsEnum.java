package lagatrix.server.entities.actions;

/**
 * This enum represents the actions who can do it.
 * 
 * @author javierfh03
 * @since 0.1
 */
public enum ActionsEnum {
    INSERT("insert"),
    GET("get information"),
    DELETE("delete"),
    MODIFY("modify");
    
    private String correctName;

    private ActionsEnum(String correctName) {
        this.correctName = correctName;
    }

    public String getCorrectName() {
        return correctName;
    }
}
