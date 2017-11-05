package com.example.briannak.campuseventproject;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.briannak.campuseventproject.Objects.Event;

import java.util.ArrayList;

public class ViewCategoryResults extends AppCompatActivity {

    private ArrayList<Event> events;
    private ArrayAdapter<Event> adapter;

    public final static String CONTACT_LIST= "EVENT_LIST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category_results);

        events = (ArrayList<Event>) getIntent().getSerializableExtra(EventFeed.EVENT_LIST);
        if(events == null){
            Log.e("test", "Events list is null!");
        }

        TextView categoryTitle = (TextView) findViewById(R.id.categoryTV);
        categoryTitle.setText(events.get(0).getCategory() + " Events");

        adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.categoryResults);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Event> {
        public MyListAdapter(){
            super(ViewCategoryResults.this, R.layout.category_item, events);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            //Make sure we have a view to work with.
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.category_item, parent, false);
            }

            //Find the contact to work with.
            Event currentEvent = events.get(position);

            //fill the view.
            //ImageView imageView = (ImageView) itemView.findViewById(R.id.iconImage);
            //imageView.setImageResource(currentEvent.getAvatarID());

            TextView eventName = (TextView) itemView.findViewById(R.id.eventNameTV);
            eventName.setText(currentEvent.getName());

            TextView campusName = (TextView) itemView.findViewById(R.id.campusTV);
            campusName.setText(currentEvent.getUniversity());

            return itemView;
        }
    }
}
