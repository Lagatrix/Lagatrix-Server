package lagatrix.server.entities.connection;

/**
 * This class is used to send responses to the client.
 *
 * @author javier
 * @since 1.0
 */
public class Response {
    
    private boolean correctResult;
    private Object response;

    public Response(boolean correctResult, Object response) {
        this.correctResult = correctResult;
        this.response = response;
    }
    
    public boolean isCorrectResult() {
        return correctResult;
    }

    public Object getResponse() {
        return response;
    }
}
