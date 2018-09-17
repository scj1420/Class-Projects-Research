package model;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * Object containing the information about an event
 *
 * Domain:
 * eventID      : non-empty string
 * descendant   : non-empty User
 * person       : non-empty Person
 * latitude     : double
 * longitude    : double
 * country      : non-empty string
 * city         : non-empty string
 * eventType    : non-empty string
 * year         : int
 */
public class Event {
    private String eventID;
    private User descendant;
    private Person person;
    private double latitude;
    private double longitude;
    private String country;
    private String city;
    private String EventType;
    private int year;

    /**
     * Creates an Event object
     *
     * @param eventID -- Unique identifier for this event
     * @param descendant -- User to which this person belongs
     * @param person -- Person to which this event belongs
     * @param latitude -- Latitude of event's location
     * @param longitude -- Longitude of event's location
     * @param country -- Country in which event occurred
     * @param city -- City in which event occurred
     * @param eventType -- Type of event
     * @param year -- Year in which event occurred
     */
    public Event(String eventID,
                 User descendant,
                 Person person,
                 double latitude,
                 double longitude,
                 String country,
                 String city,
                 String eventType,
                 int year) {
        this.eventID = eventID;
        this.descendant = descendant;
        this.person = person;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        EventType = eventType;
        this.year = year;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public User getDescendant() {
        return descendant;
    }

    public void setDescendant(User descendant) {
        this.descendant = descendant;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID='" + eventID + '\'' +
                ", descendant=" + descendant +
                ", person=" + person +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", EventType='" + EventType + '\'' +
                ", year=" + year +
                '}';
    }
}
