package service;

import junit.framework.TestCase;

/**
 * Created by Seong-EunCho on 3/6/17.
 */
public class ClearServiceTest extends TestCase {
    private ClearService cs;

    public void setUp() throws Exception {
        super.setUp();
        cs = new ClearService();
    }

    public void testClear() throws Exception {
        cs.clear();
    }

}