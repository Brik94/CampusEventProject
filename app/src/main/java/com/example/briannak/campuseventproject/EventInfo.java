package com.example.briannak.campuseventproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.briannak.campuseventproject.Objects.Event;

import java.util.ArrayList;

/**
 * Created by BriannaK on 11/5/2017.
 */

public class EventInfo extends AppCompatActivity {
    Event events;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        events = (Event) getIntent().getSerializableExtra(EventFeed.OBJECT);

        TextView name = (TextView)findViewById(R.id.eventNameInfo);
        TextView uni = (TextView)findViewById(R.id.univInfo);
        TextView categ = (TextView)findViewById(R.id.categInfo);
        TextView date = (TextView)findViewById(R.id.dateInfo);
        TextView details = (TextView)findViewById(R.id.detailInfo);

        Log.d("demememe", events.getName());
        if(events.getName() != null)
            name.setText(events.getName());
        else
            name.setText("No data available");
        if(events.getUniversity() != null)
            uni.setText(events.getUniversity());
        else
            uni.setText("No data available");
        if(events.getCategory()!= null)
            categ.setText(events.getCategory());
        else
            categ.setText("No data available");
        if(events.getDate()!= null)
            date.setText(events.getDate());
        else
            date.setText("No data available");
        if(events.getDetails() != null)
            details.setText(events.getDetails());
        else
            details.setText("No data available");



    }
}
