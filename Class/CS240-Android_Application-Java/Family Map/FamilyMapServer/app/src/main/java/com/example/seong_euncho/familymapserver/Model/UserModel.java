package com.example.seong_euncho.familymapserver.Model;

import com.example.seong_euncho.familymapserver.Server.ServerProxy;
import com.example.seong_euncho.familymapserver.UI.SearchActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import model.Item;
import model.User;
import result.*;

/**
 * Created by Seong-EunCho on 4/14/17.
 */

public class UserModel {
    private static UserModel instance;

    private ServerProxy sServerProxy;
    private LoginResult sLoginResult;
    private PersonResult sPersonResult;
    private PeopleResult sPeopleResult;
    private EventsResult sEventsResult;
    private EventsResult.Event sCurrentEvent;

    private Settings sSettings;
    private List<Filter> sFilters;
    private List<EventsResult.Event> sUserEvents;

    private Map<String, List<EventsResult.Event>> sEventFilter;
    private Map<String, Integer> sEventColor;
    private Map<String, List<EventsResult.Event>> sSideFilter;
    private Map<String, List<PeopleResult.Person>> sPeopleSideFilter;

    private boolean isLoggedIn;

    private UserModel(){
        isLoggedIn = false;
        sServerProxy = new ServerProxy();
        sSettings = new Settings();
    }

    public static UserModel getInstance(){
        if (instance == null){
            instance = new UserModel();
        }
        return instance;
    }

    public static void setInstance(UserModel instance) {
        UserModel.instance = instance;
    }

    public ServerProxy getsServerProxy() {
        return sServerProxy;
    }

    public void setsServerProxy(ServerProxy sServerProxy) {
        this.sServerProxy = sServerProxy;
    }

    public LoginResult getsLoginResult() {
        return sLoginResult;
    }

    public void setsLoginResult(LoginResult sLoginResult) {
        this.sLoginResult = sLoginResult;
    }

    public void setsLoginResult(RegisterResult result) {
        LoginResult loginResult = new LoginResult();
        loginResult.getSrb().setAuthToken(result.getSrb().getAuthToken());
        loginResult.getSrb().setPersonID(result.getSrb().getPersonID());
        loginResult.getSrb().setUserName(result.getSrb().getUserName());

        sLoginResult = loginResult;
    }

    public PersonResult getsPersonResult() {
        return sPersonResult;
    }

    public void setsPersonResult(PersonResult sPersonResult) {
        this.sPersonResult = sPersonResult;
    }

    public PeopleResult getsPeopleResult() {
        return sPeopleResult;
    }

    public void setsPeopleResult(PeopleResult sPeopleResult) {
        this.sPeopleResult = sPeopleResult;
    }

    public EventsResult getsEventsResult() {
        return sEventsResult;
    }

    public void setsEventsResult(EventsResult sEventsResult) {
        this.sEventsResult = sEventsResult;
    }

    public EventsResult.Event getsCurrentEvent() {
        return sCurrentEvent;
    }

    public void setsCurrentEvent(EventsResult.Event sCurrentEvent) {
        this.sCurrentEvent = sCurrentEvent;
    }

    public Settings getsSettings() {
        return sSettings;
    }

    public void setsSettings(Settings sSettings) {
        this.sSettings = sSettings;
    }

    public List<Filter> getsFilters() {
        return sFilters;
    }

    public void setsFilters(List<Filter> sFilters) {
        this.sFilters = sFilters;
    }

    public List<EventsResult.Event> getsUserEvents() {
        return sUserEvents;
    }

    public void setsUserEvents(List<EventsResult.Event> sUserEvents) {
        this.sUserEvents = sUserEvents;
    }

    public Map<String, List<EventsResult.Event>> getsEventFilter() {
        return sEventFilter;
    }

    public void setsEventFilter(Map<String, List<EventsResult.Event>> sEventFilter) {
        this.sEventFilter = sEventFilter;
    }

    public Map<String, Integer> getsEventColor() {
        return sEventColor;
    }

    public void setsEventColor(Map<String, Integer> sEventColor) {
        this.sEventColor = sEventColor;
    }

    public Map<String, List<EventsResult.Event>> getsSideFilter() {
        return sSideFilter;
    }

