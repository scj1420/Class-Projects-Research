package model;

import java.util.*;

/**
 * Created by Seong-EunCho on 3/7/17.
 */

public class Names {
    private ArrayList<String> data;

    public String randName(){
        Random r = new Random();
        int n = r.nextInt(data.size());
        return data.get(n);
    }

    @Override
    public String toString() {
        return "Names{" +
                "data=" + data +
                '}';
    }
}
