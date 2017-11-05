package com.example.briannak.campuseventproject;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.briannak.campuseventproject.Objects.Campus;
import com.example.briannak.campuseventproject.Objects.Event;

import java.util.ArrayList;

/**
 * Handles input submitted into the create_event layout.
 * Takes data and creates a new Event object.
 * Sends this object back to the EventFeed, where it is stored in an ArrayList.
 */
public class CreateEvent extends AppCompatActivity  implements View.OnClickListener{
    EditText eventNameEdit, categoryEdit, dateEdit, detailsEdit;
    AutoCompleteTextView campusEdit;
    String name, university, category, date, details;
    Button submit;
    Spinner spinnerCategory;
    ArrayList<Campus> campuses;

    //Dummy String array. Will be replaced by an ArrayList<Campus> objects.
    private static final String[] COLLEGES = new String[] {
            "UNCH", "UNCC", "DUKE", "DUUKE", "Spain"};

    private static final String[] CATEGORIES = new String[] {
            "Academic", "Athletic", "Community Service", "Computing", "Arts", "Social", "Religious", "Greek"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        //Initialize EditText which is used to Input userdata.
        eventNameEdit = (EditText) findViewById(R.id.eventNameEdit);
        campusEdit = (AutoCompleteTextView) findViewById(R.id.campusEdit);
        dateEdit = (EditText) findViewById(R.id.dateEdit);
        detailsEdit = (EditText) findViewById(R.id.detailsEdit);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        campuses = (ArrayList<Campus>) getIntent().getSerializableExtra(EventFeed.CAMPUS_LIST);




        //Creates a dropdown menu of Campus Suggestions based on user input.
        //Currently references Dummy String. Will be replaced by an ArrayList<Campus> objects.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COLLEGES);
        campusEdit.setAdapter(adapter);


        //Spinner or 'Category DropDown List' Logic
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);





        //Creates Button Object, then sets a Listener for when the user clicks the button.
        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(this);
    }



    /**
     * This method is called when a user clicks the submit button.
     */
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.submitButton){
            Intent i = new Intent();

            //Get Text that was submitted into each section.
            name = eventNameEdit.getText().toString();
            university = campusEdit.getText().toString();
            category = spinnerCategory.getSelectedItem().toString();
            date = dateEdit.getText().toString();
            details = detailsEdit.getText().toString();

            if (validateDate()) { //If Necessary fields are filled..
                //Create Event Object based on user data.
                Event newEvent = new Event(name, university, category, date, details);
                Log.d("testEvent", newEvent.toString());
                //Returns this event object to the EventFeed.
                i.putExtra(EventFeed.REQ_EVENT, (Parcelable) newEvent);
                setResult(RESULT_OK, i);
                finish();
            }
        }
    }

    //This method is used to validate user data.
    //Ex: Make sure an input isn't null or makes sure the user ACTUALLY typed in data.
    public boolean validateDate(){

        //Submits an error message if a field is empty
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(university) || TextUtils.isEmpty(category) || TextUtils.isEmpty(date)){
            CharSequence text = "Please make sure all input is filled in.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(CreateEvent.this, text, duration);
            toast.show();
            return false;
        }
        return true;
    }
}
