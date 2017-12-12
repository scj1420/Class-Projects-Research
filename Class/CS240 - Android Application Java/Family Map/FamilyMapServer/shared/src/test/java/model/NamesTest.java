package model;

import junit.framework.TestCase;

import java.util.Random;

/**
 * Created by Seong-EunCho on 3/7/17.
 */
public class NamesTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();

    }

    public void testRandName() throws Exception {
        Random r = new Random();
        int n = r.nextInt(100) + 1;
        System.out.println(n);
    }

    public void testToString() throws Exception {

    }

}