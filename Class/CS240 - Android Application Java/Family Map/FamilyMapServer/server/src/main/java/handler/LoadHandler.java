package handler;

import java.io.*;
import java.net.*;

import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.*;

import coder.Decoder;
import coder.Encoder;
import dataaccess.DatabaseException;
import request.LoadRequest;
import result.LoadResult;
import service.LoadService;

/**
 * Created by Seong-EunCho on 3/2/17.
 */

public class LoadHandler implements HttpHandler {
    LoadResult result;
    private Encoder encoder = new Encoder();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        boolean success = false;

        //try {
        if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {

            Headers reqHeaders = httpExchange.getRequestHeaders();
            // if (reqHeaders.containsKey("Authorization")){
            //String authToken = reqHeaders.getFirst("Authorization");
            //if (authToken.equals("Some Auth Token")){
            InputStream reqBody = httpExchange.getRequestBody();
            String body = readString(reqBody);
            System.out.println(body);
            Decoder decoder = new Decoder();
            try {
                LoadRequest r = decoder.decodeLoad(body);
                System.out.println(r.toString());

                boolean correctGenderInput = true;
                for (int i = 0; i < r.getUsers().size(); i++){
                    if (!(r.getUsers().get(i).getGender().equals("m")) && !(r.getUsers().get(i).getGender().equals("f"))){
                        correctGenderInput = false;
                        break;
                    }
                }
                for (int i = 0; i < r. getPersons().size(); i++){
                    if (!(r.getPersons().get(i).getGender().equals("m") || r.getPersons().get(i).getGender().equals("f"))){
                        correctGenderInput = false;
                        break;
                    }
                }
                if (correctGenderInput){
                    LoadService ls = new LoadService();
                    result = ls.load(r);
                    System.out.println("Here!! " + r.toString());
                } else {
                    result = new LoadResult();
                    result.setMessage("Gender must be 'm' or 'f'.");
                }

            } catch (DatabaseException e) {
                result = new LoadResult();
                result.setMessage("Internal server error.");
            } catch(JsonSyntaxException e) {
                result = new LoadResult();
                result.setMessage("Request has an invalid input");
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

    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0){
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
