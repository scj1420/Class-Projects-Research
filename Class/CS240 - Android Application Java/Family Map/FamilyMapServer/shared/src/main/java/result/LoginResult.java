package result;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * An object that contains the result of the login method
 *
 * Domain:
 * authToken    : Non-empty auth token string
 * userName     : User name passed in with request
 * personID     : Non-empty string containing the Person ID of the
 *                  user's generated Person object
 */
public class LoginResult {
    private SuccessResponseBody srb = new SuccessResponseBody();
    private ErrorResponseBody erb = new ErrorResponseBody();
    boolean success = true;

    public String toString(){
        if (success){
            return srb.toString();
        } else return erb.toString();
    }

    public SuccessResponseBody getSrb() {
        return srb;
    }

    public void setSrb(SuccessResponseBody srb) {
        this.srb = srb;
    }

    public ErrorResponseBody getErb() {
        return erb;
    }

    public void setErb(ErrorResponseBody erb) {
        this.erb = erb;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class SuccessResponseBody {
        private String authToken;
        private String userName;
        private String personID;

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPersonID() {
            return personID;
        }

        public void setPersonID(String personID) {
            this.personID = personID;
        }

        @Override
        public String toString() {
            return "SuccessResponseBody{" +
                    "authToken='" + authToken + '\'' +
                    ", userName='" + userName + '\'' +
                    ", personID='" + personID + '\'' +
                    '}';
        }
    }
    public static class ErrorResponseBody {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ErrorResponseBody{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }
}
