package model;

import junit.framework.TestCase;

/**
 * Created by Seong-EunCho on 3/1/17.
 */
public class UserTest extends TestCase {
    private User user;

    public void setUp() throws Exception {
        super.setUp();
        user = new User("testusername", "testpw", "email.gmail.com", "bob", "kim", "male", "124k");

    }

    public void testGetUsername() throws Exception {
        assertEquals("testusername", user.getUsername());
    }

    public void testSetUsername() throws Exception {

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

    public void testGetPersonID() throws Exception {

    }

    public void testSetPersonID() throws Exception {

    }

}