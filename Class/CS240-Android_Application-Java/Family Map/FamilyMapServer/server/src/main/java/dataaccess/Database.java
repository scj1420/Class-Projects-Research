package dataaccess;

import java.sql.*;
import java.util.*;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * Opens and connects to the database.
 */

public class Database{

    static {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conn;

    public void openConnection() throws DatabaseException{
        try {
            final String CONNECTION_URL = "jdbc:sqlite:fms.sqlite";

            // Open a database connection
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DatabaseException("open Connection failed", e);
        }
    }

    public void closeConnection(boolean commit) throws DatabaseException{
        try {
            if (commit) {
                conn.commit();
            } else {
                conn.rollback();
            }

            conn.close();
            conn = null;
        } catch (SQLException e) {
            throw new DatabaseException("closeConnection failed", e);
        }
    }

    public void createTables() throws DatabaseException{
        try {
            Statement stmt = null;
            try {
                String userTable = "CREATE TABLE IF NOT EXISTS user(\n" +
                        "\tusername STRING NOT NULL,\n" +
                        "\tpassword STRING NOT NULL,\n" +
                        "\temail STRING NOT NULL,\n" +
                        "\tfirstname STRING NOT NULL,\n" +
                        "\tlastname STRING NOT NULL,\n" +
                        "\tgender STRING NOT NULL,\n" +
                        "\tpersonID STRING PRIMARY KEY NOT NULL\n" +
                        ");\n";
                String personTable = "CREATE TABLE IF NOT EXISTS person(\n" +
                        "\tpersonID STRING PRIMARY KEY NOT NULL,\n" +
                        "\tdescendant STRING NOT NULL,\n" +
                        "\tfirstname STRING NOT NULL,\n" +
                        "\tlastname STRING NOT NULL,\n" +
                        "\tgender STRING NOT NULL,\n" +
                        "\tfather STRING,\n" +
                        "\tmother STRING,\n" +
                        "\tspouse STRING\n" +
                        ");";
                String eventTable = "CREATE TABLE IF NOT EXISTS event(\n" +
                        "\teventID STRING PRIMARY KEY NOT NULL,\n" +
                        "\tdescendant STRING NOT NULL,\n" +
                        "\tpersonID STRING NOT NULL,\n" +
                        "\tlatitude REAL NOT NULL,\n" +
                        "\tlongitude REAL NOT NULL,\n" +
                        "\tcountry STRING NOT NULL,\n" +
                        "\tcity STRING NOT NULL,\n" +
                        "\teventtype STRING NOT NULL,\n" +
                        "\tyear STRING NOT NULL\n" +
                        ");";
                String authTable = "CREATE TABLE IF NOT EXISTS authtoken(\n" +
                        "\ttoken STRING PRIMARY KEY NOT NULL,\n" +
                        "\tuser STRING NOT NULL,\n" +
                        "\ttimestamp DATETIME NOT NULL DEFAULT(GETDATE())\n" +
                        ");";

                stmt = conn.createStatement();
                stmt.executeUpdate(userTable);
                stmt.executeUpdate(personTable);
                stmt.executeUpdate(eventTable);
                stmt.executeUpdate(authTable);
            } finally {
                if (stmt != null){
                    stmt.close();
                    stmt = null;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("createTables failed", e);
        }
    }

    public void dropTables() throws DatabaseException{
        try {
            Statement stmt = null;
            try {
                String drop = "DROP TABLE user;\n" +
                        "DROP TABLE person;\n" +
                        "DROP TABLE event;\n" +
                        "DROP TABLE authtoken;";

                stmt = conn.createStatement();
                stmt.executeUpdate(drop);
            } finally {
                if (stmt != null){
                    stmt.close();
                    stmt = null;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("dropTables failed", e);
        }
    }

    public Connection getConn(){
        return conn;
    }
}
