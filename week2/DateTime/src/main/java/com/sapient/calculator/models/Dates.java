package com.sapient.calculator.models;

import org.joda.time.DateTime;

public class Dates {
    private int day;
    private int month;
    private int year;

    public Dates(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public DateTime toDate(){
        DateTime dt = new DateTime(year, month, day, 0, 0);
        return dt;
    }

    @Override
    public String toString() {
        return "Dates [day=" + day + ", month=" + month + ", year=" + year + "]";
    }

}