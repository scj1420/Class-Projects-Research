package com.example.seong_euncho.familymapserver.UI;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.seong_euncho.familymapserver.Model.Filter;
import com.example.seong_euncho.familymapserver.Model.UserModel;
import com.example.seong_euncho.familymapserver.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seong-EunCho on 4/17/17.
 */

public class FilterFragment extends Fragment {

    private RecyclerView mFilterRecyclerView;
    private FilterAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        mFilterRecyclerView = (RecyclerView) view.findViewById(R.id.filter_recycler_view);
        mFilterRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        mAdapter = new FilterAdapter(UserModel.getInstance().getsFilters());
        mFilterRecyclerView.setAdapter(mAdapter);
    }

    private class FilterHolder extends RecyclerView.ViewHolder {

        private Filter mFilter;
        private TextView mFilterType;
        private TextView mFilterDescription;
        private Switch mFilterSwitch;

        public FilterHolder(View itemView) {
            super(itemView);

            mFilterType = (TextView) itemView.findViewById(R.id.list_item_filter_type);
            mFilterDescription = (TextView) itemView.findViewById(R.id.list_item_filter_description);
            mFilterSwitch = (Switch) itemView.findViewById(R.id.list_item_filter_switch);
        }

        public void BindFilter(Filter filter){
            mFilter = filter;
            mFilterType.setText(mFilter.getType());
            mFilterDescription.setText(mFilter.getDescription());
            mFilterSwitch.setChecked(mFilter.isOn());
            mFilterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mFilter.setOn(isChecked);
                }
            });
        }

    }

    private class FilterAdapter extends RecyclerView.Adapter<FilterHolder> {
        private List<Filter> mFilters;

        public FilterAdapter(List<Filter> filters) {
            mFilters = filters;
        }

        @Override
        public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_filter, parent, false);
            return new FilterHolder(view);
        }

        @Override
        public void onBindViewHolder(FilterHolder holder, int position){
            Filter filter = mFilters.get(position);
            holder.BindFilter(filter);
        }

        @Override
        public int getItemCount(){
            return mFilters.size();
        }
    }

}
