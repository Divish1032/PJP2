package com.sapient.calculator.web.operations;

import com.sapient.calculator.web.CommonMethod;

import java.sql.Timestamp;
import java.util.Scanner;

import com.sapient.calculator.web.models.AutomatedQuery;
import com.sapient.calculator.web.models.DateInput;
import com.sapient.calculator.web.persistance.Queries;

import org.joda.time.DateTime;

public class WeekNumber extends CommonMethod {
    private DateTime t1;
    private final Scanner temp;
    private Queries query;
    private AutomatedQuery aq;

    public WeekNumber(Scanner d, Queries q, AutomatedQuery aq) {
        this.temp = d;
        this.query = q;
        this.aq = aq;
    }

    public Queries init() {
        // set query start time to now
        query.setQueryStartTime(new Timestamp( new DateTime().getMillis()));
        // set type of query
        query.setType(4);
        
        
        // input choice
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

        // Take input from user for a specific dates.
        try {
             // input specified date

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
            sop("Number of the week of the given date is: " + executeOperation());
        }
        query.setResult("Number of the week of the given date is: " + executeOperation());
        return query;
    }

    public int executeOperation() {
        return this.t1.getWeekOfWeekyear();
    }

    public void boilerplateMessage() {
        sop("You opted for determing the day of week for the given date");
        sop("Please enter the date");
        sop("Date can be enter in two formats:");
        sop("Enter 1 for following format : DD/MM/YYYY");
        sop("Enter 2 for fully intuitive input");
    }
}