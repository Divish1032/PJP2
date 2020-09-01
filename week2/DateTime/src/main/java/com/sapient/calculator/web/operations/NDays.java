package com.sapient.calculator.web.operations;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Scanner;

import com.sapient.calculator.web.CommonMethod;
import com.sapient.calculator.web.models.AutomatedQuery;
import com.sapient.calculator.web.models.DateInput;
import com.sapient.calculator.web.persistance.Queries;

import org.joda.time.DateTime;

public class NDays extends CommonMethod{
    private DateTime t1;
    private final Scanner temp;
    private Queries query;
    private AutomatedQuery aq;

    public NDays(Scanner d, Queries q, AutomatedQuery aq) {
        this.temp = d;
        this.query = q;
        this.aq = aq;
    }

    public Queries init() {
        // set query start time to now
        query.setQueryStartTime(new Timestamp( new DateTime().getMillis()));
        // set type of query
        query.setType(2);
        
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
        } catch (final Exception e) {
            sop(e.getMessage());
            query.setError(true);
            return query;
        }

        // input for add/minus of n days from specified date
        int value;
        if(this.aq == null){
            sop("Enter value of n");
            value = temp.nextInt();
            query.setN(value);
            sop("Select operation to be operated on the dates");
            sop("Enter 1 for addtion of value of n from given date.");
            sop("Enter 2 for substraction of value of n from given date.");

            // Choice of operation to be applied to the two dates
            choice = inputChoice(temp);
         }
         else{
             value = aq.getN();
             query.setN(value);
             choice = aq.getAddition() ? 1 : 2;
        }

        // Execture the type of operation to be done on the date
        executeOperation(choice, value);

        return query;
    }


    // adding n days/weeks/months to given date
    public void addition(int n) {
        final DateTime day = this.t1.plusDays(n);
        final DateTime week = this.t1.plusWeeks(n);
        final DateTime month = this.t1.plusMonths(n);

        if(aq == null){
            sop("Result after addition is: ");
            sop("Date afer adding "+ n +" days: " + day.toLocalDate());
            sop("Date afer adding "+ n +" weeks: " + week.toLocalDate());
            sop("Date afer adding "+ n +" months: " + month.toLocalDate());
        }
        String result = "Date afer adding "+ n +" days: " + day.toLocalDate() + "\n" +
         "Date afer adding "+ n +" weeks: " + week.toLocalDate() + "\n" +
         "Date afer adding "+ n +" months: " + month.toLocalDate();
        query.setResult(result);
        return;
    }

    // subtracting n days/weeks/months to given date
    public void subtraction(final int n) {
        final DateTime day = this.t1.minusDays(n);
        final DateTime week = this.t1.minusWeeks(n);
        final DateTime month = this.t1.minusMonths(n);

        if(aq ==null) {
            sop("Result after substraction is: ");
            sop("Date afer subtracting n days: " + day.toLocalDate());
            sop("Date afer subtracting n weeks: " + week.toLocalDate());
            sop("Date afer subtracting n months: " + month.toLocalDate());
        }

        String result = "Date afer subtracting "+ n +" days: " + day.toLocalDate() + "\n" +
         "Date afer subtracting "+ n +" weeks: " + week.toLocalDate() + "\n" +
         "Date afer subtracting "+ n +" months: " + month.toLocalDate();
        query.setResult(result);
        return;
    }

    // 
    public void executeOperation(final int choice, final int n) {
        HashMap<Integer, Runnable> map = new HashMap<>();
        map.put(1, () -> {
            query.setAddition(true);
            addition(n);
        });
        map.put(2, () -> {
            query.setAddition(false);
            subtraction(n);
        });

        Runnable action = map.get(choice);
        if (action != null) {
            action.run();
        } else {
            sop("Enter a valid choice of input for operation.");
            System.exit(0);
        }
    }

    public void boilerplateMessage() {
        sop("You opted for operation of addition and substration of n days/weeks/months to a given date.");
        sop("Please enter given date to perform the selected operation");
        sop("Date can be enter in two formats:");
        sop("Enter 1 for following format : DD/MM/YYYY");
        sop("Enter 2 for fully intuitive input");
    }
}