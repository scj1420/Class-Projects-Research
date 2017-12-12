package com.example.seong_euncho.familymapserver.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import coder.Decoder;
import coder.Encoder;
import model.AuthToken;
import request.LoadRequest;
import request.LoginRequest;
import request.RegisterRequest;
import result.ClearResult;
import result.EventResult;
import result.EventsResult;
import result.FillResult;
import result.LoadResult;
import result.LoginResult;
import result.PeopleResult;
import result.PersonResult;
import result.RegisterResult;

/**
 * Created by Seong-EunCho on 2/17/17.
 */

public class ServerProxy {
    Decoder decoder = new Decoder();
    AuthToken token = new AuthToken();

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    String host;
    String port;

    public String post(String toSend, String urlEnding) throws IOException {
        String urlString = "http://" + host + ":" + port + urlEnding;
        System.out.println("url = " + urlString);

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // optional default is POST
        con.setRequestMethod("POST");
//        con.setRequestProperty("Authorization", "12345");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(toSend);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + toSend);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());


        return response.toString();
    }
    public String get(String urlEnding, String token) throws IOException{
        String urlString = "http://" + host + ":" + port + urlEnding;
        System.out.println("url = " + urlString);

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", token);


        // Send get request
        //con.setDoOutput(true);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result

        return response.toString();
    }

    /**
     * Creates a new user account, generates 4 generations of ancestor data
     * for the new user, logs the user in, and returns an auth token.
     *
     * @param r -- RegisterRequest object
     * @return RegisterResult body
     */
    public RegisterResult register(RegisterRequest r) throws IOException {
        Encoder encoder = new Encoder();
        String serialized  = encoder.Encode(r);
        String s = post(serialized, "/user/register");

        RegisterResult result = new RegisterResult();
        result.setSrb(decoder.decodeRegisterSuccess(s));
        if (result.getSrb().getAuthToken() == null){
            result.setSuccess(false);
            result.setErb(decoder.decodeRegisterError(s));
        }

        return result;
    }

    /**
     * Returns the single person object with the specified ID.
     *
     * @param personID -- Non-empty string containing the Person ID
     * @return PersonResult body
     */
    public PersonResult person(String personID, String token) throws IOException {
        String urlEnding = "/person/" + personID;
        String s = get(urlEnding, token);

        PersonResult result = new PersonResult();
        result.setSrb(decoder.decodePersonSuccess(s));
        if (result.getSrb().getPersonID() == null){
            result.setSuccess(false);
            result.setErb(decoder.decodePersonError(s));
        }

        return result;
    }

    /**
     * Returns ALL family members of the current user. The current user is
     * determined from the provided auth token.
     *
     * @return PeopleResult body
     */
    public PeopleResult person(String token) throws IOException {
        String urlEnding = "/person";
        String s = get(urlEnding, token);

        PeopleResult result = new PeopleResult();
        result.setSrb(decoder.decodePeopleSuccess(s));
        if (result.getSrb().getData() == null){
            result.setSuccess(false);
            result.setErb(decoder.decodePeopleError(s));
        }

        return result;
    }

    /**
     * Logs in the user and returns an auth token.
     *
     * @param r -- LongRequest object
     * @return LoginResult body
     */
    public LoginResult login(LoginRequest r) throws IOException {
        Encoder encoder = new Encoder();
        String serialized  = encoder.Encode(r);
        String s = post(serialized, "/user/login");

        LoginResult result = new LoginResult();
        result.setSrb(decoder.decodeLoginSuccess(s));
        if (result.getSrb().getAuthToken() == null){
            result.setSuccess(false);
            result.setErb(decoder.decodeLoginError(s));
        }

        return result;
    }

    /**
     * Clears all data from the database (just like the /clear API), and then loads the
     * posted user, person, and event data into the database.
     *
     * @param r -- LoadRequest object
     * @return LoadResult body
     */
    public LoadResult load(LoadRequest r) throws IOException {
        Encoder encoder = new Encoder();
        String serialized  = encoder.Encode(r);
        String s = post(serialized, "/load");

        LoadResult result = decoder.decodeLoadResult(s);

        return result;
    }

    /**
     * Populates the server's database with generated data for the specified user name.
     * The required "username" parameter must be a user already registered with the server. If there is
     * any data in the database already associated with the given user name, it is deleted. The
     * optional “generations” parameter lets the caller specify the number of generations of ancestors
     * to be generated, and must be a non-negative integer (the default is 4, which results in 31 new
     * persons each with associated events).
     *
     * @param userName -- the user's username
     * @param generations -- the number of generations of ancestors to be generated
     * @return FillResult body
     */
    public FillResult fill(String userName,
                    int generations) throws IOException{

        String urlEnding = "/fill/" + userName + "/" + generations;
        String urlString = "http://" + host + ":" + port + urlEnding;
        System.out.println("url = " + urlString);

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // optional default is POST
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        FillResult result = decoder.decodeFillResult(response.toString());

        return result;
    }
    public FillResult fill(String userName) throws IOException{

        String urlEnding = "/fill/" + userName;
        String urlString = "http://" + host + ":" + port + urlEnding;
        System.out.println("url = " + urlString);

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // optional default is POST
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        FillResult result = decoder.decodeFillResult(response.toString());

        return result;
    }

    /**
     * The event method
     *
     * @param eventID -- Non-empty string containing the Event ID
     * @return EventResult body
     */
    public EventResult event(String eventID, String token) throws IOException {
        String urlEnding = "/event/" + eventID;
        String s = get(urlEnding, token);

        EventResult result = new EventResult();
        result.setSrb(decoder.decodeEventSuccess(s));
        if (result.getSrb().getEventID() == null){
            result.setSuccess(false);
            result.setErb(decoder.decodeEventError(s));
        }

        return result;
    }

    /**
     * Returns ALL events for ALL family members of the current user. The current
     * user is determined from the provided auth token.
     *
     * @return
     */
    public EventsResult event(String token) throws IOException {
        String urlEnding = "/event";
        String s = get(urlEnding, token);

        EventsResult result = new EventsResult();
        result.setSrb(decoder.decodeEventsSuccess(s));
        if (result.getSrb().getData() == null){
            result.setSuccess(false);
            result.setErb(decoder.decodeEventsError(s));
        }

        return result;
    }

    /**
     * Deletes ALL data from the database, including user accounts, auth tokens,
     * and generated person and event data.
     *
     * @return ClearResult body
     */
    public ClearResult clear() throws IOException{
        String urlEnding = "/clear";
        String urlString = "http://" + host + ":" + port + urlEnding;
        System.out.println("url = " + urlString);

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // optional default is POST
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        ClearResult result = decoder.decodeClearResult(response.toString());

        return result;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
