package com.sapient.calculator.operations;

import com.sapient.calculator.CommonMethod;
import java.util.Scanner;

import com.sapient.calculator.models.DateInput;

import org.joda.time.DateTime;

public class WeekNumber extends CommonMethod {
    private DateTime t1;
    private final Scanner temp;

    public WeekNumber(final Scanner d) {
        this.temp = d;
    }

    public void init() {
        // Boiler plate message for type 1 operation
        boilerplateMessage();
        // input choice
        int choice = inputChoice(temp);
        // Take input from user for a specific dates.

        try {
             // input specified date
             DateInput date = new DateInput(temp, choice);
             this.t1 = date.init();
 
             // if null returned then some exception occured
             if(this.t1 == null) System.exit(0);
        } catch (Exception e) {
            sop(e.getMessage());
        }  

        sop("Number of the week of the given date is: " + executeOperation());
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