package lagatrix.server.entities.connection;

import java.io.Serializable;

/**
 * This class is used to send responses to the client.
 *
 * @author javier
 * @since 0.2
 */
public class Response implements Serializable {
    
    private boolean correctResult;
    private Object response;

    public Response(Object response) {
        this.response = response;
    }

    public void setCorrectResult(boolean correctResult) {
        this.correctResult = correctResult;
    }
    
    public boolean isCorrectResult() {
        return correctResult;
    }

    public Object getResponse() {
        return response;
    }
}
