package com.sapient.calculator.operations;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Scanner;

import com.sapient.calculator.CommonMethod;
import com.sapient.calculator.models.AutomatedQuery;
import com.sapient.calculator.models.DateInput;
import com.sapient.calculator.persistance.Queries;

import org.joda.time.DateTime;
import org.joda.time.Period;


public class TwoDate extends CommonMethod{
    private DateTime t1, t2;
    private Scanner temp;
    private Queries query;
    private AutomatedQuery aq;

    public TwoDate(Scanner d, Queries q, AutomatedQuery aq){
        this.temp = d;
        this.query = q;
        this.aq = aq;
    }


	public Queries init() {
        // set query start time to now
        query.setQueryStartTime(new Timestamp( new DateTime().getMillis()));
        // set type of query
        query.setType(1);

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

        // Take input from user for two dates.
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
            
            if(this.aq == null){
                DateInput date = new DateInput(temp, choice);
                this.t2 = date.init();
             }
             else{
                 this.t2 = aq.getDate2();
            }
            query.setDate2(new Timestamp(this.t2.getMillis()));
            
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

        if(aq == null){
            // Choice of operation to be applied to the two dates
            sop("Select operation to be operated on the dates");
            sop("Enter 1 for addtion of dates");
            sop("Enter 2 for substraction of dates");    
            choice = inputChoice(temp);
        }
        else{
            choice = aq.getAddition() ? 1 : 2;
        }

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

        if(aq == null){
            sop("Result after addition is: ");
            sop("Date: " + res.toLocalDate());
            sop("Days: " + res.getDayOfYear());
            sop("Weeks: " + res.getWeekOfWeekyear());
            sop("Month: " + res.getMonthOfYear());
    
        }
    
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

            if(aq == null){
                sop("Result after substraction is: ");
                sop("Days: " + dif.getDays());
                sop("Weeks: " + dif.getWeeks());
                sop("Month: " + dif.getMonths());
            }
            

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
            addition();
        });
        map.put(2, () -> {
            query.setAddition(false);
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