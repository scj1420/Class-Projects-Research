package result;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * Contains informations for a single event
 *
 * Domain:
 * descendant   : Name of user account this event belongs to (non-empty string)
 * eventID      : Event's unique ID
 * person ID    : ID of the person this event belongs to
 * latitude     : Latitude of the event's locations (number)
 * longitude    : Longitude of the event's location (number)
 * country      : Name of country where event occurred (non-empty string)
 */
public class EventResult {
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
        private String eventID;
        private String descendant;
        private String personID;
        private double latitude;
        private double longitude;
        private String country;
        private String city;
        private String eventType;
        private String year;

        public SuccessResponseBody(){};
        public SuccessResponseBody(String descendant, String eventID, String personID,
                                   double latitude, double longitude, String country, String city,
                                   String eventType, String year) {
            this.descendant = descendant;
            this.eventID = eventID;
            this.personID = personID;
            this.latitude = latitude;
            this.longitude = longitude;
            this.country = country;
            this.city = city;
            this.eventType = eventType;
            this.year = year;
        }

        public String getDescendant() {
            return descendant;
        }

        public void setDescendant(String descendant) {
            this.descendant = descendant;
        }

        public String getEventID() {
            return eventID;
        }

        public void setEventID(String eventID) {
            this.eventID = eventID;
        }

        public String getPersonID() {
            return personID;
        }

        public void setPersonID(String personID) {
            this.personID = personID;
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

        @Override
        public String toString() {
            return "SuccessResponseBody{" +
                    "descendant='" + descendant + '\'' +
                    ", eventID='" + eventID + '\'' +
                    ", personID='" + personID + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", country='" + country + '\'' +
                    ", city='" + city + '\'' +
                    ", eventType='" + eventType + '\'' +
                    ", year='" + year + '\'' +
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
}
