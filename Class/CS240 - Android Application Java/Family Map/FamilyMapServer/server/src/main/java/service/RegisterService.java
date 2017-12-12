package service;

import java.util.ArrayList;
import java.util.UUID;

import dataaccess.*;
import model.*;
import request.RegisterRequest;
import result.RegisterResult;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * This object performs the register method. It constructs a new User then, using the UserDao,
 * it will add a new user to the database. It will then return a message, whether the user has
 * been successfully registered or if there has been an error during the process.
 */
public class RegisterService {
    User user;
    RegisterResult result = new RegisterResult();

    /**
     * Creates a new user account, generates 4 generations of ancestor data
     * for the new user, logs the user in, and returns an auth token.
     *
     * @param r -- RegisterRequest object
     * @return RegisterResult body
     */
    public RegisterResult register(RegisterRequest r) throws DatabaseException {
        Generator gen = new Generator();
        gen.importData();
        user = new User(r.getUserName(), r.getPassword(), r.getEmail(), r.getFirstName(),
                r.getLastName(), r.getGender(), gen.generateID());
        UserDao ud = new UserDao();

        if (ud.insert(user)) {
            Tree<Person> personTree = gen.generatePeople(user, 4);
            PersonDao pd = new PersonDao();
            pd.insert(personTree.getRoot().getData());
            insertPersonRecursive(pd, personTree.getRoot());

            ArrayList<Event> allEvents = gen.generateEvents(personTree);
            EventDao ed = new EventDao();
            for (int i = 0; i < allEvents.size(); i++) {
                ed.insert(allEvents.get(i));
            }

            AuthToken token = new AuthToken(gen.generateID(), user.getPersonID());
            AuthTokenDao ad = new AuthTokenDao();
            ad.insert(token);
            result.getSrb().setAuthToken(token.getAuthToken());
            result.getSrb().setUserName(user.getUsername());
            result.getSrb().setPersonID(user.getPersonID());
        } else {
            result.getErb().setMessage("Username already in use.");
            result.setSuccess(false);
        }

        return result;
    }
    public void insertPersonRecursive(PersonDao pd, Node<Person> n) throws DatabaseException {
        if (n.getLeft() != null && n.getRight() != null){
            pd.insert(n.getLeft().getData());
            insertPersonRecursive(pd, n.getLeft());
            pd.insert(n.getRight().getData());
            insertPersonRecursive(pd, n.getRight());
            return;
        } else return;
    }
}