    public void setsSideFilter(Map<String, List<EventsResult.Event>> sSideFilter) {
        this.sSideFilter = sSideFilter;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Map<String, List<PeopleResult.Person>> getsPeopleSideFilter() {
        return sPeopleSideFilter;
    }

    public void setsPeopleSideFilter(Map<String, List<PeopleResult.Person>> sPeopleSideFilter) {
        this.sPeopleSideFilter = sPeopleSideFilter;
    }

    public void createFilters() {
        sFilters = new ArrayList<>();

        for (int i = 0; i < sEventsResult.getSrb().getData().size(); i++){
            String type = sEventsResult.getSrb().getData().get(i).getEventType();
            type = type.toLowerCase();
            type = type.substring(0, 1).toUpperCase() + type.substring(1, type.length());
            type = type.concat(" Events");
            if (!findEventType(type)){
                String description = "FILTER BY " + type.toUpperCase();
                Filter filter = new Filter(type, description);
                sFilters.add(filter);
            }

        }

        Filter fatherSide = new Filter("Father's Side", "FILTER BY FATHER'S SIDE OF THE FAMILY");
        Filter motherSide = new Filter("Mother's Side", "FILTER BY MOTHER'S SIDE OF THE FAMILY");
        Filter maleEvents = new Filter("Male Events", "FILTER EVENTS BASED ON GENDER");
        Filter femaleEvents = new Filter("Female Events", "FILTER EVENTS BASED ON GENDER");
        sFilters.add(fatherSide);
        sFilters.add(motherSide);
        sFilters.add(maleEvents);
        sFilters.add(femaleEvents);

    }

    private boolean findEventType(String type){
        if (sFilters.size() == 0){
            return false;
        } else {
            for (int i = 0; i < sFilters.size(); i++){

                if (sFilters.get(i).getType().equals(type)){
                    return true;
                }
            }
            return false;
        }
    }

    public void filterByEvent(){
        sEventFilter = new HashMap<>();
        sEventColor = new HashMap<>();
        Random rand = new Random();
        boolean first = true;
        int range = 350/(sFilters.size() - 4);

        if (sFilters.size() > 4) {
            for (int i = 0; i < sFilters.size() - 4; i++) {
                List<EventsResult.Event> tempList = new ArrayList<>();
                sEventFilter.put(sFilters.get(i).getType(), tempList);
                int color;
                if (first) {
                    color = rand.nextInt(350);
                    first = false;
                } else {
                    color = sEventColor.get(sFilters.get(i - 1).getType()) + range;
                    if (color > 350){
                        color = color - 350;
                    }
                }
                sEventColor.put(sFilters.get(i).getType(), color);
            }
        } else return;

        for (int i = 0; i < sEventsResult.getSrb().getData().size(); i++){
            String type = sEventsResult.getSrb().getData().get(i).getEventType();
            type = type.toLowerCase();
            type = type.substring(0, 1).toUpperCase() + type.substring(1, type.length());
            type = type.concat(" Events");
            sEventFilter.get(type).
                    add(sEventsResult.getSrb().getData().get(i));
        }
    }

    public void filterBySide(){
        sSideFilter = new HashMap<>();
        sPeopleSideFilter = new HashMap<>();

        List<EventsResult.Event> list1 = new ArrayList<>();
        List<EventsResult.Event> list2 = new ArrayList<>();
        List<PeopleResult.Person> list3 = new ArrayList<>();
        List<PeopleResult.Person> list4 = new ArrayList<>();

        sSideFilter.put("Mother's Side", list1);
        sSideFilter.put("Father's Side", list2);
        sPeopleSideFilter.put("Mother's Side", list3);
        sPeopleSideFilter.put("Father's Side", list4);

        String mother = sPersonResult.getSrb().getMother();
        String user = sPersonResult.getSrb().getPersonID();

        for (int i = 0; i < sEventsResult.getSrb().getData().size(); i++){
            EventsResult.Event e = sEventsResult.getSrb().getData().get(i);
            if (e.getPerson() != null) {
                if (retrievePerson(e.getPerson()).getPersonID().equals(user)) {
                    continue;
                }
                if (determineSide(e.getPerson(), retrievePerson(mother))) {
                    sSideFilter.get("Mother's Side").add(e);
                } else sSideFilter.get("Father's Side").add(e);
            }
        }
        for (PeopleResult.Person p : sPeopleResult.getSrb().getData()){
            if (p.getPersonID().equals(user)){
                continue;
            }
            if (determineSide(p.getPersonID(), retrievePerson(mother))){
                sPeopleSideFilter.get("Mother's Side").add(p);
            } else sPeopleSideFilter.get("Father's Side").add(p);
        }
    }

    private boolean determineSide(String id, PeopleResult.Person p){
        if (id.equals(p.getPersonID())){
            return true;
        } else {
            if (p.getMother() != null && p.getFather() != null){
                if (determineSide(id, retrievePerson(p.getMother())) ||
                        determineSide(id, retrievePerson(p.getFather()))) {
                    return true;
                } else return false;
            } else {
                return false;
            }
        }
    }

    public void filterByGender(){

    }

    public Filter findFilterByType(String type){
        for (int i = 0; i < sFilters.size(); i++){
            if (sFilters.get(i).getType().equals(type)){
                return sFilters.get(i);
            }
        }
        return null;
    }

    public Filter findFilterByGender(String gender){
        String filterType;
        if (gender.equals("m")){
            filterType = "Male Events";
        } else {
            filterType = "Female Events";
        }
        for (int i = 0; i < sFilters.size(); i++){
            if (sFilters.get(i).getType().equals(filterType)){
                return sFilters.get(i);
            }
        }
        return null;
    }

    public void filterSwitch(Filter filter){
        for (int i = 0; i < sFilters.size(); i++){
            if (sFilters.get(i).getType().equals(filter.getType())){
                sFilters.get(i).setOn(filter.isOn());
            }
        }
    }

    public PeopleResult.Person retrievePerson(String id){
        for (int i = 0; i < sPeopleResult.getSrb().getData().size(); i++){
            PeopleResult.Person p = sPeopleResult.getSrb().getData().get(i);
            if (p.getPersonID().equals(id)){
                return p;
            }
        }

        return null;
    }

    public void createUserEvents(){
        sUserEvents = new ArrayList<>();
        for (int i = 0; i < sEventsResult.getSrb().getData().size(); i++){
            EventsResult.Event e = sEventsResult.getSrb().getData().get(i);
            if (e.getPerson().equals(sPersonResult.getSrb().getPersonID())){
                sUserEvents.add(e);
            }
        }
    }

    public void logout(){
        instance = null;
    }

    public EventsResult.Event getBirthEvent(String id){
        List<EventsResult.Event> list = new ArrayList<>();
        for (String type : sEventFilter.keySet()){
            list = sEventFilter.get(type);
            EventsResult.Event e = null;
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).getPerson().equals(id)){
                    e = list.get(i);
                    return e;
                }
            }
            if (e == null) {
                continue;
            } else {
                break;
            }
        }
