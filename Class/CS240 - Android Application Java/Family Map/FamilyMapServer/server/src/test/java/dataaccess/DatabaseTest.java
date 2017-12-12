package dataaccess;

import junit.framework.TestCase;

import org.junit.Test;

import java.awt.image.DataBuffer;

/**
 * Created by Seong-EunCho on 3/2/17.
 */
public class DatabaseTest extends TestCase {
    private Database db;

    public void setUp() throws Exception {
        super.setUp();
        db = new Database();

    }
    @Test
    public void testOpenConnection() throws Exception {
        db.openConnection();
    }

    public void testCloseConnection() throws Exception {

    }
    @Test
    public void testCreateTables() throws Exception {
        db.openConnection();
        db.createTables();
        db.closeConnection(true);
    }

    public void testGetConn() throws Exception {

    }

    public void testDropTables() throws Exception {
        db.openConnection();
        db.dropTables();
        db.closeConnection(true);
    }
}