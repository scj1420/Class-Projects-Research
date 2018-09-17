package com.example.seong_euncho.familymapserver.Model;

import android.net.UrlQuerySanitizer;

import junit.framework.TestCase;

import java.util.List;
import java.util.Random;

import model.User;
import request.LoginRequest;
import result.EventsResult;
import result.LoginResult;
import result.PeopleResult;

/**
 * Created by Seong-EunCho on 4/17/17.
 */
public class UserModelTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

        LoginRequest r = new LoginRequest("sheila", "parker");
        UserModel.getInstance().getsServerProxy().setHost("localhost");
        UserModel.getInstance().getsServerProxy().setPort("8080");
        UserModel.getInstance().setsLoginResult(UserModel.getInstance().getsServerProxy().login(r));
        LoginResult lr = UserModel.getInstance().getsLoginResult();
        UserModel.getInstance().setsPersonResult(UserModel.getInstance().getsServerProxy().
                person(lr.getSrb().getPersonID(), lr.getSrb().getAuthToken()));
        UserModel.getInstance().setsPeopleResult(UserModel.getInstance().getsServerProxy().
                person(UserModel.getInstance().getsLoginResult().getSrb().getAuthToken()));
        UserModel.getInstance().setsEventsResult(UserModel.getInstance().getsServerProxy().
                event(UserModel.getInstance().getsLoginResult().getSrb().getAuthToken()));
    }

    public void testCreateFilters() throws Exception {
        UserModel.getInstance().createFilters();
        for (int i = 0; i < UserModel.getInstance().getsFilters().size(); i++){
            System.out.println(UserModel.getInstance().getsFilters().get(i).getType());
            System.out.println(UserModel.getInstance().getsFilters().get(i).getDescription());
            System.out.println();
        }
    }

    public void testFilterByEvent() throws Exception {
        UserModel.getInstance().createFilters();
        UserModel.getInstance().filterByEvent();

        for (String key : UserModel.getInstance().getsEventFilter().keySet()){
            System.out.println(key);
            for (int i = 0; i < UserModel.getInstance().getsEventFilter().get(key).size(); i++){
                System.out.println(UserModel.getInstance().getsEventFilter().get(key).get(i).toString());
            }
            System.out.println();
        }

        for (String key : UserModel.getInstance().getsEventColor().keySet()){
            System.out.println(key);
            System.out.println(UserModel.getInstance().getsEventColor().get(key));
            System.out.println();
        }

    }

    public void testFilterBySide() throws Exception {
        UserModel.getInstance().createFilters();
        UserModel.getInstance().filterBySide();

        for (String key : UserModel.getInstance().getsSideFilter().keySet()){
            System.out.println(key);
            for (int i = 0; i < UserModel.getInstance().getsSideFilter().get(key).size(); i++){
                EventsResult.Event e = UserModel.getInstance().getsSideFilter().get(key).get(i);
                System.out.println(UserModel.getInstance().retrievePerson(e.getPerson()).toString());
            }
            System.out.println();
        }
    }

    public void testRetrievePerson() throws Exception {
        Random rand = new Random();
        int i = rand.nextInt(UserModel.getInstance().getsEventsResult().getSrb().getData().size());
        EventsResult.Event e = UserModel.getInstance().getsEventsResult().getSrb().getData().get(i);
        PeopleResult.Person p = UserModel.getInstance().retrievePerson(e.getPerson());
        System.out.println(UserModel.getInstance().getsEventsResult().getSrb().getData().get(i).toString());
        System.out.println(p.toString());
    }

    public void testCreateUserEvents() throws Exception {
        UserModel.getInstance().createUserEvents();
        for (int i = 0; i < UserModel.getInstance().getsUserEvents().size(); i++){
            System.out.println(UserModel.getInstance().getsUserEvents().get(i).toString());
        }
    }

    public void testGetMapType() throws Exception {
        assertEquals(0, UserModel.getInstance().getsSettings().getMapType());
    }

    public void testGetBirthEvent() throws Exception {
        UserModel.getInstance().createFilters();
        UserModel.getInstance().filterByEvent();

        Random rand = new Random();
        int i = rand.nextInt(UserModel.getInstance().getsEventsResult().getSrb().getData().size());
        EventsResult.Event e = UserModel.getInstance().getsEventsResult().getSrb().getData().get(i);
        PeopleResult.Person p = UserModel.getInstance().retrievePerson(e.getPerson());
        if (p.getSpouse() != null){
            EventsResult.Event b = UserModel.getInstance().getBirthEvent(p.getSpouse());
            System.out.println(b.toString());
        }
    }

    public void testGetLifeEvents() throws Exception {
        Random rand = new Random();
        int i = rand.nextInt(UserModel.getInstance().getsEventsResult().getSrb().getData().size());
        EventsResult.Event e = UserModel.getInstance().getsEventsResult().getSrb().getData().get(i);
        PeopleResult.Person p = UserModel.getInstance().retrievePerson(e.getPerson());
        List<EventsResult.Event> events = UserModel.getInstance().getLifeEvents(p.getPersonID());
        for (int f = 0; f < events.size(); f++){
            System.out.println(events.get(f).toString());
        }
    }

    public void testRetrieveChild() throws Exception {
        Random rand = new Random();
        int i = rand.nextInt(UserModel.getInstance().getsPeopleResult().getSrb().getData().size());
        PeopleResult.Person p = UserModel.getInstance().getsPeopleResult().getSrb().getData().get(i);
        PeopleResult.Person child = UserModel.getInstance().retrieveChild(p.getPersonID(),
                UserModel.getInstance().getsPersonResult().getSrb().getPersonID());

        System.out.println(p.toString());
        System.out.println(child.toString());
    }
}