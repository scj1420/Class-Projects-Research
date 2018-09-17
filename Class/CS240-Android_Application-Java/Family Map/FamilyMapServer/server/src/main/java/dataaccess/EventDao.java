package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Event;
import request.LoadRequest;
import result.EventResult;
import result.EventsResult;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * Object that directly accesses the event in the database
 */
public class EventDao {
    private Database db = new Database();

    /**
     * insert a new event
     *
     * @param a
     * @return
     */
    public boolean insert(Event a) throws DatabaseException {
        db.openConnection();
        try {
            Statement stmt = null;
            try {

                String insertEvent = "INSERT INTO event\n" +
                        "VALUES(\n" +
                        "\t'" + a.getEventID() + "', \n" +
                        "\t'" + a.getDescendant().getUsername() + "', \n" +
                        "\t'" + a.getPerson().getPersonID() + "', \n" +
                        "\t" + a.getLatitude() + ", \n" +
                        "\t" + a.getLongitude() + ", \n" +
                        "\t\"" + a.getCountry() + "\", \n" +
                        "\t\"" + a.getCity() + "\", \n" +
                        "\t'" + a.getEventType() + "', \n" +
                        "\t'" + a.getYear() + "' \n" +
                        ");";

                stmt = db.getConn().createStatement();
                stmt.executeUpdate(insertEvent.toString());
            } finally {
                if (stmt != null){
                    stmt.close();
                    stmt = null;
                }
            }
            db.closeConnection(true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            db.closeConnection(true);
            return false;
        }
    }
    public boolean lInsert (LoadRequest.lEvent a) throws DatabaseException {
        db.openConnection();
        try {
            Statement stmt = null;
            try {

                String insertEvent = "INSERT INTO event\n" +
                        "VALUES(\n" +
                        "\t'" + a.getEventID() + "', \n" +
                        "\t'" + a.getDescendant() + "', \n" +
                        "\t'" + a.getPerson() + "', \n" +
                        "\t" + a.getLatitude() + ", \n" +
                        "\t" + a.getLongitude() + ", \n" +
                        "\t\"" + a.getCountry() + "\", \n" +
                        "\t\"" + a.getCity() + "\", \n" +
                        "\t'" + a.getEventType() + "', \n" +
                        "\t'" + a.getYear() + "' \n" +
                        ");";

                stmt = db.getConn().createStatement();
                stmt.executeUpdate(insertEvent.toString());
            } finally {
                if (stmt != null){
                    stmt.close();
                    stmt = null;
                }
            }
            db.closeConnection(true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            db.closeConnection(true);
            return false;
        }
    }

    /**
     * updates an event
     *
     * @param a
     * @return
     */
    public boolean update(Event a){
        return false;
    }

    /**
     * deletes an event
     *
     * @param username
     * @return
     */
    public boolean delete(String username) throws DatabaseException {
        db.openConnection();
        try {
            Statement stmt = null;
            try {
                String delete = "DELETE FROM event \n" +
                        "WHERE descendant = '" + username + "';";

                stmt = db.getConn().createStatement();
                stmt.executeUpdate(delete);

            } finally {
                if (stmt != null){
                    stmt.close();
                    stmt = null;
                }
            }
            db.closeConnection(true);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            db.closeConnection(true);
            return false;
        }
    }
    /**
     * finds an event
     *
     * @return
     */
    public EventResult find(String id) throws  DatabaseException{
        EventResult result = new EventResult();
        db.openConnection();
        try {
            Statement stmt = null;
            String descendant;
            String personID;
            double latitude;
            double longitude;
            String country;
            String city;
            String eventType;
            String year;

            try {
                String select = "SELECT * FROM event \n" +
                        "WHERE eventID = '" + id + "';";

                stmt = db.getConn().createStatement();
                ResultSet rs = stmt.executeQuery(select);

                if (rs.next()){
                    descendant = rs.getString("descendant");
                    personID = rs.getString("person");
                    latitude = rs.getDouble("latitude");
                    longitude = rs.getDouble("longitude");
                    country = rs.getString("country");
                    city = rs.getString("city");
                    eventType = rs.getString("eventtype");
                    year = rs.getString("year");
                } else {
                    result.getErb().setMessage("Event does not exist.");
                    result.setSuccess(false);
                    stmt.close();
                    db.closeConnection(true);
                    return result;
                }
            } finally {
                if (stmt != null){
                    stmt.close();
                    stmt = null;
                }
            }
            db.closeConnection(true);

            EventResult.SuccessResponseBody person = new EventResult.SuccessResponseBody(descendant,
                    id, personID, latitude, longitude, country, city, eventType, year);
            result.setSrb(person);
            return result;
        } catch (SQLException e) {
            result.setSuccess(false);
            result.getErb().setMessage("Internal server error.");
            e.printStackTrace();
            db.closeConnection(true);
            return result;
        }
    }

    public EventsResult retrieve(String user) throws DatabaseException{
        db.openConnection();
        EventsResult result = new EventsResult();
        ArrayList<EventsResult.Event> list = new ArrayList<EventsResult.Event>();
        try {
            Statement stmt = null;
            try {
                String select = "SELECT * FROM event\n" +
                        "WHERE descendant = '"+ user +"'\n";

                stmt = db.getConn().createStatement();
                ResultSet rs = stmt.executeQuery(select);
                while (rs.next()){
                    EventsResult.Event event = new EventsResult.Event(rs.getString("eventID"),
                            rs.getString("descendant"), rs.getString("personID"), rs.getDouble("latitude"),
                            rs.getDouble("longitude"), rs.getString("country"), rs.getString("city"),
                            rs.getString("eventtype"), rs.getString("year"));
                    list.add(event);
                }
                EventsResult.SuccessResponseBody body = new EventsResult.SuccessResponseBody();
                body.setData(list);
                result.setSrb(body);
            } finally {
                if (stmt != null){
                    stmt.close();
                    stmt = null;
                }
            }
            db.closeConnection(true);

            return result;
        } catch (SQLException e){
            result.setSuccess(false);
            result.getErb().setMessage("Internal server error.");
            e.printStackTrace();
            db.closeConnection(true);
            return result;
        }
    }
}
