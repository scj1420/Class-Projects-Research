package dataaccess;

import model.AuthToken;
import java.sql.*;


/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * Object that directly accesses the auth tokens in the database
 */
public class AuthTokenDao {
    Database db = new Database();
    /**
     * insert a new auth token
     *
     * @param a
     * @return
     */
    public boolean insert(AuthToken a) throws DatabaseException{
        db.openConnection();
        Timestamp t = new Timestamp();

        try {
            Statement stmt = null;
            try {

                String insert = "INSERT INTO authtoken\n" +
                        "VALUES(\n" +
                        "\t'" + a.getAuthToken() + "', \n" +
                        "\t'" + a.getUser() + "', \n" +
                        "\t'" + t.getTimestamp() + "' \n" +
                        ");";

                stmt = db.getConn().createStatement();
                stmt.executeUpdate(insert);
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
     * updates an auth token
     *
     * @param a
     * @return
     */
    public boolean update(AuthToken a){
        return false;
    }

    /**
     * deletes an auth token
     *
     * @param a
     * @return
     */
    public boolean delete(AuthToken a){
        return false;
    }

    /**
     * finds an auth token
     *
     * @return
     */
    public AuthToken find(String s) throws DatabaseException {
        db.openConnection();
        try {
            Statement stmt = null;
            String user;
            try {
                String select = "SELECT * FROM authtoken \n" +
                        "WHERE token = '" + s + "';";

                stmt = db.getConn().createStatement();
                ResultSet rs = stmt.executeQuery(select);

                if (rs.next()){
                    user = rs.getString("user");

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

            AuthToken token = new AuthToken(s, user);

            return token;
        } catch (SQLException e) {
            e.printStackTrace();
            db.closeConnection(true);
            return null;
        }
    }
}
