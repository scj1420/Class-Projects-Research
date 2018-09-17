package service;

import dataaccess.DatabaseException;
import dataaccess.EventDao;
import dataaccess.UserDao;
import request.EventRequest;
import request.EventsRequest;
import result.EventResult;
import result.EventsResult;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * The object that performs the event method
 */
public class EventService {

    /**
     * The event method
     *
     * @param eventID -- Non-empty string containing the Event ID
     * @return EventResult body
     */
    public EventResult event(String eventID, String user) throws DatabaseException{

        UserDao ud = new UserDao();
        String userName = ud.findUser(user);

        EventDao ed = new EventDao();
        EventResult result = ed.find(eventID);

        if (result.isSuccess()){
            if (!result.getSrb().getDescendant().equals(userName)){
                result.getErb().setMessage("You are not authorized to retrieve this information.");
                result.setSuccess(false);
            }
        }

        return result;
    }

    /**
     * Returns ALL events for ALL family members of the current user. The current
     * user is determined from the provided auth token.
     *
     * @return
     */
    public EventsResult events(String user) throws DatabaseException {
        UserDao ud = new UserDao();
        EventDao ed = new EventDao();
        String userName = ud.findUser(user);

        EventsResult result = ed.retrieve(userName);

        if (result.getSrb().getData().size() == 0){
            result.getErb().setMessage("No information could be retrieved.");
            result.setSuccess(false);
        }


        return result;
    }
}