//        System.out.println(list.size());
//        System.out.println(id);
//        System.out.println(list.get(0).toString());
//        for (int i = 0; i < list.size(); i++){
//            if (list.get(i).getPerson().equals(id)){
//                return list.get(i);
//            }
//        }
        return null;
    }

    public List<EventsResult.Event> getLifeEvents(String id){
        List<EventsResult.Event> events = new ArrayList<>();
        for (int i = 0; i < sEventsResult.getSrb().getData().size(); i++){
            EventsResult.Event e = sEventsResult.getSrb().getData().get(i);
            if (e.getPerson().equals(id)){
                events.add(e);
            }
        }
        return events;
    }

    public List<Object> getOLifeEvents(String id){
        List<EventsResult.Event> events = new ArrayList<>();

        for (int i = 0; i < getsUserEvents().size(); i++){
            EventsResult.Event e = getsUserEvents().get(i);
            PeopleResult.Person p = retrievePerson(e.getPerson());

            String type = e.getEventType();
            type = type.toLowerCase();
            type = type.substring(0, 1).toUpperCase() + type.substring(1, type.length());
            type = type.concat(" Events");

            if (findFilterByType(type).isOn() &&
                    findFilterByGender(p.getGender()).isOn()) {
                if (e.getPerson().equals(id)){
                    events.add(e);
                }
            }
        }

        for (String key : getsSideFilter().keySet()){
            if (findFilterByType(key).isOn()){
                for (int i = 0; i < getsSideFilter().get(key).size(); i++){
                    EventsResult.Event e = getsSideFilter().get(key).get(i);
                    PeopleResult.Person p = retrievePerson(e.getPerson());

                    String type = e.getEventType();
                    type = type.toLowerCase();
                    type = type.substring(0, 1).toUpperCase() + type.substring(1, type.length());
                    type = type.concat(" Events");

                    if (findFilterByType(type).isOn() &&
                            findFilterByGender(p.getGender()).isOn()) {
                        if (e.getPerson().equals(id)){
                            events.add(e);
                        }
                    }
                }
            }
        }

//        for (int i = 0; i < sEventsResult.getSrb().getData().size(); i++){
//            EventsResult.Event e = sEventsResult.getSrb().getData().get(i);
//            if (e.getPerson().equals(id)){
//                events.add(e);
//            }
//        }

        List<Object> list = new ArrayList<>();
        list.addAll(events);
        return list;
    }

    public List<Object> getOFamily(String id){
        List<PeopleResult.Person> persons = new ArrayList<>();
        PeopleResult.Person p = retrievePerson(id);
        if (p.getFather() != null){
            persons.add(retrievePerson(p.getFather()));
        }
        if (p.getMother() != null){
            persons.add(retrievePerson(p.getMother()));
        }
        if (p.getSpouse() != null){
            persons.add(retrievePerson(p.getSpouse()));
        }
        PeopleResult.Person child = retrieveChild(p.getPersonID(), sPersonResult.getSrb().getPersonID());
        if (child != null){
            persons.add(child);
        }

        List<Object> list = new ArrayList<>();
        list.addAll(persons);
        return list;
    }

    public PeopleResult.Person retrieveChild(String parent, String des){
        PeopleResult.Person p = retrievePerson(des);
        if (p.getFather() == null && p.getMother() == null){
            return null;
        } else {
            if (p.getFather().equals(parent) || p.getMother().equals(parent)){
                return p;
            } else {
                PeopleResult.Person p1 = retrieveChild(parent, p.getFather());
                PeopleResult.Person p2 = retrieveChild(parent, p.getMother());
                if (p1 != null){
                    return p1;
                } else if (p2 != null){
                    return p2;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Item> searchAll(String term){
        List<Item> list = new ArrayList<>();

        PeopleResult.Person user = retrievePerson(getsPersonResult().getSrb().getPersonID());
        if (user.contains(term)){
            list.add(user);
        }
        for (String key: sPeopleSideFilter.keySet()){
            if (findFilterByType(key).isOn()){
                for (PeopleResult.Person p : sPeopleSideFilter.get(key)){
                    if (p.contains(term)){
                        list.add(p);
                    }
                }
            }
        }
//        for (PeopleResult.Person p : sPeopleResult.getSrb().getData()){
//            if (p.contains(term)){
//                list.add(p);
//            }
//        }

        for (int i = 0; i < getsUserEvents().size(); i++){
            EventsResult.Event e = getsUserEvents().get(i);
            PeopleResult.Person p = retrievePerson(e.getPerson());

            String type = e.getEventType();
            type = type.toLowerCase();
            type = type.substring(0, 1).toUpperCase() + type.substring(1, type.length());
            type = type.concat(" Events");

            if (findFilterByType(type).isOn() &&
                    findFilterByGender(p.getGender()).isOn()) {
                if (e.contains(term)){
                    list.add(e);
                }
            }
        }

        for (String key : getsSideFilter().keySet()){
            if (findFilterByType(key).isOn()){
                for (int i = 0; i < getsSideFilter().get(key).size(); i++){
                    EventsResult.Event e = getsSideFilter().get(key).get(i);
                    PeopleResult.Person p = retrievePerson(e.getPerson());

                    String type = e.getEventType();
                    type = type.toLowerCase();
                    type = type.substring(0, 1).toUpperCase() + type.substring(1, type.length());
                    type = type.concat(" Events");

                    if (findFilterByType(type).isOn() &&
                            findFilterByGender(p.getGender()).isOn()) {
                        if (e.contains(term)){
                            list.add(e);
                        }
                    }
                }
            }
        }

        return list;
    }

    public void addNameToEvents(){
        for (EventsResult.Event e : sEventsResult.getSrb().getData()){
            PeopleResult.Person p = retrievePerson(e.getPerson());
            e.setPersonName(p.getFirstName() + " " + p.getLastName());
        }
    }

}

