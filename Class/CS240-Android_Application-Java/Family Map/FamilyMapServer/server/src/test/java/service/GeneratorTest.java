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

/**
 * Created by Seong-EunCho on 3/7/17.
 */
public class GeneratorTest extends TestCase {
    private Generator gen;

    public void setUp() throws Exception {
        super.setUp();
        gen = new Generator();
        gen.importData();
    }

    public void testGeneratePersonID() throws Exception {
        gen.generateID();
    }

    public void testImportData() throws Exception{
        gen.importData();
    }

    public void testRandPerson() throws Exception{
        User u = new User("scj14", "ko-ng3513", "scj1420@gmail.com", "Seong", "Cho", "m", gen.generateID());
        Person p1 = gen.randPerson(u, "m");
        Person p2 = gen.randPerson(u, "f");
        Person p3 = gen.randPerson(u, "m", u.getLastName());
//        System.out.println(p1.toString());
//        System.out.println(p2.toString());
//        System.out.println(p3.toString());
    }

    public void testGeneratePeople() throws Exception{
        User u = new User("scj14", "ko-ng3513", "scj1420@gmail.com", "Seong", "Cho", "m", gen.generateID());
        gen.generatePeople(u, 4);
    }

    public void testGenerateEvents() throws Exception{
        User u = new User("scj14", "ko-ng3513", "scj1420@gmail.com", "Seong", "Cho", "m", gen.generateID());
        gen.generateEvents(gen.generatePeople(u, 4));

    }

    public void testGenerateRandUser() throws Exception{
        Encoder e = new Encoder();

        ArrayList<User> userList = gen.randUserList(5);
        for (int i = 0; i < userList.size(); i++){
//            System.out.println(userList.get(i).toString());
        }
        System.out.println();
        ArrayList<Person> personList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++){
            ArrayList<Person> tempList = gen.randPeopleList(userList.get(i));
            for (int j = 0; j < tempList.size(); j++){
                personList.add(tempList.get(j));
            }
        }
        for (int i = 0; i < personList.size(); i++){
//            System.out.println(personList.get(i).toString());
        }
//        System.out.println();
        ArrayList<Event> eventList = gen.generateEvents(gen.generatePeople(userList.get(0), 2));
        for (int i = 0; i < eventList.size(); i++){
//            System.out.println(eventList.get(i).toString());
        }

        LoadModel lm = new LoadModel(userList, personList, eventList);
        LoadConverter lc = new LoadConverter();
        LoadRequest lr = lc.loadModelToLoadRequest(lm);

        String jsonUser = e.Encode(lr.getUsers());
        String jsonPerson = e.Encode(lr.getPersons());
        String jsonEvent = e.Encode(lr.getEvents());

        String json = e.Encode(lr);
    }
}