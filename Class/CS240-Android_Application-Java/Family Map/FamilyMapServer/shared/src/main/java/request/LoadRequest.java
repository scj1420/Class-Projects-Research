package request;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

import java.util.ArrayList;

import model.Event;
import model.Person;
import model.User;
import result.LoadResult;

/**
 * The “users” property in the request body contains an array of users to be
 * created. The “persons” and “events” properties contain family history information for these
 * users. The objects contained in the “persons” and “events” arrays should be added to the
 * server’s database.
 *
 * Domain:
 * users        : Array of User objects
 * persons      : Array of Person objects
 * events       : Array of Event objects
 */
public class LoadRequest {
    private ArrayList<User> users;
    private ArrayList<lPerson> persons;
    private ArrayList<lEvent> events;

    public LoadRequest(ArrayList<User> users, ArrayList<lPerson> persons, ArrayList<lEvent> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<lPerson> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<lPerson> persons) {
        this.persons = persons;
    }

    public ArrayList<lEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<lEvent> events) {
        this.events = events;
    }

    public static class lPerson{
        private String personID;
        private String descendant;
        private String firstName;
        private String lastName;
        private String gender;
        private String father;
        private String mother;
        private String spouse;

        public String getPersonID() {
            return personID;
        }

        public void setPersonID(String personID) {
            this.personID = personID;
        }

        public String getDescendant() {
            return descendant;
        }

        public void setDescendant(String descendant) {
            this.descendant = descendant;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getFather() {
            return father;
        }

        public void setFather(String father) {
            this.father = father;
        }

        public String getMother() {
            return mother;
        }

        public void setMother(String mother) {
            this.mother = mother;
        }

        public String getSpouse() {
            return spouse;
        }

        public void setSpouse(String spouse) {
            this.spouse = spouse;
        }

        public lPerson(String personID, String descendant, String firstName, String lastName,
                       String gender) {
            this.personID = personID;
            this.descendant = descendant;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "lPerson{" +
                    "personID='" + personID + '\'' +
                    ", descendant='" + descendant + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", gender='" + gender + '\'' +
                    ", father='" + father + '\'' +
                    ", mother='" + mother + '\'' +
                    ", spouse='" + spouse + '\'' +
                    '}';
        }
    }
    public static class lEvent{
        private String eventID;
        private String descendant;
        private String personID;
        private double latitude;
        private double longitude;
        private String country;
        private String city;
        private String eventType;
        private int year;

        public lEvent(String eventID, String descendant, String personID, double latitude,
                      double longitude, String country, String city, String eventType, int year) {
            this.eventID = eventID;
            this.descendant = descendant;
            this.personID = personID;
            this.latitude = latitude;
            this.longitude = longitude;
            this.country = country;
            this.city = city;
            this.eventType = eventType;
            this.year = year;
        }

        public String getEventID() {
            return eventID;
        }

        public void setEventID(String eventID) {
            this.eventID = eventID;
        }

        public String getDescendant() {
            return descendant;
        }

        public void setDescendant(String descendant) {
            this.descendant = descendant;
        }

        public String getPerson() {
            return personID;
        }

        public void setPerson(String person) {
            this.personID = person;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public String toString() {
            return "lEvent{" +
                    "eventID='" + eventID + '\'' +
                    ", descendant='" + descendant + '\'' +
                    ", personID='" + personID + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", country='" + country + '\'' +
                    ", city='" + city + '\'' +
                    ", eventType='" + eventType + '\'' +
                    ", year=" + year +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoadRequest{" +
                "users=" + users +
                ", persons=" + persons +
                ", events=" + events +
                '}';
    }
}
