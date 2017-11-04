package com.example.briannak.campuseventproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.briannak.campuseventproject.Objects.Event;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQ_CODE = 100;
    public static final String RETURNED_EVENT = "newEvent";
    private FloatingActionButton createEvent;

    //Might change depending on how we organize events.
    private ArrayList<Event> totalEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalEvents = new ArrayList<>();

        createEvent = (FloatingActionButton) findViewById(R.id.buttonCreateEvent);
        createEvent.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        //Open Create Event Page.
        if (view.getId() == R.id.buttonCreateEvent) {
            i = new Intent(MainActivity.this, CreateEvent.class);

            //Start Activity For Result
            startActivityForResult(i, REQ_CODE);
        }
        //Open Profile Page
        /*else if(view.getId() == R.id.buttonProfile){

        }

        //Open Menu
        else if(view.getId() == R.id.buttonMenu){

        }

        open Search View.
        else if(view.getId() == R.id.buttonSearch){

        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_CODE){
            if(resultCode == RESULT_OK && data.getExtras().containsKey(RETURNED_EVENT)){
                Event returnedEvent = data.getExtras().getParcelable(RETURNED_EVENT);
                totalEvents.add(returnedEvent);
            }else if (resultCode == RESULT_CANCELED){
                Log.d("eventListender", "Create event cancelled");
            }
        }
    }
}
