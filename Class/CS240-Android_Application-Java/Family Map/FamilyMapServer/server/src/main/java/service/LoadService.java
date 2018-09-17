package service;

import dataaccess.*;
import request.LoadRequest;
import result.LoadResult;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * The object that performs the load method
 */
public class LoadService {
    LoadResult lr = new LoadResult();

    /**
     * Clears all data from the database (just like the /clear API), and then loads the
     * posted user, person, and event data into the database.
     *
     * @param r -- LoadRequest object
     * @return LoadResult body
     */
    public LoadResult load(LoadRequest r) throws DatabaseException {
        ClearService cs = new ClearService();
        cs.clear();

        UserDao ud = new UserDao();
        PersonDao pd = new PersonDao();
        EventDao ed = new EventDao();

        boolean success = true;

        for (int i = 0; i < r.getUsers().size(); i++){
            success = ud.insert(r.getUsers().get(i));
            if (!success) break;
        }
        for (int i = 0; i < r.getPersons().size(); i++){
            if (!success) break;
            success = pd.lInsert(r.getPersons().get(i));
        }
        for (int i = 0; i < r.getEvents().size(); i++){
            if (!success) break;
            success = ed.lInsert(r.getEvents().get(i));
        }

        if (success) {
            String successMessage = "Successfully added " + r.getUsers().size() + " users, " +
                    r.getPersons().size() + " person, and " + r.getEvents().size() +
                    " events to the database.";
            lr.setMessage(successMessage);
        } else {
            lr.setMessage("Internal server error.");
        }

        return lr;
    }

}
