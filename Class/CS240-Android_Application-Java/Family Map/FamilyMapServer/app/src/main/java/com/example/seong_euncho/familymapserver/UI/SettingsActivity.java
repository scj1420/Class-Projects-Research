package com.example.seong_euncho.familymapserver.UI;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.seong_euncho.familymapserver.Model.UserModel;
import com.example.seong_euncho.familymapserver.R;

import java.io.IOException;

import result.EventsResult;
import result.PeopleResult;

/**
 * Created by Seong-EunCho on 4/14/17.
 */

public class SettingsActivity extends AppCompatActivity {

    private Switch
            mLifeStoryLinesSwitch,
            mFamilyTreeLinesSwitch,
            mSpouseLinesSwitch;
    private Spinner
            mLifeStoryLinesSpinner,
            mFamilyTreeLinesSpinner,
            mSpouseLinesSpinner,
            mMapTypeSpinner;
    private RelativeLayout
            mReSyncData,
            mLogout;

    private boolean serverConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Family Map: Settings");

        ArrayAdapter<CharSequence> colorArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> mapTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.map_type_array, android.R.layout.simple_spinner_dropdown_item);

        mLifeStoryLinesSwitch = (Switch) findViewById(R.id.life_story_lines_switch);
        mLifeStoryLinesSwitch.setChecked(UserModel.getInstance().getsSettings().isLifeStoryOn());
        mLifeStoryLinesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserModel.getInstance().getsSettings().setLifeStoryOn(isChecked);
            }
        });
        mLifeStoryLinesSpinner = (Spinner) findViewById(R.id.life_story_lines_spinner);
        mLifeStoryLinesSpinner.setAdapter(colorArrayAdapter);
        mLifeStoryLinesSpinner.setSelection(UserModel.getInstance().getsSettings().getLifeStoryLinesColor());
        mLifeStoryLinesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        UserModel.getInstance().getsSettings().setLifeStoryLinesColor(0);
                        break;
                    case 1:
                        UserModel.getInstance().getsSettings().setLifeStoryLinesColor(1);
                        break;
                    case 2:
                        UserModel.getInstance().getsSettings().setLifeStoryLinesColor(2);
                        break;
                    default:
                        Log.e("Life line selection", "Got an invalid color");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mFamilyTreeLinesSwitch = (Switch) findViewById(R.id.family_tree_lines_switch);
        mFamilyTreeLinesSwitch.setChecked(UserModel.getInstance().getsSettings().isFamilyTreeOn());
        mFamilyTreeLinesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserModel.getInstance().getsSettings().setFamilyTreeOn(isChecked);
            }
        });
        mFamilyTreeLinesSpinner = (Spinner) findViewById(R.id.family_tree_lines_spinner);
        mFamilyTreeLinesSpinner.setAdapter(colorArrayAdapter);
        mFamilyTreeLinesSpinner.setSelection(UserModel.getInstance().getsSettings().getFamilyTreeLinesColor());
        mFamilyTreeLinesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        UserModel.getInstance().getsSettings().setFamilyTreeLinesColor(0);
                        break;
                    case 1:
                        UserModel.getInstance().getsSettings().setFamilyTreeLinesColor(1);
                        break;
                    case 2:
                        UserModel.getInstance().getsSettings().setFamilyTreeLinesColor(2);
                        break;
                    default:
                        Log.e("Spouse line selection", "Got an invalid color");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpouseLinesSwitch = (Switch) findViewById(R.id.spouse_lines_switch);
        mSpouseLinesSwitch.setChecked(UserModel.getInstance().getsSettings().isSpouseOn());
        mSpouseLinesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserModel.getInstance().getsSettings().setSpouseOn(isChecked);
            }
        });
        mSpouseLinesSpinner = (Spinner) findViewById(R.id.spouse_lines_spinner);
        mSpouseLinesSpinner.setAdapter(colorArrayAdapter);
        mSpouseLinesSpinner.setSelection(UserModel.getInstance().getsSettings().getSpouseLinesColor());
        mSpouseLinesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        UserModel.getInstance().getsSettings().setSpouseLinesColor(0);
                        break;
                    case 1:
                        UserModel.getInstance().getsSettings().setSpouseLinesColor(1);
                        break;
                    case 2:
                        UserModel.getInstance().getsSettings().setSpouseLinesColor(2);
                        break;
                    default:
                        Log.e("Spouse line selection", "Got an invalid color");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mMapTypeSpinner = (Spinner) findViewById(R.id.map_type_spinner);
        mMapTypeSpinner.setAdapter(mapTypeAdapter);
        mMapTypeSpinner.setSelection(UserModel.getInstance().getsSettings().getMapType());
        mMapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        UserModel.getInstance().getsSettings().setMapType(0);
                        break;
                    case 1:
                        UserModel.getInstance().getsSettings().setMapType(1);
                        break;
                    case 2:
                        UserModel.getInstance().getsSettings().setMapType(2);
                        break;
                    case 3:
                        UserModel.getInstance().getsSettings().setMapType(3);
                        break;
                    default:
                        Log.e("MapType selection", "Got an invalid map type");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mReSyncData = (RelativeLayout) findViewById(R.id.re_sync_data_layout);
        mReSyncData.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new DownloadPeopleTask().execute(UserModel.getInstance().getsLoginResult().getSrb().getAuthToken());
                new DownloadEventsTask().execute(UserModel.getInstance().getsLoginResult().getSrb().getAuthToken());

            }
        });

        mLogout = (RelativeLayout) findViewById(R.id.logout_layout);
        mLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserModel.getInstance().logout();
                MainActivity.getInstance().onLogout();
                startActivity(getParentActivityIntent());
                finish();

            }
        });
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
                UserModel.getInstance().filterByEvent();
                UserModel.getInstance().filterBySide();
                UserModel.getInstance().createUserEvents();

                Toast.makeText(getApplicationContext(), "Data re-synced", Toast.LENGTH_SHORT).show();
                MainActivity.getInstance().recreate();
                finish();

            } else {
                Toast.makeText(getApplicationContext(),
                        "Re-sync failed! Try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
