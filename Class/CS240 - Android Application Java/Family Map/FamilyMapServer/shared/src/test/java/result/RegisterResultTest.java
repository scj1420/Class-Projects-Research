package result;

import junit.framework.TestCase;

import coder.Encoder;

/**
 * Created by Seong-EunCho on 3/8/17.
 */
public class RegisterResultTest extends TestCase {
    RegisterResult result = new RegisterResult();

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testGetSrb() throws Exception {
        result.getSrb().setPersonID("12345");
        result.getSrb().setAuthToken("12345");
        result.getSrb().setUserName("scj14");
        Encoder encoder = new Encoder();
        String jsonstr = encoder.Encode(result.getSrb());
        System.out.println(jsonstr);
    }

    public void testSetSrb() throws Exception {

    }

    public void testGetErb() throws Exception {
        result.getErb().setMessage("Error");
        Encoder encoder = new Encoder();
        String jsonstr = encoder.Encode(result.getErb());
        System.out.println(jsonstr);
    }

    public void testSetErb() throws Exception {

    }

    public void testIsSuccess() throws Exception {

    }

    public void testSetSuccess() throws Exception {

    }

}