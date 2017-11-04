package com.example.briannak.campuseventproject.Objects;

import java.util.ArrayList;

/**
 * Created by BriannaK on 11/4/2017.
 */

public class University {
    public String name, location;
    public ArrayList<Event> events;

    public University(String name, String location){
        this.name = name;
        this.location = location;
    }
}
