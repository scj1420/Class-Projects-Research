package com.example.seong_euncho.familymapserver.UI;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.seong_euncho.familymapserver.Model.UserModel;
import com.example.seong_euncho.familymapserver.R;
import com.example.seong_euncho.familymapserver.Server.ServerProxy;

import java.io.IOException;

import model.User;
import result.EventsResult;
import result.PeopleResult;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    private Fragment mLoginFragment;
    private Fragment mMapFragment;
    private boolean serverConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance=this;

        FragmentManager fm = getSupportFragmentManager();
        mLoginFragment = fm.findFragmentById(R.id.fragment_container);
        mMapFragment = fm.findFragmentById(R.id.fragment_container);

        if (!UserModel.getInstance().isLoggedIn()){
            mLoginFragment = new LoginFragment();
            fm.beginTransaction().add(R.id.fragment_container, mLoginFragment).commit();

        } else {
            mMapFragment = new MapFragment();
            fm.beginTransaction().replace(R.id.fragment_container, mMapFragment).commit();
        }

    }

    public void onLogin() throws IOException {

        new DownloadPeopleTask().execute(UserModel.getInstance().getsLoginResult().getSrb().getAuthToken());
        new DownloadEventsTask().execute(UserModel.getInstance().getsLoginResult().getSrb().getAuthToken());
        UserModel.getInstance().setLoggedIn(true);

//        FragmentManager fm = getSupportFragmentManager();
//        mMapFragment = new MapFragment();
//        fm.beginTransaction().replace(R.id.fragment_container, mMapFragment).commit();


    }

    public void onLogout() {
        instance = null;
        this.finish();
    }

    public static MainActivity getInstance(){
        return instance;
    }

    private class DownloadPeopleTask extends AsyncTask<String, Void, PeopleResult> {

        @Override
        protected PeopleResult doInBackground(String... params){
            try{
                PeopleResult r = UserModel.getInstance().getsServerProxy().person(params[0]);
                serverConnection = true;
                return r;
            } catch (IOException ioe){
                serverConnection = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(PeopleResult result){
            if (serverConnection){
                UserModel.getInstance().setsPeopleResult(result);

            }
        }
    }

    private class DownloadEventsTask extends AsyncTask<String, Void, EventsResult> {

        @Override
        protected EventsResult doInBackground(String... params){
            try{
                EventsResult r = UserModel.getInstance().getsServerProxy().event(params[0]);
                serverConnection = true;
                return r;
            } catch (IOException ioe){
                serverConnection = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(EventsResult result){
            if (serverConnection){
                UserModel.getInstance().setsEventsResult(result);
                UserModel.getInstance().addNameToEvents();
                UserModel.getInstance().createFilters();
                UserModel.getInstance().filterByEvent();
                UserModel.getInstance().filterBySide();
                UserModel.getInstance().createUserEvents();
                FragmentManager fm = getSupportFragmentManager();
                mMapFragment = new MapFragment();
                fm.beginTransaction().replace(R.id.fragment_container, mMapFragment).commit();
            }
        }
    }

}

