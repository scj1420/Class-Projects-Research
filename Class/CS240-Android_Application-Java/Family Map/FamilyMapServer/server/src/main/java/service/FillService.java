package service;

import java.util.ArrayList;

import dataaccess.*;
import model.*;
import request.FillRequest;
import result.FillResult;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * The object that performs the fill method
 */
public class FillService {
    FillResult result = new FillResult();

    /**
     * Populates the server's database with generated data for the specified user name.
     * The required "username" parameter must be a user already registered with the server. If there is
     * any data in the database already associated with the given user name, it is deleted. The
     * optional “generations” parameter lets the caller specify the number of generations of ancestors
     * to be generated, and must be a non-negative integer (the default is 4, which results in 31 new
     * persons each with associated events).
     *
     * @param userName -- the user's username
     * @param generations -- the number of generations of ancestors to be generated
     * @return FillResult body
     */
    public FillResult fill(String userName,
                    int generations) throws DatabaseException {
        Generator gen = new Generator();
        gen.importData();
        UserDao ud = new UserDao();
        User user = ud.find(userName);
        if (user == null){
            result.setMessage("User does not exist.");
            return result;
        }
        Tree<Person> personTree = gen.generatePeople(user, generations);

        PersonDao pd = new PersonDao();
        EventDao ed = new EventDao();
        pd.delete(user.getUsername());
        ed.delete(user.getUsername());

        pd.insert(personTree.getRoot().getData());
        int countPerson = 1;
        countPerson = insertPersonRecursive(pd, personTree.getRoot(), countPerson);

        ArrayList<Event> allEvents = gen.generateEvents(personTree);
        for (int i = 0; i < allEvents.size(); i++) {
            ed.insert(allEvents.get(i));
        }

        result.setMessage("Successfully added " + countPerson + " persons and " +
                            allEvents.size() + " events to the database.");
        return result;
    }
    public int insertPersonRecursive(PersonDao pd, Node<Person> n, int count) throws DatabaseException {
        if (n.getLeft() != null && n.getRight() != null){
            pd.insert(n.getLeft().getData());
            ++count;
            count = insertPersonRecursive(pd, n.getLeft(), count);
            pd.insert(n.getRight().getData());
            ++count;
            count = insertPersonRecursive(pd, n.getRight(), count);
            return count;
        } else return count;
    }

}
