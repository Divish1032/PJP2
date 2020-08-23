package com.sapient.calculator.operations;

import java.util.HashMap;
import java.util.Scanner;

import com.sapient.calculator.CommonMethod;
import com.sapient.calculator.models.DateInput;
import org.joda.time.DateTime;
import org.joda.time.Period;


public class TwoDate extends CommonMethod{
    private DateTime t1, t2;
    private Scanner temp;

    public TwoDate(Scanner d){
        this.temp = d;
    }


	public void init() {
        // Boiler plate message for type 1 operation
        boilerplateMessage();
        // input choice
        int choice = inputChoice(temp);
        // Take input from user for two dates.
        try {
            DateInput date = new DateInput(temp, choice);
            this.t1 = date.init();
            // if null returned then some exception occured
            if(this.t1 == null) System.exit(0);   
            
            DateInput date2 = new DateInput(temp, choice);
            this.t2 = date2.init();
            // if null returned then some exception occured
            if(this.t2 == null) System.exit(0);   
        } catch (Exception e) {
            sop(e.getMessage());
            System.exit(0); 
        }  

        // Choice of operation to be applied to the two dates
        sop("Select operation to be operated on the dates");
        sop("Enter 1 for addtion of dates");
        sop("Enter 2 for substraction of dates");    
        choice = inputChoice(temp);
        executeOperation(choice);
    }

    public void boilerplateMessage() {
        sop("You opted for operation of addition and substration of two dates");
        sop("Please enter two dates to perform the selected operation");
        sop("Date can be enter in two formats:");
        sop("Enter 1 for following format : DD/MM/YYYY");
        sop("Enter 2 for fully intuitive input");
    }


    public void addition() {
        DateTime res = this.t1.plusDays(this.t2.getDayOfMonth());
        res = res.plusMonths(this.t2.getMonthOfYear());
        res = res.plusYears(this.t2.getYear());
        sop("Date: " + res.toLocalDate());
        sop("Days: " + res.getDayOfYear());
        sop("Weeks: " + res.getWeekOfWeekyear());
        sop("Month: " + res.getMonthOfYear());
        return;
    }

    public void subtraction() {
        if(this.t1.getMillis()> this.t2.getMillis()){
            Period dif =  new Period(this.t1, this.t2);
            sop("Days: " + dif.getDays());
            sop("Weeks: " + dif.getWeeks());
            sop("Month: " + dif.getMonths());
            return;
        }
        sop("Invalid date substraction.");
        return;
    }

    public void executeOperation(int choice) {
        HashMap<Integer, Runnable> map = new HashMap<>();
        map.put(1, () -> {
            sop("Result after addition is: ");
            addition();
        });
        map.put(2, () -> {
            sop("Result after substraction is: ");
            subtraction();
        });

        Runnable action = map.get(choice);
        if (action != null) {
            action.run();
        } else {
            sop("Enter a valid choice of input for operation.");
            System.exit(0);
        }
    }

}