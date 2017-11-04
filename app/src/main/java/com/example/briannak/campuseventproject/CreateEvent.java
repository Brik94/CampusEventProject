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

        //Initialize EditText
        eventNameEdit = (EditText) findViewById(R.id.eventNameEdit);
        universityEdit = (EditText) findViewById(R.id.universityEdit);
        categoryEdit = (EditText) findViewById(R.id.categoryEdit);
        locationEdit = (EditText) findViewById(R.id.locationEdit);
        createdByEdit = (EditText) findViewById(R.id.createdEdit);
        dateEdit = (EditText) findViewById(R.id.dateEdit);
        detailsEdit = (EditText) findViewById(R.id.detailsEdit);




        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.submitButton){
            //Get Text
            name = eventNameEdit.getText().toString();
            university = universityEdit.getText().toString();
            category = categoryEdit.getText().toString();
            location = locationEdit.getText().toString();
            createdBy = createdByEdit.getText().toString();
            date = dateEdit.getText().toString();
            details = detailsEdit.getText().toString();



            Intent i = new Intent();
            
            Event newEvent = new Event(name, university, category, location, createdBy, date, details);
        }
    }
}
