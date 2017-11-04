package com.example.briannak.campuseventproject.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by BriannaK on 11/4/2017.
 */

public class Event implements Serializable, Parcelable {
    private String name, university, category, location, createdBy, date, details;


    public Event(String name, String university, String category, String location, String createdBy, String date, String details) {
        this.name = name;
        this.university = university;
        this.category = category;
        this.location = location;
        this.createdBy = createdBy;
        this.date = date;
        this.details = details;
    }

    protected Event(Parcel in) {
        name = in.readString();
        university = in.readString();
        category = in.readString();
        location = in.readString();
        createdBy = in.readString();
        date = in.readString();
        details = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name);
        dest.writeString(university);
        dest.writeString(category);
        dest.writeString(location);
        dest.writeString(createdBy);
        dest.writeString(date);
        dest.writeString(details);
    }
}
