package model;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * object containing informations for auth token
 *
 * Domain:
 * authToken    : string
 * user         : User
 */
public class AuthToken {
    String authToken;
    String user;

    public AuthToken(String authToken, String user) {
        this.authToken = authToken;
        this.user = user;
    }
    public AuthToken(){}


    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
