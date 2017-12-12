package com.example.seong_euncho.familymapserver.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.seong_euncho.familymapserver.Model.UserModel;
import com.example.seong_euncho.familymapserver.R;

import java.util.ArrayList;
import java.util.List;

import model.Event;
import result.EventsResult;

/**
 * Created by Seong-EunCho on 4/14/17.
 */

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Family Map: Filter");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new FilterFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

//    private RecyclerView mFilterRecyclerView;
//    private FilterAdapter mAdapter;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_filter);
//
//        mFilterRecyclerView = (RecyclerView) findViewById(R.id.filter_recycler_view);
//        mFilterRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//        updateUI();
//
//    }
//
//    private void updateUI(){
//        List<String> events = new ArrayList<>();
//        events.add("Birth");
//        events.add("Baptism");
//        events.add("Death");
//        mAdapter = new FilterAdapter(events);
//        mFilterRecyclerView.setAdapter(mAdapter);
//    }
//
//    private class FilterHolder extends RecyclerView.ViewHolder {
//
//        public TextView mEventTitle;
//
//        public FilterHolder(View itemView) {
//            super(itemView);
//
//            mEventTitle = (TextView) itemView;
//        }
//    }
//
//    private class FilterAdapter extends RecyclerView.Adapter<FilterHolder> {
//        private List<String> mEvents;
//
//        public FilterAdapter(List<String> events) {
//            mEvents = events;
//        }
//
//        @Override
//        public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType){
//            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
//            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//            return new FilterHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(FilterHolder holder, int position){
//            String event = mEvents.get(position);
//            holder.mEventTitle.setText(event);
//        }
//
//        @Override
//        public int getItemCount(){
//            return mEvents.size();
//        }
//    }


}
