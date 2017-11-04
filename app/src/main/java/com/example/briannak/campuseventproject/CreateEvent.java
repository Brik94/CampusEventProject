package com.example.briannak.campuseventproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.briannak.campuseventproject.Objects.Event;

public class CreateEvent extends AppCompatActivity  implements View.OnClickListener{
    EditText eventNameEdit, universityEdit, categoryEdit, locationEdit, createdByEdit, dateEdit, detailsEdit;
    String name, university, category, location, createdBy, date, details;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        //Initialize EditText which is used to Input userdata.
        eventNameEdit = (EditText) findViewById(R.id.eventNameEdit);
        universityEdit = (EditText) findViewById(R.id.universityEdit);
        categoryEdit = (EditText) findViewById(R.id.categoryEdit);
        locationEdit = (EditText) findViewById(R.id.locationEdit);
        createdByEdit = (EditText) findViewById(R.id.createdEdit);
        dateEdit = (EditText) findViewById(R.id.dateEdit);
        detailsEdit = (EditText) findViewById(R.id.detailsEdit);


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
            university = universityEdit.getText().toString();
            category = categoryEdit.getText().toString();
            location = locationEdit.getText().toString();
            createdBy = createdByEdit.getText().toString();
            date = dateEdit.getText().toString();
            details = detailsEdit.getText().toString();

            //Create Event Object based on user data.
            Event newEvent = new Event(name, university, category, location, createdBy, date, details);




        }
    }
}
