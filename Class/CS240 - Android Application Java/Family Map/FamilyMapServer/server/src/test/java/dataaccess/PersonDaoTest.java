package dataaccess;

import junit.framework.TestCase;

import java.util.ArrayList;

import model.Person;
import model.User;
import result.PeopleResult;
import result.PersonResult;

/**
 * Created by Seong-EunCho on 3/8/17.
 */
public class PersonDaoTest extends TestCase {
    private PersonDao pd;

    public void setUp() throws Exception {
        super.setUp();
        pd = new PersonDao();
        Database db = new Database();
        db.openConnection();
        db.createTables();
        db.closeConnection(true);
    }

    public void testInsert() throws Exception {
        User user = new User("testid", "testpw", "test@gmail.com", "testfn", "testln", "male", "21234");
        Person p = new Person("12345", user, "firstname", "lastname", "m", null, null, null);
        assertEquals(true, pd.insert(p));
    }

    public void testUpdate() throws Exception {

    }

    public void testDelete() throws Exception {
        pd.delete("scj14");
    }

    public void testFind() throws Exception {
        PersonResult result = pd.find("19a17085-5aa5-4e74-9c9e9aec0757");

    }

    public void testRetrieve() throws Exception {
        PeopleResult result = pd.retrieve("scj14");
    }
}