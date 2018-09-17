package com.example.seong_euncho.familymapserver.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seong_euncho.familymapserver.Model.UserModel;
import com.example.seong_euncho.familymapserver.R;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import result.EventsResult;
import result.PeopleResult;

/**
 * Created by Seong-EunCho on 4/19/17.
 */

public class PersonActivity extends AppCompatActivity {

    private static PersonActivity instance;

    private EventsResult.Event mEvent;
    private PeopleResult.Person mPerson;
    private TextView mFirstName;
    private TextView mLastName;
    private TextView mGender;
    private List<String> mTitleList;
    private List<Object> mLifeEventsList;
    private List<Object> mFamilyList;
    private Map<String, List<Object>> mListDataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        getSupportActionBar().setTitle("Family Map: Person Details");
        instance = this;

        mEvent = UserModel.getInstance().getsCurrentEvent();
        mPerson = UserModel.getInstance().retrievePerson(mEvent.getPerson());

        mFirstName = (TextView) findViewById(R.id.person_first_name);
        mFirstName.setText(mPerson.getFirstName());

        mLastName = (TextView) findViewById(R.id.person_last_name);
        mLastName.setText(mPerson.getLastName());

        mGender = (TextView) findViewById(R.id.person_gender);
        if (mPerson.getGender().equals("m")){
            mGender.setText("Male");
        } else {
            mGender.setText("Female");
        }

        mTitleList = new ArrayList<>();
        mTitleList.add("LIFE EVENTS");
        mTitleList.add("FAMILY");

        mLifeEventsList = UserModel.getInstance().getOLifeEvents(mPerson.getPersonID());
        mFamilyList = UserModel.getInstance().getOFamily(mPerson.getPersonID());

        mListDataMap = new HashMap<>();
        mListDataMap.put("LIFE EVENTS", mLifeEventsList);
        mListDataMap.put("FAMILY", mFamilyList);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.events_family_list);
        final ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(this, mTitleList, mListDataMap);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.expandGroup(0);
        expandableListView.expandGroup(1);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0) {
                    EventsResult.Event event = (EventsResult.Event)
                            expandableListAdapter.getChild(groupPosition, childPosition);
                    UserModel.getInstance().setsCurrentEvent(event);
                    Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                    startActivity(intent);
                } else {
                    PeopleResult.Person person = (PeopleResult.Person)
                            expandableListAdapter.getChild(groupPosition, childPosition);
                    UserModel.getInstance().setsCurrentEvent(UserModel.getInstance().getBirthEvent(person.getPersonID()));
                    Intent intent = new Intent(getApplicationContext(), PersonActivity.class);
                    intent.putExtra("PERSON_ID", person.getPersonID());
                    startActivity(intent);
                }
                return true;
            }

        });

    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context context;
        private List<String> titles;
        private Map<String, List<Object>> details;

        public ExpandableListAdapter(Context context, List<String> expandableListTitle, Map<String, List<Object>> expandableListDetail) {
            this.context = context;
            this.titles = expandableListTitle;
            this.details = expandableListDetail;
        }

        @Override
        public int getGroupCount() {
            return titles.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return details.get(titles.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return titles.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return details.get(titles.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String groupTitle = (String) getGroup (groupPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_group, null);
            }

            TextView listTitle = (TextView) convertView.findViewById(R.id.list_title);
            listTitle.setText(groupTitle);

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
            TextView description = (TextView) convertView.findViewById(R.id.search_description);
            TextView subDescription = (TextView) convertView.findViewById(R.id.search_subdescription);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.search_image_view);


            if (groupPosition == 0) {

                EventsResult.Event e = (EventsResult.Event) getChild (groupPosition, childPosition);
                description.setText(e.getEventType()
                        + ": " + e.getCity() + " " + e.getCountry() + " (" + e.getYear() + ")");
                subDescription.setText(mPerson.getFirstName() + " " + mPerson.getLastName());

                Drawable markerIcon = new IconDrawable(getApplicationContext(),
                        Iconify.IconValue.fa_map_marker).colorRes(R.color.color_grey);
                imageView.setImageDrawable(markerIcon);

            } else {
                PeopleResult.Person p = (PeopleResult.Person) getChild (groupPosition, childPosition);
                description.setText(p.getFirstName() + " " + p.getLastName());

                if (p.getGender().equals("m")){
                    Drawable maleIcon = new IconDrawable(getApplicationContext(),
                            Iconify.IconValue.fa_male).colorRes(R.color.color_male);
                    imageView.setImageDrawable(maleIcon);
                } else {
                    Drawable femaleIcon = new IconDrawable(getApplicationContext(),
                            Iconify.IconValue.fa_female).colorRes(R.color.color_female);
                    imageView.setImageDrawable(femaleIcon);

                }

                if (p.getPersonID().equals(mPerson.getMother())) {
                    subDescription.setText("Mother");
                } else if (p.getPersonID().equals(mPerson.getFather())) {
                    subDescription.setText("Father");
                } else if (p.getPersonID().equals(mPerson.getSpouse())) {
                    subDescription.setText("Spouse");
                } else if (mPerson.getPersonID().equals(p.getMother()) | mPerson.getPersonID().equals(p.getFather())) {
                    if (p.getGender().equals("f"))
                        subDescription.setText("Daughter");
                    else
                        subDescription.setText("Son");
                } else {

                }
            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
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

    public static PersonActivity getInstance(){
        return instance;
    }
}
