package request;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

import result.LoginResult;

/**
 * Contains information needed to log in the user
 *
 * Domain:
 * userName     : Non-empty string
 * password     : Non-empty string
 */
public class LoginRequest {
    private String userName;
    private String password;

    /**
     * Creates a LoginRequest object
     *
     * @param userName -- the username of the user
     * @param password -- the password of the user
     */
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginRequest(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
