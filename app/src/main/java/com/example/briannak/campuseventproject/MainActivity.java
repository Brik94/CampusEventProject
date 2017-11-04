package com.example.briannak.campuseventproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.briannak.campuseventproject.Objects.Campus;
import com.example.briannak.campuseventproject.Objects.Event;

import java.util.ArrayList;

/**
 * Home Page of the app.
 * Will handle button clicks to different activites and perform onCreate calculations?
 * Ex: Reading in text files, adding data to arraylists, etc.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQ_CODE = 100;
    public static final String RETURNED_EVENT = "newEvent";
    private FloatingActionButton createEvent;
    TextView test;

    //Might change depending on how we organize events.
    private ArrayList<Event> totalEvents;
    private ArrayList<Campus> colleges;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalEvents = new ArrayList<>();

        createEvent = (FloatingActionButton) findViewById(R.id.buttonCreateEvent);
        createEvent.setOnClickListener(this);

        test = (TextView) findViewById(R.id.test);
        test.setText(totalEvents.toString());

    }

    /**
     * This method will handle clicks to different buttons on the layout.
     * @param view
     */
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

    /**
     * This method will read back data from other Activities and handle that data.
     * Ex: Reads back created Event from CreateEvent.class, and adds that event to the ArrayList<Event>.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_CODE){
            if(resultCode == RESULT_OK && data.getExtras().containsKey(RETURNED_EVENT)){
                Event returnedEvent = data.getExtras().getParcelable(RETURNED_EVENT);
                Log.d("test", returnedEvent.toString());
                totalEvents.add(returnedEvent);
                test.setText(totalEvents.toString());
            }else if (resultCode == RESULT_CANCELED){
                Log.d("eventListender", "Create event cancelled");
            }
        }
    }

    /**
     * Might be used to read a college text file.
     */
    public void readCollegeTextFile(){

    }
}
