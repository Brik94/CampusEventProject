package com.example.briannak.campuseventproject;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.briannak.campuseventproject.Adapters.CustomListAdapter;
import com.example.briannak.campuseventproject.Objects.Campus;
import com.example.briannak.campuseventproject.Objects.Event;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Home Page of the app.
 * Will handle button clicks to different activites and perform onCreate calculations?
 * Ex: Reading in text files, adding data to arraylists, etc.
 */

public class EventFeed extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    public static final int REQ_CODE = 100;
    public static final String REQ_EVENT = "newEvent";
    public final static String EVENT_LIST = "eventList";
    public final static String CAMPUS_LIST = "campusList";
    public final static String OBJECT = "objects";
    private FloatingActionButton createEvent;
    TextView test;

    //Might change depending on how we organize events.
    private ArrayList<Event> totalEvents;
    private ArrayList<Event> hold;
    private ArrayList<Campus> campuses;

    //Category Specific ArrayLists.
    private ArrayList<Event> academicList;
    private ArrayList<Event> athleticList;
    private ArrayList<Event> communityList;
    private ArrayList<Event> computingList;
    private ArrayList<Event> artsList;
    int x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_feed);
        totalEvents = new ArrayList<>();
        campuses = new ArrayList<>();
        hold = new ArrayList<>();
        x = 0;
        academicList = new ArrayList<>();
        athleticList = new ArrayList<>();
        communityList = new ArrayList<>();
        computingList = new ArrayList<>();
        artsList = new ArrayList<>();


        /*Event event1 = new Event();
        event1.setName("Block Party");
        event1.setDate("10/11/2017");
        totalEvents.add(event1);

        Event event2 = new Event();
        event2.setName("Barbque Lit Day");
        event2.setDate("10/15/2017");
        totalEvents.add(event2);

        Event event3 = new Event();
        event3.setName("Thanksgiving Bash");
        event3.setDate("11/24/2017");
        totalEvents.add(event3);*/



        try {
            parseCollegeFile();
            parseEventFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        sortEvents();

        //Creates a dropdown menu of Campus Suggestions based on user input.
        //Currently references Dummy String. Will be replaced by an ArrayList<Campus> objects.
        //ArrayAdapter<Campus> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, campuses);
        //campusSearch.setAdapter(adapter);



        

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        FloatingActionButton menu = (FloatingActionButton) findViewById(R.id.menuFab);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(Gravity.START);
            }
        });



        FloatingActionButton search = (FloatingActionButton) findViewById(R.id.searchFab);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMethod();
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListView listView = (ListView) findViewById(R.id.listV);
        ArrayAdapter<Event> customAdapter = new CustomListAdapter(this, 0 , totalEvents);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myIntent = new Intent(EventFeed.this, EventInfo.class);
                myIntent.putExtra(OBJECT, (Parcelable) totalEvents.get(i));
                startActivity(myIntent);
            }
        });

    }

    /**
     * This method will handle clicks to different buttons on the layout.
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent i;

        //Open Create Event Page.
        if (view.getId() == R.id.fab) {
            i = new Intent(EventFeed.this, CreateEvent.class);
            i.putExtra(CAMPUS_LIST, campuses);
            //Start Activity For Result
            startActivityForResult(i, REQ_CODE);
        }
        //Open Profile Page
        /*else if(view.getId() == R.id.buttonProfile){

        }*/
    }

    public void searchMethod() {
        FloatingActionButton search = (FloatingActionButton) findViewById(R.id.searchFab);
        EditText searchBar = (EditText) findViewById(R.id.editText);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (x == 0) {
            searchBar.setVisibility(View.VISIBLE);
            searchBar.requestFocus();
            imm.showSoftInput(searchBar, InputMethodManager.SHOW_IMPLICIT);
            search.setImageResource(R.drawable.send);
            x++;
        } else if (x == 1) {
            search.setImageResource(R.drawable.search);
            searchBar.setVisibility(View.GONE);

            try {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                // TODO: handle exception
            }
            String s = searchBar.getText().toString().toLowerCase();
            hold = new ArrayList<>();

            for (int x = 0; x < totalEvents.size(); x++) {
                try {
                    hold.add((Event) totalEvents.get(x).clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }

            if (!(s.isEmpty())) {
                for (int x = 0; x < hold.size(); x++) {
                    if (hold.get(x).getName().toLowerCase().contains(s)) {

                        Event h = null;
                        try {
                            h = (Event) hold.get(x).clone();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        if (h != null) {
                            h.setHighlight(2);
                        }
                        hold.remove(x);
                        hold.add(0, h);
                    } else {
                        hold.get(x).setHighlight(0);
                    }
                }
                ListView listView = (ListView) findViewById(R.id.listV);
                ArrayAdapter<Event> customAdapter2 = new CustomListAdapter(this, 0, hold);
                listView.setAdapter(customAdapter2);
            } else {
                ListView listView = (ListView) findViewById(R.id.listV);
                ArrayAdapter<Event> customAdapter = new CustomListAdapter(this, 0, totalEvents);
                listView.setAdapter(customAdapter);
            }

            searchBar.setText(null);
            x = 0;
        }
    }

    public void parseCollegeFile() throws IOException {
        Log.d("test", "Loading txt file data...");
        final Resources resources = this.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.colleges);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try
        {
            String line, name, location;
            while ((line = reader.readLine()) != null)
            {
                String[] strings = TextUtils.split(line, ",");
                name = strings[0];
                location = strings[1];

                campuses.add(new Campus(name,location));
            }
        }
        finally
        {
            reader.close();
        }
        Log.d("test", "DONE loading buildings.");
    }

    public void parseEventFile() throws IOException {
        Log.d("test", "Loading txt file data...");
        final Resources resources = this.getResources();
        InputStream inputStream = resources.openRawResource(R.raw.events);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try
        {
            String line, name, campus, category, date, details;
            while ((line = reader.readLine()) != null)
            {
                String[] strings = TextUtils.split(line, ",");
                name = strings[0];
                campus = strings[1];
                date  = strings[2];
                category  = strings[3];
                details =  strings[4];


                totalEvents.add(new Event(name,campus, category, date, details));
            }
        }
        finally
        {
            reader.close();
        }
        Log.d("test", "DONE loading events.");
    }

    public void parseFile(String fileName) throws IOException {
        // Scanner keyboard;
        Scanner input;
        StringTokenizer token;

        Log.d("test", fileName);
        String name ;
        String location ;
        input = new Scanner(new File(fileName));

        int counter = 0 ;
        token = new StringTokenizer(input.nextLine(),",");

        while(token.hasMoreTokens()){
            String s1 = input.next();

            counter++ ;}
        input.close() ;

        for(int n = 0 ; n< counter -1   ; n++)
        {
            token = new StringTokenizer(input.nextLine(),",");
            while(token.hasMoreTokens()){

                name = token.nextToken();
                location = token.nextToken();


                 campuses.add(new Campus(name,location));
            }
            Log.d("test", campuses.toString());
        }

        input.close();


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
            if(resultCode == RESULT_OK && data.getExtras().containsKey(REQ_EVENT)){
                Event returnedEvent = data.getExtras().getParcelable(REQ_EVENT);
                Log.d("test", returnedEvent.toString());
                totalEvents.add(returnedEvent);

                if(returnedEvent.getCategory().equals("Academic")){
                    academicList.add(returnedEvent);
                }else if(returnedEvent.getCategory().equals("Athletic")){
                    athleticList.add(returnedEvent);
                }else if(returnedEvent.getCategory().equals("Community Service")){
                    communityList.add(returnedEvent);
                }else if(returnedEvent.getCategory().equals("Technology")){
                    computingList.add(returnedEvent);
                }else if(returnedEvent.getCategory().equals("Arts")){
                    artsList.add(returnedEvent);
                }

            }else if (resultCode == RESULT_CANCELED){
                Log.d("eventListender", "Create event cancelled");
            }
        }
    }

    public void sortEvents(){
        for(int i = 0; i < totalEvents.size(); i++){
            Log.d("Tester",totalEvents.get(i).toString());
            if(totalEvents.get(i).category == null) {

            }else if(totalEvents.get(i).category.equals("Academic")){
                academicList.add(totalEvents.get(i));
            }else if(totalEvents.get(i).category.equals("Athletic")){
                athleticList.add(totalEvents.get(i));
            }else if(totalEvents.get(i).category.equals("Community Service")){
                communityList.add(totalEvents.get(i));
            }else if(totalEvents.get(i).category.equals("Technology")){
                computingList.add(totalEvents.get(i));
            }else if(totalEvents.get(i).category.equals("Arts")){
                artsList.add(totalEvents.get(i));
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent categoryIntent = new Intent(EventFeed.this, ViewCategoryResults.class);
        int id = item.getItemId();

        if (id == R.id.myAccount) {
            // Handle the camera action
        } else if (id == R.id.myCalendar) {

        } else if (id == R.id.academicCat) {
            categoryIntent.putExtra(EVENT_LIST, academicList);
            startActivity(categoryIntent);
        } else if (id == R.id.athleticCat) {
            categoryIntent.putExtra(EVENT_LIST, athleticList);
            startActivity(categoryIntent);
        } else if (id == R.id.communCat) {
            categoryIntent.putExtra(EVENT_LIST, communityList);
            startActivity(categoryIntent);
        } else if (id == R.id.techCat) {
            categoryIntent.putExtra(EVENT_LIST, computingList);
            startActivity(categoryIntent);
        } else if (id == R.id.artCat) {
            categoryIntent.putExtra(EVENT_LIST, artsList);
            startActivity(categoryIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

