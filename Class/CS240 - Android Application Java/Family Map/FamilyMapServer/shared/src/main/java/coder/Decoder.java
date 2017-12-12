package coder;

import com.google.gson.*;

import java.util.ArrayList;

import model.Locations;
import model.Names;
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
 * Created by Seong-EunCho on 3/2/17.
 */

public class Decoder {
    Gson gson;

    public Decoder(){
        gson = new Gson();
    }

    public RegisterRequest decodeRegister(String s){
        RegisterRequest r = gson.fromJson(s, RegisterRequest.class);
        return r;
    }

    public Names decodeNames(String s){
        Names n = gson.fromJson(s, Names.class);
        return n;
    }

    public Locations decodeLocations(String s){
        Locations l = gson.fromJson(s, Locations.class);
        return l;
    }

    public LoadRequest decodeLoad(String s){
        LoadRequest l = gson.fromJson(s, LoadRequest.class);
        return l;
    }

    public LoginRequest decodeLogin(String s){
        LoginRequest l = gson.fromJson(s, LoginRequest.class);
        return l;
    }

    public RegisterResult.SuccessResponseBody decodeRegisterSuccess(String s){
        RegisterResult.SuccessResponseBody r = gson.fromJson(s, RegisterResult.SuccessResponseBody.class);
        return r;
    }

    public RegisterResult.ErrorResponseBody decodeRegisterError(String s){
        RegisterResult.ErrorResponseBody r = gson.fromJson(s, RegisterResult.ErrorResponseBody.class);
        return r;
    }

    public PersonResult.SuccessResponseBody decodePersonSuccess(String s){
        PersonResult.SuccessResponseBody r = gson.fromJson(s, PersonResult.SuccessResponseBody.class);
        return r;
    }

    public PersonResult.ErrorResponseBody decodePersonError(String s){
        PersonResult.ErrorResponseBody r = gson.fromJson(s, PersonResult.ErrorResponseBody.class);
        return r;
    }

    public PeopleResult.SuccessResponseBody decodePeopleSuccess(String s){
        PeopleResult.SuccessResponseBody r = gson.fromJson(s, PeopleResult.SuccessResponseBody.class);
        return r;
    }

    public PeopleResult.ErrorResponseBody decodePeopleError(String s){
        PeopleResult.ErrorResponseBody r = gson.fromJson(s, PeopleResult.ErrorResponseBody.class);
        return r;
    }

    public LoginResult.SuccessResponseBody decodeLoginSuccess(String s){
        LoginResult.SuccessResponseBody r = gson.fromJson(s, LoginResult.SuccessResponseBody.class);
        return r;
    }

    public LoginResult.ErrorResponseBody decodeLoginError(String s){
        LoginResult.ErrorResponseBody r = gson.fromJson(s, LoginResult.ErrorResponseBody.class);
        return r;
    }

    public LoadResult decodeLoadResult(String s){
        LoadResult r = gson.fromJson(s, LoadResult.class);
        return r;
    }

    public FillResult decodeFillResult(String s){
        FillResult r = gson.fromJson(s, FillResult.class);
        return r;
    }

    public EventResult.SuccessResponseBody decodeEventSuccess(String s){
        EventResult.SuccessResponseBody r = gson.fromJson(s, EventResult.SuccessResponseBody.class);
        return r;
    }

    public EventResult.ErrorResponseBody decodeEventError(String s){
        EventResult.ErrorResponseBody r = gson.fromJson(s, EventResult.ErrorResponseBody.class);
        return r;
    }

    public EventsResult.SuccessResponseBody decodeEventsSuccess(String s){
        EventsResult.SuccessResponseBody r = gson.fromJson(s, EventsResult.SuccessResponseBody.class);
        return r;
    }

    public EventsResult.ErrorResponseBody decodeEventsError(String s){
        EventsResult.ErrorResponseBody r = gson.fromJson(s, EventsResult.ErrorResponseBody.class);
        return r;
    }

    public ClearResult decodeClearResult(String s){
        ClearResult r = gson.fromJson(s, ClearResult.class);
        return r;
    }
}
