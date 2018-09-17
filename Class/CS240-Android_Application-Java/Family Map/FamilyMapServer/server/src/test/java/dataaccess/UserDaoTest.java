package dataaccess;

import junit.framework.TestCase;

import model.User;
import result.LoginResult;

/**
 * Created by Seong-EunCho on 3/3/17.
 */
public class UserDaoTest extends TestCase {
    private UserDao ud;
    private User u;

    public void setUp() throws Exception {
        super.setUp();
        ud = new UserDao();
        u = new User("scj14", "ko-ng3513", "scj1420@gmail.com", "Seong", "Cho", "m", "123456");
        Database db = new Database();
        db.openConnection();
        db.createTables();
        db.closeConnection(true);
    }

    public void testInsert() throws Exception {
        ud.insert(u);
    }

    public void testUpdate() throws Exception {

    }

    public void testDelete() throws Exception {

    }

    public void testLogin() throws Exception {
        LoginResult result = ud.login("scj14", "ko-n3513");

    }

    public void testFind() throws Exception {
        User u = ud.find("scj14");
    }

    public void testFindUser() throws Exception{
        ud.findUser("08561784-e293-41e1-bd18-30596c0242e3");
    }

}