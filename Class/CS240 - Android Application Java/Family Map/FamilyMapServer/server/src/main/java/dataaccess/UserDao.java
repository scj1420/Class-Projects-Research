package dataaccess;

import java.sql.*;

import model.User;
import result.LoginResult;
import service.Generator;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

// Who is supposed to have the database object???????
// Who are calling all these objects???????

/**
 * Object that directly accesses the user in the database
 */
public class UserDao {
    private Database db = new Database();

    /**
     * insert a new user
     *
     * @param a
     * @return
     */
    public boolean insert(User a) throws DatabaseException{
        db.openConnection();
        try {
            Statement stmt = null;
            try {
                String select = "SELECT * FROM user \n" +
                        "WHERE username = '" + a.getUsername() + "';";
                stmt = db.getConn().createStatement();
                ResultSet rs = stmt.executeQuery(select);

                if (rs.next()){
                    stmt.close();
                    db.closeConnection(true);
                    return false;
                }

                String insertUser = "INSERT INTO user\n" +
                        "VALUES(\n" +
                        "\t'" + a.getUsername() + "', \n" +
                        "\t'" + a.getPassword() + "', \n" +
                        "\t'" + a.getEmail() + "', \n" +
                        "\t'" + a.getFirstName() + "', \n" +
                        "\t'" + a.getLastName() + "', \n" +
                        "\t'" + a.getGender() + "', \n" +
                        "\t'" + a.getPersonID() + "' \n" +
                        ");";

                stmt = db.getConn().createStatement();
                stmt.executeUpdate(insertUser);
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
     * updates a user
     *
     * @param a
     * @return
     */
    public boolean update(User a){
        return false;
    }

    /**
     * deletes a user
     *
     * @param a
     * @return
     */
    public boolean delete(User a){
        return false;
    }

    /**
     * finds a user
     *
     * @param userName
     * @return
     */
    public LoginResult login(String userName, String password) throws DatabaseException{
        LoginResult result = new LoginResult();
        db.openConnection();
        try {
            Statement stmt = null;
            String pw;
            String id;
            try {
                String select = "SELECT * FROM user \n" +
                        "WHERE username = '" + userName + "';";

                stmt = db.getConn().createStatement();
                ResultSet rs = stmt.executeQuery(select);

                if (rs.next()){
                    pw = rs.getString("password");
                    id = rs.getString("personID");
                } else {
                    result.setSuccess(false);
                    result.getErb().setMessage("Username does not exist.");
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

            if (pw.equals(password)){
                Generator gen = new Generator();
                result.getSrb().setAuthToken(gen.generateID());
                result.getSrb().setUserName(userName);
                result.getSrb().setPersonID(id);
                return result;
            } else {
                result.setSuccess(false);
                result.getErb().setMessage("Incorrect password.");
                return result;
            }
        } catch (SQLException e) {
            result.setSuccess(false);
            e.printStackTrace();
            db.closeConnection(true);
            result.getErb().setMessage("Internal server error.");
            return result;
        }
    }

    public User find(String userName) throws DatabaseException{
        db.openConnection();
        try {
            Statement stmt = null;
            String password;
            String email;
            String firstName;
            String lastName;
            String gender;
            String personID;

            try {
                String select = "SELECT * FROM user \n" +
                        "WHERE username = '" + userName + "';";

                stmt = db.getConn().createStatement();
                ResultSet rs = stmt.executeQuery(select);

                if (rs.next()){
                    password = rs.getString("password");
                    email = rs.getString("email");
                    firstName = rs.getString("firstname");
                    lastName = rs.getString("lastname");
                    gender = rs.getString("gender");
                    personID = rs.getString("personid");
                } else {
                    stmt.close();
                    db.closeConnection(true);
                    return null;
                }
            } finally {
                if (stmt != null){
                    stmt.close();
                    stmt = null;
                }
            }
            db.closeConnection(true);

            User user = new User(userName, password, email, firstName, lastName, gender, personID);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            db.closeConnection(true);
            return null;
        }
    }

    public String findUser (String id) throws DatabaseException{
        db.openConnection();
        try {
            Statement stmt = null;
            String userName;

            try {
                String select = "SELECT * FROM user \n" +
                        "WHERE personID = '" + id + "';";

                stmt = db.getConn().createStatement();
                ResultSet rs = stmt.executeQuery(select);

                if (rs.next()){
                    userName = rs.getString("username");

                } else {
                    stmt.close();
                    db.closeConnection(true);
                    return null;
                }
            } finally {
                if (stmt != null){
                    stmt.close();
                    stmt = null;
                }
            }
            db.closeConnection(true);

            return userName;
        } catch (SQLException e) {
            e.printStackTrace();
            db.closeConnection(true);
            return null;
        }
    }
}
