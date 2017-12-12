package handler;

import junit.framework.TestCase;

import coder.Decoder;
import request.RegisterRequest;

/**
 * Created by Seong-EunCho on 3/7/17.
 */
public class RegisterHandlerTest extends TestCase {
    private RegisterHandler rh;
    private Decoder d;

    public void setUp() throws Exception {
        super.setUp();
        rh = new RegisterHandler();
        d = new Decoder();

    }

    public void testHandle() throws Exception {
        String jsonstr = "{\"userName\":\"testusername\",\"password\":\"testpw\",\"email\":\"email.gmail.com\",\"firstName\":\"bob\",\"lastName\":\"kim\",\"gender\":\"male\"}";
        RegisterRequest r = d.decodeRegister(jsonstr);
        System.out.println(r.toString());
    }

}