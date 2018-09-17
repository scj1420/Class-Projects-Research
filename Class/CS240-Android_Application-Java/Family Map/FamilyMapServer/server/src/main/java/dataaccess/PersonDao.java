package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Person;
import request.LoadRequest;
import request.PersonRequest;
import result.LoadResult;
import result.PeopleResult;
import result.PersonResult;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * Object that directly accesses the person in the database
 */
public class PersonDao {
    private Database db = new Database();

    /**
     * insert a new person
     *
     * @param a
     * @return
     */
    public boolean insert(Person a) throws DatabaseException {
        db.openConnection();
        try {
            Statement stmt = null;
            try {

                StringBuilder insertPerson = new StringBuilder("INSERT INTO person\n" +
                        "VALUES(\n" +
                        "\t'" + a.getPersonID() + "', \n" +
                        "\t'" + a.getDescendant().getUsername() + "', \n" +
                        "\t\"" + a.getFirstName() + "\", \n" +
                        "\t\"" + a.getLastName() + "\", \n" +
                        "\t'" + a.getGender() + "', \n");
                if (a.getFather() != null){
                    insertPerson.append("\t'" + a.getFather().getPersonID() + "', \n");
                } else {
                    insertPerson.append("\tNULL, \n");
                }
                if (a.getMother() != null){
                    insertPerson.append("\t'" + a.getMother().getPersonID() + "', \n");
                } else {
                    insertPerson.append("\tNULL, \n");
                }
                if (a.getSpouse() != null){
                    insertPerson.append("\t'" + a.getSpouse().getPersonID() + "' \n" +
                    ");");
                } else {
                    insertPerson.append("\tNULL\n" +
                    ");");
                }

                stmt = db.getConn().createStatement();
                stmt.executeUpdate(insertPerson.toString());
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
    public boolean lInsert(LoadRequest.lPerson a) throws DatabaseException {
        db.openConnection();
        try {
            Statement stmt = null;
            try {

                StringBuilder insertPerson = new StringBuilder("INSERT INTO person\n" +
                        "VALUES(\n" +
                        "\t'" + a.getPersonID() + "', \n" +
                        "\t'" + a.getDescendant() + "', \n" +
                        "\t\"" + a.getFirstName() + "\", \n" +
                        "\t\"" + a.getLastName() + "\", \n" +
                        "\t'" + a.getGender() + "', \n");
                if (a.getFather() != null){
                    insertPerson.append("\t'" + a.getFather() + "', \n");
                } else {
                    insertPerson.append("\tNULL, \n");
                }
                if (a.getMother() != null){
                    insertPerson.append("\t'" + a.getMother() + "', \n");
                } else {
                    insertPerson.append("\tNULL, \n");
                }
                if (a.getSpouse() != null){
                    insertPerson.append("\t'" + a.getSpouse() + "' \n" +
                            ");");
                } else {
                    insertPerson.append("\tNULL\n" +
                            ");");
                }

                stmt = db.getConn().createStatement();
                stmt.executeUpdate(insertPerson.toString());
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
     * updates a person
     *
     * @param a
     * @return
     */
    public boolean update(Person a){
        return false;
    }

    /**
     * deletes a person
     *
     * @param username
     * @return
     */
    public boolean delete(String username) throws DatabaseException {
        db.openConnection();
        try {
            Statement stmt = null;
            try {
                String delete = "DELETE FROM person \n" +
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
     * finds a person
     *
     * @param id
     * @return
     */
    public PersonResult find(String id) throws DatabaseException {
        PersonResult result = new PersonResult();
        db.openConnection();
        try {
            Statement stmt = null;
            String descendant;
            String firstName;
            String lastName;
            String gender;
            String father;
            String mother;
            String spouse;

            try {
                String select = "SELECT * FROM person \n" +
                        "WHERE personID = '" + id + "';";

                stmt = db.getConn().createStatement();
                ResultSet rs = stmt.executeQuery(select);

                if (rs.next()){
                    descendant = rs.getString("descendant");
                    firstName = rs.getString("firstname");
                    lastName = rs.getString("lastname");
                    gender = rs.getString("gender");
                    father = rs.getString("father");
                    mother = rs.getString("mother");
                    spouse = rs.getString("spouse");
                } else {
                    result.getErb().setMessage("Person does not exist.");
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

            PersonResult.SuccessResponseBody person = new PersonResult.SuccessResponseBody(descendant,
                    id, firstName, lastName, gender, father, mother, spouse);
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

    public PeopleResult retrieve(String user) throws DatabaseException{
        db.openConnection();
        PeopleResult result = new PeopleResult();
        ArrayList<PeopleResult.Person> list = new ArrayList<PeopleResult.Person>();
        try {
            Statement stmt = null;
            try {
                String select = "SELECT * FROM person\n" +
                        "WHERE descendant = '"+ user +"'\n";

                stmt = db.getConn().createStatement();
                ResultSet rs = stmt.executeQuery(select);
                while (rs.next()){
                    PeopleResult.Person person = new PeopleResult.Person(rs.getString("personID"),
                            rs.getString("descendant"), rs.getString("firstname"),
                            rs.getString("lastname"), rs.getString("gender"), rs.getString("father"),
                            rs.getString("mother"), rs.getString("spouse"));
                    list.add(person);
                }
                PeopleResult.SuccessResponseBody body = new PeopleResult.SuccessResponseBody();
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
