package com.example.briannak.campuseventproject.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Defines and Event object.
 * Go Back and add in a Picture
 */

public class Event implements Serializable, Parcelable, Cloneable {
    public String name, university, category, date, details;
    int highlight;

    public Event() {
        name = null;
        university = null;
        category = null;
        date = null;
        details = null;
        highlight = 0;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public Event(String name, String university, String category, String date, String details) {
        this.name = name;
        this.university = university;
        this.category = category;
        this.date = date;
        this.details = details;
        highlight = 0;
    }

    protected Event(Parcel in) {
        name = in.readString();
        university = in.readString();
        category = in.readString();
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

    public int getHighlight() {
        return highlight;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name);
        dest.writeString(university);
        dest.writeString(category);
        dest.writeString(date);
        dest.writeString(details);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", university='" + university + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getUniversity() {
        return university;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
