package lagatrix.server.entities.connection;

import lagatrix.server.entities.actions.ActionsEnum;

/**
 * This class is used to send requests to the server.
 *
 * @author javierfh03
 * @since 0.2
 */
public class Request {
    
    private ActionsEnum action;
    private Object[] params;

    public Request(ActionsEnum action, Object... params) {
        this.action = action;
        this.params = params;
    }
    
    public Request(ActionsEnum action) {
        this.action = action;
        this.params = null;
    }

    public ActionsEnum getAction() {
        return action;
    }

    public Object[] getParams() {
        return params;
    }
}
