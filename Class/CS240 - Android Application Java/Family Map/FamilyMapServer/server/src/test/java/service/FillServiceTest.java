package service;

import junit.framework.TestCase;

/**
 * Created by Seong-EunCho on 3/8/17.
 */
public class FillServiceTest extends TestCase {
    private FillService fs;

    public void setUp() throws Exception {
        super.setUp();
        fs = new FillService();
    }

    public void testFill() throws Exception {
        fs.fill("scj14", 5);
    }

    public void testInsertPersonRecursive() throws Exception {

    }

}