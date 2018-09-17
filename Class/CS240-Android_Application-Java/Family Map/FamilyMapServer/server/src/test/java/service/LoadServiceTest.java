package service;

import junit.framework.TestCase;

import java.util.ArrayList;

import coder.Encoder;
import coder.LoadConverter;
import model.Event;
import model.LoadModel;
import model.Person;
import model.User;
import request.LoadRequest;
import result.LoadResult;

/**
 * Created by Seong-EunCho on 3/9/17.
 */
public class LoadServiceTest extends TestCase {
    LoadService ls = new LoadService();
    LoadRequest r;

    public void setUp() throws Exception {
        super.setUp();
        Generator gen = new Generator();
        gen.importData();
        ArrayList<User> userList = gen.randUserList(5);
        ArrayList<Person> personList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++){
            ArrayList<Person> tempList = gen.randPeopleList(userList.get(i));
            for (int j = 0; j < tempList.size(); j++){
                personList.add(tempList.get(j));
            }
        }

        ArrayList<Event> eventList = gen.generateEvents(gen.generatePeople(userList.get(0), 2));
        LoadModel lm = new LoadModel(userList, personList, eventList);
        LoadConverter lc = new LoadConverter();
        r = lc.loadModelToLoadRequest(lm);
    }

    public void testLoad() throws Exception {
        ls.load(r);
    }

}