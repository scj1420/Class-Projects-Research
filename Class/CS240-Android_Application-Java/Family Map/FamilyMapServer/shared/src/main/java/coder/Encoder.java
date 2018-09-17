package coder;

import com.google.gson.*;

/**
 * Created by Seong-EunCho on 3/4/17.
 */

public class Encoder {
    public String Encode(Object o){
        Gson gson = new Gson();
        String jsonStr = gson.toJson(o);

        return jsonStr;
    }
}
