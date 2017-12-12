package result;

import junit.framework.TestCase;

import coder.Encoder;

/**
 * Created by Seong-EunCho on 3/8/17.
 */
public class PersonResultTest extends TestCase {
    PersonResult pr = new PersonResult();

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testGetSrb() throws Exception {
        pr.getSrb().setDescendant("scj14");
        pr.getSrb().setPersonID("7255e93e");
        pr.getSrb().setFirstName("Seong");
        pr.getSrb().setLastName("Cho");
        pr.getSrb().setGender("m");
        pr.getSrb().setFather("7255e93e");
        Encoder encode = new Encoder();
        String s = encode.Encode(pr.getSrb());
        System.out.println(s);
    }

    public void testSetSrb() throws Exception {

    }

    public void testGetErb() throws Exception {

    }

    public void testSetErb() throws Exception {

    }

    public void testIsSuccess() throws Exception {

    }

    public void testSetSuccess() throws Exception {

    }

}