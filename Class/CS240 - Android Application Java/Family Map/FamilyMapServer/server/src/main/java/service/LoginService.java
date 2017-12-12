package service;

import dataaccess.AuthTokenDao;
import dataaccess.DatabaseException;
import dataaccess.UserDao;
import model.AuthToken;
import request.LoginRequest;
import result.LoginResult;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

public class LoginService {

    /**
     * Logs in the user and returns an auth token.
     *
     * @param r -- LongRequest object
     * @return LoginResult body
     */
    public LoginResult login(LoginRequest r) throws DatabaseException{
        UserDao ud = new UserDao();
        LoginResult lr = ud.login(r.getUserName(), r.getPassword());
        if (lr.isSuccess()){
            AuthToken token = new AuthToken(lr.getSrb().getAuthToken(), lr.getSrb().getPersonID());
            AuthTokenDao ad = new AuthTokenDao();
            ad.insert(token);
        }

        return lr;
    }
}
