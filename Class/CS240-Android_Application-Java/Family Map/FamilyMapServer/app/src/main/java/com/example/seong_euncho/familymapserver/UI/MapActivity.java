package com.example.seong_euncho.familymapserver.UI;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seong_euncho.familymapserver.Model.UserModel;
import com.example.seong_euncho.familymapserver.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import result.EventsResult;
import result.PeopleResult;

/**
 * Created by Seong-EunCho on 4/19/17.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView mIcon;
    private TextView mPersonName;
    private TextView mEventDescription;
    private Map<Integer, EventsResult.Event> MarkerToEventMap = new HashMap<>();
    private List<Polyline> mPolylines = new ArrayList<>();
    private LinearLayout mInformationDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        getSupportActionBar().setTitle("Fly Map");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mInformationDisplay = (LinearLayout) findViewById(R.id.information_display_layout);
        mInformationDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PersonActivity.class));
            }
        });

        EventsResult.Event e = UserModel.getInstance().getsCurrentEvent();
        PeopleResult.Person p = UserModel.getInstance().retrievePerson(e.getPerson());

        mIcon = (ImageView) findViewById(R.id.marker_image);
        mPersonName = (TextView) findViewById(R.id.marker_person_name);
        mEventDescription = (TextView) findViewById(R.id.marker_event_description);

        if (p.getGender().equals("m")){
            Drawable maleIcon = new IconDrawable(getApplicationContext(),
                    Iconify.IconValue.fa_male).colorRes(R.color.color_male).sizeDp(60);
            mIcon.setImageDrawable(maleIcon);
        } else {
            Drawable femaleIcon = new IconDrawable(getApplicationContext(),
                    Iconify.IconValue.fa_female).colorRes(R.color.color_female).sizeDp(60);
            mIcon.setImageDrawable(femaleIcon);

        }
        mPersonName.setText(p.getFirstName() + " " + p.getLastName());
        mEventDescription.setText(e.getEventType()
                + ": " + e.getCity() + " " + e.getCountry() + " (" + e.getYear() + ")");

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng currentLoc = new LatLng(UserModel.getInstance().getsCurrentEvent().getLatitude(),
                                UserModel.getInstance().getsCurrentEvent().getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLoc));

        switch (UserModel.getInstance().getsSettings().getMapType()){
            case 0:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            default:
                break;
        }

        addMarkers();

        EventsResult.Event e = UserModel.getInstance().getsCurrentEvent();
        PeopleResult.Person p = UserModel.getInstance().retrievePerson(e.getPerson());
        for (Polyline polyline : mPolylines){
            polyline.remove();
        }
        mPolylines.clear();
        drawSpouseLines(e, p);
        drawFamilyLines(e, p);
        drawLifeStoryLines(e, p);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                EventsResult.Event e = MarkerToEventMap.get(marker.hashCode());
                UserModel.getInstance().setsCurrentEvent(e);
                PeopleResult.Person p = UserModel.getInstance().retrievePerson(e.getPerson());

                for (Polyline polyline : mPolylines){
                    polyline.remove();
                }
                mPolylines.clear();
                drawSpouseLines(e, p);
                drawFamilyLines(e, p);
                drawLifeStoryLines(e, p);

                if (p.getGender().equals("m")){
                    Drawable maleIcon = new IconDrawable(getApplicationContext(),
                            Iconify.IconValue.fa_male).colorRes(R.color.color_male).sizeDp(60);
                    mIcon.setImageDrawable(maleIcon);
                } else {
                    Drawable femaleIcon = new IconDrawable(getApplicationContext(),
                            Iconify.IconValue.fa_female).colorRes(R.color.color_female).sizeDp(60);
                    mIcon.setImageDrawable(femaleIcon);

                }
                mPersonName.setText(p.getFirstName() + " " + p.getLastName());
                mEventDescription.setText(e.getEventType()
                        + ": " + e.getCity() + " " + e.getCountry() + " (" + e.getYear() + ")");

                return true;
            }
        });
    }

    public void addMarkers(){

        for (int i = 0; i < UserModel.getInstance().getsUserEvents().size(); i++){
            EventsResult.Event e = UserModel.getInstance().getsUserEvents().get(i);
            PeopleResult.Person p = UserModel.getInstance().retrievePerson(e.getPerson());

            String type = UserModel.getInstance().getsEventsResult().getSrb().getData().get(i).getEventType();
            type = type.toLowerCase();
            type = type.substring(0, 1).toUpperCase() + type.substring(1, type.length());
            type = type.concat(" Events");

            if (UserModel.getInstance().findFilterByType(type).isOn() &&
                    UserModel.getInstance().findFilterByGender(p.getGender()).isOn()) {
                LatLng latLng = new LatLng(e.getLatitude(), e.getLongitude());
                Marker marker = mMap.addMarker(new MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(UserModel.getInstance().getsEventColor().
                                        get(type))));
                MarkerToEventMap.put(marker.hashCode(), e);
            }
        }

        for (String key : UserModel.getInstance().getsSideFilter().keySet()){
            if (UserModel.getInstance().findFilterByType(key).isOn()){
                for (int i = 0; i < UserModel.getInstance().getsSideFilter().get(key).size(); i++){
                    EventsResult.Event e = UserModel.getInstance().getsSideFilter().get(key).get(i);
                    PeopleResult.Person p = UserModel.getInstance().retrievePerson(e.getPerson());

                    String type = UserModel.getInstance().getsEventsResult().getSrb().getData().get(i).getEventType();
                    type = type.toLowerCase();
                    type = type.substring(0, 1).toUpperCase() + type.substring(1, type.length());
                    type = type.concat(" Events");

                    if (UserModel.getInstance().findFilterByType(type).isOn() &&
                            UserModel.getInstance().findFilterByGender(p.getGender()).isOn()) {
                        LatLng latLng = new LatLng(e.getLatitude(), e.getLongitude());
                        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng)
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(UserModel.getInstance().getsEventColor().
                                                get(type))));
                        MarkerToEventMap.put(marker.hashCode(), e);
                    }
                }
            }
        }

    }

    public void drawSpouseLines(EventsResult.Event e, PeopleResult.Person p){
        if (UserModel.getInstance().getsSettings().isSpouseOn()){
            if(p.getSpouse() != null){
                EventsResult.Event birth = UserModel.getInstance().getBirthEvent(p.getSpouse());
                LatLng latLng1 = new LatLng(e.getLatitude(), e.getLongitude());
                LatLng latLng2 = new LatLng(birth.getLatitude(), birth.getLongitude());

                mPolylines.add(mMap.addPolyline(new PolylineOptions().
                        clickable(false).add(latLng1, latLng2).color(UserModel.getInstance().
                        getsSettings().getColorConstant(UserModel.getInstance().getsSettings().
                        getSpouseLinesColor())).width(9f)));
            }
        }
    }

    public void drawLifeStoryLines(EventsResult.Event e, PeopleResult.Person p){
        if (UserModel.getInstance().getsSettings().isLifeStoryOn()){
            List<EventsResult.Event> lifeEvents = UserModel.getInstance().getLifeEvents(p.getPersonID());
            List<LatLng> coordinates = new ArrayList<>();
            for (int i = 0; i < lifeEvents.size(); i++){
                LatLng latLng = new LatLng(lifeEvents.get(i).getLatitude(), lifeEvents.get(i).getLongitude());
                coordinates.add(latLng);
            }
            mPolylines.add(mMap.addPolyline(new PolylineOptions().
                    clickable(false).addAll(coordinates).color(UserModel.getInstance().
                    getsSettings().getColorConstant(UserModel.getInstance().getsSettings().
                    getLifeStoryLinesColor())).width(9f)));
        }
    }

    public void drawFamilyLines(EventsResult.Event e, PeopleResult.Person p){
        if (UserModel.getInstance().getsSettings().isFamilyTreeOn()){
            drawFamilyRecursive(e, p, 9f);
        }
    }
    private void drawFamilyRecursive(EventsResult.Event e, PeopleResult.Person p, float width){
        if (p.getFather() != null || p.getMother() != null){
            EventsResult.Event birth = UserModel.getInstance().getBirthEvent(p.getFather());
            LatLng latLng1 = new LatLng(e.getLatitude(), e.getLongitude());
            LatLng latLng2 = new LatLng(birth.getLatitude(), birth.getLongitude());

            mPolylines.add(mMap.addPolyline(new PolylineOptions().
                    clickable(false).add(latLng1, latLng2).color(UserModel.getInstance().
                    getsSettings().getColorConstant(UserModel.getInstance().getsSettings().
                    getFamilyTreeLinesColor())).width(width)));
            drawFamilyRecursive(birth, UserModel.getInstance().retrievePerson(p.getFather()), width - 2f);

            EventsResult.Event birth2 = UserModel.getInstance().getBirthEvent(p.getMother());
            LatLng latLng3 = new LatLng(e.getLatitude(), e.getLongitude());
            LatLng latLng4 = new LatLng(birth2.getLatitude(), birth2.getLongitude());

            mPolylines.add(mMap.addPolyline(new PolylineOptions().
                    clickable(false).add(latLng3, latLng4).color(UserModel.getInstance().
                    getsSettings().getColorConstant(UserModel.getInstance().getsSettings().
                    getFamilyTreeLinesColor())).width(width)));
            drawFamilyRecursive(birth2, UserModel.getInstance().retrievePerson(p.getMother()), width - 2f);


        } else {
            return;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.up_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_up:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
