package result;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * Returns the result of the load method
 */
public class LoadResult {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoadResult{" +
                "message='" + message + '\'' +
                '}';
    }
}
