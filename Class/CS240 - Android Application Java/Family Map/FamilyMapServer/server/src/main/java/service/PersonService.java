package service;

import dataaccess.DatabaseException;
import dataaccess.PersonDao;
import dataaccess.UserDao;
import request.PersonRequest;
import result.PeopleResult;
import result.PersonResult;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * The object that performs the person method
 */
public class PersonService {

    /**
     * Returns the single person object with the specified ID.
     *
     * @param personID -- Non-empty string containing the Person ID
     * @return PersonResult body
     */
    public PersonResult person(String personID, String user) throws DatabaseException {

        UserDao ud = new UserDao();
        String userName = ud.findUser(user);

        PersonDao pd = new PersonDao();
        PersonResult result = pd.find(personID);

        if (result.isSuccess()){
            if (!result.getSrb().getDescendant().equals(userName)){
                System.out.println("Descendant: " + result.getSrb().getDescendant());
                System.out.println("User: " + userName);
                result.getErb().setMessage("You are not authorized to retrieve this information.");
                result.setSuccess(false);
            }
        }

        return result;
    }

    /**
     * Returns ALL family members of the current user. The current user is
     * determined from the provided auth token.
     *
     * @return PeopleResult body
     */
    public PeopleResult persons(String user) throws DatabaseException {
        UserDao ud = new UserDao();
        PersonDao pd = new PersonDao();
        String userName = ud.findUser(user);

        PeopleResult result = pd.retrieve(userName);

        if (result.getSrb().getData().size() == 0){
            result.getErb().setMessage("No information could be retrieved.");
            result.setSuccess(false);
        }

        return result;
    }
}
