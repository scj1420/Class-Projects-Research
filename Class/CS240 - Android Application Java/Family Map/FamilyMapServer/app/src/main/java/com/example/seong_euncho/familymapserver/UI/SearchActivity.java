package com.example.seong_euncho.familymapserver.UI;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.seong_euncho.familymapserver.Model.UserModel;
import com.example.seong_euncho.familymapserver.R;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import java.util.List;

import model.Item;
import result.EventsResult;
import result.PeopleResult;

/**
 * Created by Seong-EunCho on 4/14/17.
 */

public class SearchActivity extends AppCompatActivity{

    private EditText mSearchField;
    private RecyclerView mSearchRecyclerView;
    private SearchAdapter mSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setTitle("Family Map: Search");

        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateUI(s.toString());
            }
        });

        mSearchRecyclerView = (RecyclerView) findViewById(R.id.search_recycler_view);
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        updateUI("");
    }

    private void updateUI(String term) {
        if (term.length() > 0) {
            List<Item> items = UserModel.getInstance().searchAll(term);
            mSearchAdapter = new SearchAdapter(items);
            mSearchRecyclerView.setAdapter(mSearchAdapter);
        }
    }

    private class SearchHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private Item mItem;
        private ImageView mImageView;
        private TextView mDescription;
        private TextView mSubDescription;
        private RelativeLayout mLayout;


        public SearchHolder(View itemView){
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.search_image_view);
            mDescription = (TextView) itemView.findViewById(R.id.search_description);
            mSubDescription = (TextView) itemView.findViewById(R.id.search_subdescription);
            mLayout = (RelativeLayout) itemView.findViewById(R.id.search_layout);
        }

        public void BindSearch(Item item){
            mItem = item;
            mLayout.setOnClickListener(this);
            if(mItem.getClass() == PeopleResult.Person.class){
                PeopleResult.Person p = (PeopleResult.Person) mItem;
                mDescription.setText(p.getFirstName() + " " + p.getLastName());
                mDescription.setTextColor(Color.BLACK);
                mSubDescription.setText("");

                if (p.getGender().equals("m")){
                    Drawable maleIcon = new IconDrawable(getApplicationContext(),
                            Iconify.IconValue.fa_male).colorRes(R.color.color_male);
                    mImageView.setImageDrawable(maleIcon);
                } else {
                    Drawable femaleIcon = new IconDrawable(getApplicationContext(),
                            Iconify.IconValue.fa_female).colorRes(R.color.color_female);
                    mImageView.setImageDrawable(femaleIcon);
                }
            }
            else if(mItem.getClass() == EventsResult.Event.class){
                EventsResult.Event e = (EventsResult.Event)mItem;
                mDescription.setText(e.getEventType()
                        + ": " + e.getCity() + " " + e.getCountry() + " (" + e.getYear() + ")");
                mDescription.setTextColor(Color.BLACK);
                PeopleResult.Person p = UserModel.getInstance().retrievePerson(e.getPerson());
                mSubDescription.setText(p.getFirstName() + " " + p.getLastName());
                mSubDescription.setTextColor(Color.BLACK);

                Drawable markerIcon = new IconDrawable(getApplicationContext(),
                        Iconify.IconValue.fa_map_marker).colorRes(R.color.color_grey);
                mImageView.setImageDrawable(markerIcon);
            }
        }


        @Override
        public void onClick(View v) {
            if (mItem.getClass() == PeopleResult.Person.class) {
                PeopleResult.Person person = (PeopleResult.Person) mItem;
                UserModel.getInstance().setsCurrentEvent(UserModel.getInstance().
                        getBirthEvent(person.getPersonID()));
                Intent intent = new Intent(getApplicationContext(), PersonActivity.class);
                intent.putExtra("PERSON_ID", person.getPersonID());
                startActivity(intent);
            } else if (mItem.getClass() == EventsResult.Event.class) {
                EventsResult.Event event = (EventsResult.Event) mItem;
                UserModel.getInstance().setsCurrentEvent(event);
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        }
    }

    private class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {
        private List<Item> mItemList;

        public SearchAdapter(List<Item> itemList){
            mItemList = itemList;
        }

        @Override
        public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new SearchHolder(view);
        }

        @Override
        public void onBindViewHolder(SearchHolder holder, int position) {
            Item item = mItemList.get(position);
            holder.BindSearch(item);
        }

        @Override
        public int getItemCount() {
            return mItemList.size();
        }
    }
}
