package result;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

import java.util.ArrayList;

import model.Event;
import model.Item;

/**
 * Returns the result of the person method
 *
 * Domain:
 * data     : Array of person objects
 */
public class EventsResult {
    private SuccessResponseBody srb = new SuccessResponseBody();
    private ErrorResponseBody erb = new ErrorResponseBody();
    boolean success = true;

    public String toString(){
        if (success){
            return srb.toString();
        } else return erb.toString();
    }

    public SuccessResponseBody getSrb() {
        return srb;
    }

    public void setSrb(SuccessResponseBody srb) {
        this.srb = srb;
    }

    public ErrorResponseBody getErb() {
        return erb;
    }

    public void setErb(ErrorResponseBody erb) {
        this.erb = erb;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class SuccessResponseBody {
        private ArrayList<Event> data;

        public ArrayList<Event> getData() {
            return data;
        }

        public void setData(ArrayList<Event> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "SuccessResponseBody{" +
                    "data=" + data +
                    '}';
        }
    }
    public static class ErrorResponseBody {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ErrorResponseBody{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }

    public static class Event implements Item{
        private String eventID;
        private String descendant;
        private String person;
        private double latitude;
        private double longitude;
        private String country;
        private String city;
        private String eventType;
        private String year;
        private String personName;

        public Event(String eventID, String descendant, String person, double latitude,
                     double longitude, String country, String city, String eventType, String year) {
            this.eventID = eventID;
            this.descendant = descendant;
            this.person = person;
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
            return person;
        }

        public void setPerson(String person) {
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
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        @Override
        public boolean contains(String search) {
            if (country.toLowerCase().contains(search.toLowerCase())) {
                return true;
            }
            if (city.toLowerCase().contains(search.toLowerCase())) {
                return true;
            }
            if (year.toString().toLowerCase().contains(search.toLowerCase())) {
                return true;
            }
            if (personName.toLowerCase().contains(search.toLowerCase())){
                return true;
            }
            if (eventType.toLowerCase().contains(search.toLowerCase())){
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "eventID='" + eventID + '\'' +
                    ", descendant='" + descendant + '\'' +
                    ", person='" + person + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", country='" + country + '\'' +
                    ", city='" + city + '\'' +
                    ", eventType='" + eventType + '\'' +
                    ", year='" + year + '\'' +
                    '}';
        }
    }
}