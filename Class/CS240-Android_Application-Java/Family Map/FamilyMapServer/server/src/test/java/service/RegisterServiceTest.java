package service;

import junit.framework.TestCase;

import org.junit.Test;

import request.RegisterRequest;

/**
 * Created by Seong-EunCho on 3/3/17.
 */
public class RegisterServiceTest extends TestCase {
    private RegisterService rs;
    private RegisterRequest r;

    public void setUp() throws Exception {
        super.setUp();
        rs = new RegisterService();
        r = new RegisterRequest("scj14", "ko-ng3513", "scj1420@gmail.com", "Seong", "Cho", "m");
    }
    @Test
    public void testRegister() throws Exception {
        rs.register(r);
    }

}