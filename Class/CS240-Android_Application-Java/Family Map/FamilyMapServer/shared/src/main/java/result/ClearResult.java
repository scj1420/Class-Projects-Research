package result;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * Returns the result of the clear method
 */
public class ClearResult {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClearResult{" +
                "message='" + message + '\'' +
                '}';
    }
}
