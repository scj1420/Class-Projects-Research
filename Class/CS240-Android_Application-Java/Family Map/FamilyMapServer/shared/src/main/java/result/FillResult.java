package result;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * The result for the fill method
 */
public class FillResult {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FillResult{" +
                "message='" + message + '\'' +
                '}';
    }
}
