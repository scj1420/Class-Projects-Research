package handler;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.sun.net.httpserver.*;

import coder.Encoder;
import dataaccess.AuthTokenDao;
import dataaccess.DatabaseException;
import model.AuthToken;
import result.EventResult;
import result.EventsResult;
import service.EventService;

/**
 * Created by Seong-EunCho on 3/2/17.
 */

public class EventHandler implements HttpHandler {
    private EventResult result1;
    private EventsResult result2;
    private Encoder encoder = new Encoder();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        boolean success = false;
        try {
            if (httpExchange.getRequestMethod().toLowerCase().equals("get")) {
                Headers reqHeaders = httpExchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {
                    String token = reqHeaders.getFirst("Authorization");
                    AuthTokenDao atd = new AuthTokenDao();
                    AuthToken authToken = null;
                    try {
                        authToken = atd.find(token);
                    } catch (DatabaseException e) {
                        System.err.println("Authentication error.");
                        e.printStackTrace();
                    }

                    if(authToken == null){
                        result1 = new EventResult();
                        System.out.println("Invalid authentication");
                        result1.getErb().setMessage("Invalid authentication");
                        System.out.println("here");
                        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        String sendBackString = encoder.Encode(result1.getErb());
                        System.out.println(sendBackString);
                        byte[] response = sendBackString.getBytes();
                        httpExchange.getResponseBody().write(response);
                        httpExchange.getResponseBody().flush();
                        httpExchange.getResponseBody().close();
                        return;
                    }

                    if (token.equals(authToken.getAuthToken())) {
                        System.out.println("Access authorized");

                        ArrayList<String> list = new ArrayList<String>();
                        String path = httpExchange.getRequestURI().toString();
                        for (String s : path.split("/")) {
                            list.add(s);
                        }

                        if (list.size() == 3){

                            EventService es = new EventService();
                            try {
                                result1 = es.event(list.get(2), authToken.getUser());
                            } catch (DatabaseException e) {
                                result1 = new EventResult();
                                result1.setSuccess(false);
                                result1.getErb().setMessage("Internal server error.");
                            }

                            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                            String sendBackString;
                            if (result1.isSuccess()){
                                sendBackString = encoder.Encode(result1.getSrb());
                                System.out.println(sendBackString);
                            } else {
                                sendBackString = encoder.Encode(result1.getErb());
                                System.out.println(sendBackString);
                            }
                            byte[] response = sendBackString.getBytes();
                            httpExchange.getResponseBody().write(response);
                            httpExchange.getResponseBody().flush();
                            httpExchange.getResponseBody().close();
                            return;
                        } else {
                            EventService es = new EventService();
                            try {
                                result2 = es.events(authToken.getUser());
                            } catch (DatabaseException e) {
                                result2 = new EventsResult();
                                result2.setSuccess(false);
                                result2.getErb().setMessage("Internal server error.");
                            }

                            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                            String sendBackString;
                            if (result2.isSuccess()){
                                sendBackString = encoder.Encode(result2.getSrb());
                            } else {
                                sendBackString = encoder.Encode(result2.getErb());
                            }
                            byte[] response = sendBackString.getBytes();
                            httpExchange.getResponseBody().write(response);
                            httpExchange.getResponseBody().flush();
                            httpExchange.getResponseBody().close();
                            return;
                        }
                    } else {
                        result1 = new EventResult();
                        System.out.println("Invalid authentication");
                        result1.getErb().setMessage("Invalid authentication");
                        System.out.println("here2");

                        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        String sendBackString = encoder.Encode(result1.getErb());
                        System.out.println(sendBackString);
                        byte[] response = sendBackString.getBytes();
                        httpExchange.getResponseBody().write(response);
                        httpExchange.getResponseBody().flush();
                        httpExchange.getResponseBody().close();
                        return;
                    }
                }
            }
            if (!success) {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException e) {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            e.printStackTrace();
            httpExchange.getResponseBody().close();
        }
    }
}

