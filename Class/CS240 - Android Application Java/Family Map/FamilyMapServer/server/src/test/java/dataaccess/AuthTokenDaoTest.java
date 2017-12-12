package dataaccess;

import junit.framework.TestCase;

import model.AuthToken;
import service.Generator;

/**
 * Created by Seong-EunCho on 3/10/17.
 */
public class AuthTokenDaoTest extends TestCase {
    private AuthTokenDao atd;

    public void setUp() throws Exception {
        super.setUp();
        atd = new AuthTokenDao();
        Database db = new Database();
        db.openConnection();
        db.createTables();
        db.closeConnection(true);
    }

    public void testInsert() throws Exception {
        Generator gen = new Generator();
        AuthToken a = new AuthToken(gen.generateID(), gen.generateID());
        atd.insert(a);
    }

    public void testUpdate() throws Exception {

    }

    public void testDelete() throws Exception {

    }

    public void testFind() throws Exception {

    }

}