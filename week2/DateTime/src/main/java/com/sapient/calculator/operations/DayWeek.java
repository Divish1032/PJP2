package com.sapient.calculator.operations;

import java.util.Scanner;

import com.sapient.calculator.CommonMethod;
import com.sapient.calculator.models.*;
import com.sapient.calculator.persistance.Query;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DayWeek extends CommonMethod  {
    private DateTime t1;
    private final Scanner temp;
    private Query query;

    public DayWeek(final Scanner d, Query q) {
        this.temp = d;
        this.query = q;
    }

    public Query init() {
        // set query start time to now
        query.setQueryStartTime(new DateTime());
        // set type of query
        query.setType(3);

        // Boiler plate message for type 1 operation
        boilerplateMessage();
        // input choice
        int choice = inputChoice(temp);

        // set format
        query.setFormat(choice);

        // Take input from user for given dates.
        try {
            DateInput date = new DateInput(temp, choice);
            this.t1 = date.init();
            query.setDate1(this.t1);
            // if null returned then some exception occured
            if(this.t1 == null){ 
                query.setError(true);
                return query;
            } 
        } catch (Exception e) {
            sop(e.getMessage());
            query.setError(true);
            return query; 
        }  

        sop("Name of the day of the week of the given date is: " + executeOperation());

        query.setResult("Name of the day of the week of the given date is: " + executeOperation());
        return query;
    }

    public String executeOperation() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("EEEE");
        return fmt.print(this.t1);
    }

    public void boilerplateMessage() {
        sop("You opted for determing the day of week for the given date");
        sop("Please enter the date");
        sop("Date can be enter in two formats:");
        sop("Enter 1 for following format : DD/MM/YYYY");
        sop("Enter 2 for fully intuitive input");
    }
}