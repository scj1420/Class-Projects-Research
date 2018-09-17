package handler;

import java.io.*;
import java.net.*;

import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.*;

import coder.Decoder;
import coder.Encoder;
import dataaccess.DatabaseException;
import request.LoginRequest;
import result.LoginResult;
import service.LoginService;

/**
 * Created by Seong-EunCho on 3/2/17.
 */

public class LoginHandler implements HttpHandler {
    private LoginResult result;
    Encoder encoder = new Encoder();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        boolean success = false;

        //try {
        if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {

            Headers reqHeaders = httpExchange.getRequestHeaders();

            InputStream reqBody = httpExchange.getRequestBody();
            String body = readString(reqBody);
            System.out.println(body);
            Decoder decoder = new Decoder();
            try {
                LoginRequest r = decoder.decodeLogin(body);
                System.out.println(r.toString());
                LoginService ls = new LoginService();
                result = ls.login(r);

            } catch (DatabaseException e) {
                result = new LoginResult();
                result.getErb().setMessage("Internal server error.");
                result.setSuccess(false);
            } catch(JsonSyntaxException e) {
                result = new LoginResult();
                result.getErb().setMessage("Request is missing a required field.");
                result.setSuccess(false);
            }


            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            String sendBackString;
            if (result.isSuccess()){
                sendBackString = encoder.Encode(result.getSrb());
            } else {
                sendBackString = encoder.Encode(result.getErb());
            }
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
