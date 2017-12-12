package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Seong-EunCho on 3/7/17.
 */

public class Locations {
    private ArrayList<Location> data;

    public Location randLocation(){
        Random r = new Random();
        int n = r.nextInt(data.size());
        return data.get(n);
    }

    public ArrayList<Location> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "data=" + data +
                '}';
    }
}
