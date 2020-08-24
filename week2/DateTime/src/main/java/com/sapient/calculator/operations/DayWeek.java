package com.sapient.calculator.operations;

import java.sql.Timestamp;
import java.util.Scanner;

import com.sapient.calculator.CommonMethod;
import com.sapient.calculator.models.*;
import com.sapient.calculator.persistance.Queries;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DayWeek extends CommonMethod  {
    private DateTime t1;
    private final Scanner temp;
    private Queries query;
    private AutomatedQuery aq;

    public DayWeek(final Scanner d, Queries q, AutomatedQuery aq) {
        this.temp = d;
        this.query = q;
        this.aq = aq;
    }

    public Queries init() {
        // set query start time to now
        query.setQueryStartTime(new Timestamp( new DateTime().getMillis()));
        // set type of query
        query.setType(3);

        int choice;
        if(this.aq == null)  {
            // Boiler plate message for type 1 operation
            boilerplateMessage();
            choice = inputChoice(temp);
        }else{
            choice = aq.getFormat();
        } 

        // set format
        query.setFormat(choice);

        // Take input from user for given dates.
        try {
            if(this.aq == null){
                DateInput date = new DateInput(temp, choice);
                this.t1 = date.init();
             }
             else{
                 this.t1 = aq.getDate1();
             }
            query.setDate1(new Timestamp( this.t1.getMillis()));
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

        if(this.aq == null){
            sop("Name of the day of the week of the given date is: " + executeOperation());
        }
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