package lagatrix.server.entities.actions;

import java.io.Serializable;

/**
 * This enum represents the actions who can do it.
 * 
 * @author javierfh03
 * @since 0.1
 */
public enum ActionsEnum implements Serializable {
    INSERT("insert"),
    GET("get information"),
    DELETE("delete"),
    MODIFY("modify"),
    OPEN("open"),
    CLOSE("close"),
    SEND("send response"),
    RECEIVE("receive request");
    
    private String correctName;

    private ActionsEnum(String correctName) {
        this.correctName = correctName;
    }

    public String getCorrectName() {
        return correctName;
    }
}
