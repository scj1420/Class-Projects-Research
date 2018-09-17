package handler;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.sun.net.httpserver.*;

import coder.Decoder;
import coder.Encoder;
import dataaccess.DatabaseException;
import result.FillResult;
import service.FillService;

/**
 * Created by Seong-EunCho on 3/2/17.
 */

public class FillHandler implements HttpHandler {
    private FillResult result;
    private Encoder encoder = new Encoder();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        boolean success = false;
        if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {
            ArrayList<String> list = new ArrayList<String>();
            String path = httpExchange.getRequestURI().toString();
            for (String s : path.split("/")){
                list.add(s);
            }

            int generation;
            if (list.size() < 4){
                generation = 4;
            } else {
                generation = Integer.parseInt(list.get(3));
            }

            if (generation <= 0) {
                result.setMessage("Generation must be greater than 0.");
            } else {
                Decoder decoder = new Decoder();
                try {
                    FillService fs = new FillService();
                    result = fs.fill(list.get(2), generation);
                } catch (DatabaseException e) {
                    result = new FillResult();
                    result.setMessage("Internal server error");
                }
            }

            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            String sendBackString = encoder.Encode(result);
            byte[] response = sendBackString.getBytes();
            httpExchange.getResponseBody().write(response);
            httpExchange.getResponseBody().flush();
            httpExchange.getResponseBody().close();
            return;
            // }
            // }
            //}
        }
        if (!success){
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
    }
}
