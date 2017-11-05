package com.example.briannak.campuseventproject.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.briannak.campuseventproject.Objects.Event;
import com.example.briannak.campuseventproject.R;

import java.util.ArrayList;
import java.util.EventObject;

/**
 * Created by BriannaK on 11/4/2017.
 */

public class CustomListAdapter extends ArrayAdapter{
    Context con;
    ArrayList<Event> arrHold;
    public CustomListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Event> objects) {
        super(context, resource, objects);
        con = context;
        arrHold = objects;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_layout, parent, false);
        }

        Event hold = arrHold.get(position);
        Log.d("Output", arrHold.get(position).getName());
        TextView name= view.findViewById(R.id.name);
        TextView date= view.findViewById(R.id.date);

        if(arrHold.get(position).getHighlight() != 0){
            RelativeLayout hl = view.findViewById(R.id.highlight);
            hl.setBackgroundColor(view.getResources().getColor(R.color.colorAccent));
        }else {
            RelativeLayout hl = view.findViewById(R.id.highlight);
            hl.setBackgroundColor(view.getResources().getColor(R.color.white));
        }

        name.setText(hold.getName());
        date.setText(hold.getDate());





        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if (position == 0) {
            TextView tv = new TextView(getContext());
            tv.setVisibility(View.GONE);
            v = tv;
        } else {
            v = super.getDropDownView(position, null, parent);
        }
        return v;
    }

}