package service;

import dataaccess.Database;
import dataaccess.DatabaseException;
import result.ClearResult;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

/**
 * The object that performs the clear function
 */
public class ClearService {
    ClearResult result = new ClearResult();

    /**
     * Deletes ALL data from the database, including user accounts, auth tokens,
     * and generated person and event data.
     *
     * @return ClearResult body
     */
    public ClearResult clear() throws DatabaseException {
        Database db = new Database();
        db.openConnection();
        db.dropTables();
        db.createTables();
        db.closeConnection(true);

        result.setMessage("Clear succeeded.");
        return result;
    }
}
