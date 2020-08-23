package com.sapient.calculator.operations;

import java.util.HashMap;
import java.util.Scanner;

import com.sapient.calculator.CommonMethod;
import com.sapient.calculator.models.DateInput;
import com.sapient.calculator.persistance.Query;

import org.joda.time.DateTime;
import org.joda.time.Period;


public class TwoDate extends CommonMethod{
    private DateTime t1, t2;
    private Scanner temp;
    private Query query;

    public TwoDate(Scanner d, Query q){
        this.temp = d;
        this.query = q;
    }


	public Query init() {
        // set query start time to now
        query.setQueryStartTime(new DateTime());
        // set type of query
        query.setType(1);

        // Boiler plate message for type 1 operation
        boilerplateMessage();
        // input choice
        int choice = inputChoice(temp);

        // set format
        query.setFormat(choice);

        // Take input from user for two dates.
        try {
            DateInput date = new DateInput(temp, choice);
            this.t1 = date.init();
            query.setDate1(this.t1);
            // if null returned then some exception occured
            if(this.t1 == null){
                query.setError(true);
                return query;
            }
            
            DateInput date2 = new DateInput(temp, choice);
            this.t2 = date2.init();
            query.setDate2(this.t2);
            
            // if null returned then some exception occured
            if(this.t2 == null){
                query.setError(true);
                return query;
            } 
        } catch (Exception e) {
            sop(e.getMessage());
            query.setError(true);
            return query;  
        }  

        // Choice of operation to be applied to the two dates
        sop("Select operation to be operated on the dates");
        sop("Enter 1 for addtion of dates");
        sop("Enter 2 for substraction of dates");    
        choice = inputChoice(temp);
        executeOperation(choice);

        return query;
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

        String result = "Date: " + res.toLocalDate() + "\n" +
        "Days: " + res.getDayOfYear() + "\n" +
        "Weeks: " + res.getWeekOfWeekyear() + "\n" + 
        "Month: " + res.getMonthOfYear();
        query.setResult(result);
        return;
    }

    public void subtraction() {
        if(this.t1.getMillis()> this.t2.getMillis()){
            Period dif =  new Period(this.t1, this.t2);
            sop("Days: " + dif.getDays());
            sop("Weeks: " + dif.getWeeks());
            sop("Month: " + dif.getMonths());

            String result =
            "Days: " + dif.getDays() + "\n" +
            "Weeks: " + dif.getWeeks() + "\n" + 
            "Month: " + dif.getMonths();
            query.setResult(result);

            return;
        }
        sop("Invalid date substraction.");
        query.setResult("Invalid date substraction.");

        return;
    }

    public void executeOperation(int choice) {
        HashMap<Integer, Runnable> map = new HashMap<>();
        map.put(1, () -> {
            query.setAddition(true);
            sop("Result after addition is: ");
            addition();
        });
        map.put(2, () -> {
            query.setAddition(false);
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