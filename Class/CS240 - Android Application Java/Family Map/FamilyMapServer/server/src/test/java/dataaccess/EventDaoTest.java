package dataaccess;

import junit.framework.TestCase;

import result.EventResult;
import result.EventsResult;

/**
 * Created by Seong-EunCho on 3/8/17.
 */
public class EventDaoTest extends TestCase {
    private EventDao ed;

    public void setUp() throws Exception {
        super.setUp();
        ed = new EventDao();
        Database db = new Database();
        db.openConnection();
        db.createTables();
        db.closeConnection(true);
    }

    public void testInsert() throws Exception {
        EventResult result = ed.find("70b828b8-352f-4049-b4b0-b7ac11f6b99d");

    }

    public void testUpdate() throws Exception {

    }

    public void testDelete() throws Exception {
        ed.delete("scj14");
    }

    public void testFind() throws Exception {

    }

    public void testRetrieve() throws Exception {
        EventsResult result = ed.retrieve("scj14");
    }
}