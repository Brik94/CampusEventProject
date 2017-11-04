package com.example.briannak.campuseventproject.Objects;

import java.util.ArrayList;

/**
 * Defines a Campus Object.
 * May need to be refined.
 */

public class Campus {
    public String name, location;
    public ArrayList<Event> events;

    public Campus(String name, String location){
        this.name = name;
        this.location = location;
    }
}
