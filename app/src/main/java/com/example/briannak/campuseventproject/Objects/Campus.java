package com.example.briannak.campuseventproject.Objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Defines a Campus Object.
 * May need to be refined.
 */

public class Campus implements Serializable{
    public String name, location;
    public ArrayList<Event> events;

    public Campus(String name, String location){
        this.name = name;
        this.location = location;
    }

    @Override
    public String toString() {
        /*return "Campus{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", events=" + events +
                '}';*/

        return name;
    }
}
