package coder;

import java.util.ArrayList;

import model.*;
import request.LoadRequest;

/**
 * Created by Seong-EunCho on 3/9/17.
 */

public class LoadConverter {
    public LoadModel loadRequestToLoadModel(LoadRequest request){

        return null;
    }

    public LoadRequest loadModelToLoadRequest(LoadModel model){
        ArrayList<User> users = model.getUsers();
        ArrayList<LoadRequest.lPerson> persons = new ArrayList<LoadRequest.lPerson>();
        ArrayList<LoadRequest.lEvent> events = new ArrayList<LoadRequest.lEvent>();

        for (int i = 0; i < model.getPersons().size(); i++){
            Person p = model.getPersons().get(i);
            LoadRequest.lPerson lp = new LoadRequest.lPerson(p.getPersonID(),
                    p.getDescendant().getUsername(), p.getFirstName(), p.getLastName(),
                    p.getGender());
            if (p.getFather() != null){
                lp.setFather(p.getFather().getPersonID());
            }
            if (p.getMother() != null){
                lp.setMother(p.getMother().getPersonID());
            }
            if (p.getSpouse() != null){
                lp.setSpouse(p.getSpouse().getPersonID());
            }

            persons.add(lp);
        }
        for (int i = 0; i < model.getEvents().size(); i++){
            Event e = model.getEvents().get(i);
            LoadRequest.lEvent le = new LoadRequest.lEvent(e.getEventID(),
                    e.getDescendant().getUsername(), e.getPerson().getPersonID(), e.getLatitude(),
                    e.getLongitude(), e.getCountry(), e.getCity(), e.getEventType(), e.getYear());
            events.add(le);
        }
        LoadRequest lr = new LoadRequest(users, persons, events);

        return lr;
    }

}
