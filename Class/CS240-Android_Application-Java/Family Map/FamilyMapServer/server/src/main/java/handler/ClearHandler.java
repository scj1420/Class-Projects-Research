package handler;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

import coder.Encoder;
import dataaccess.DatabaseException;
import result.ClearResult;
import service.ClearService;

/**
 * Created by Seong-EunCho on 3/2/17.
 */

public class ClearHandler implements HttpHandler {
    private ClearResult result;
    private Encoder encoder = new Encoder();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        boolean success = false;
        //try {
        if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {

            try {
                ClearService cs = new ClearService();
                result = cs.clear();

            } catch (DatabaseException e) {
                result = new ClearResult();
                result.setMessage("Internal server error.");
            }


            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            String sendBackString = encoder.Encode(result);
            System.out.println(sendBackString);
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
