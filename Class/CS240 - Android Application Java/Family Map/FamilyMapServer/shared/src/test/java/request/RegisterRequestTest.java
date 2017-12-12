package request;

import junit.framework.TestCase;

/**
 * Created by Seong-EunCho on 3/3/17.
 */
public class RegisterRequestTest extends TestCase {
    private RegisterRequest r;

    public void setUp() throws Exception {
        super.setUp();
        r = new RegisterRequest("testusername", "testpw", "email.gmail.com", "bob", "kim", "male");

    }

    public void testGetUserName() throws Exception {
        assertEquals("testusername", r.getUserName());
    }

    public void testSetUserName() throws Exception {

    }

    public void testGetPassword() throws Exception {

    }

    public void testSetPassword() throws Exception {

    }

    public void testGetEmail() throws Exception {

    }

    public void testSetEmail() throws Exception {

    }

    public void testGetFirstName() throws Exception {

    }

    public void testSetFirstName() throws Exception {

    }

    public void testGetLastName() throws Exception {

    }

    public void testSetLastName() throws Exception {

    }

    public void testGetGender() throws Exception {

    }

    public void testSetGender() throws Exception {

    }

    public void testToString() throws Exception {
        System.out.println(r.toString());
    }
}