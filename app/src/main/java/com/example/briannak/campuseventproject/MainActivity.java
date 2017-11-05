package com.example.briannak.campuseventproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
int z;
    String selectedCampus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<String> s = new ArrayList<>();
        s.add("UNCC");
        s.add("Duke");
        s.add("Chapell Hill");
        s.add(0,"Select a Campus");
        z=0;
        Spinner spinner = (Spinner) findViewById(R.id.appCompatSpinner);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.spinner_item, s);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapt);
        spinner.setSelection(0);
        //TextView text = (TextView)findViewById(R.id.select);
        //text.setText("Select a Campus");


        selectedCampus = null;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TextView text = (TextView)findViewById(R.id.select);
                text.setText(s.get(i));
                selectedCampus = s.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selectedCampus.equals("Select a Campus")){
                    Intent myIntent = new Intent(MainActivity.this, EventFeed.class);
                    myIntent.putExtra("CAMPUS",selectedCampus);
                    startActivity(myIntent);
                }else {
                    Toast.makeText(MainActivity.this, "Select a campus.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        TextView login = (TextView) findViewById(R.id.loginTV);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(myIntent);
            }
        });

        TextView signup = (TextView) findViewById(R.id.signupTV);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SignupPage.class);
                startActivity(myIntent);
            }
        });



    }


}