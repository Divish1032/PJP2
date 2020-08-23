package com.sapient.calculator.operations;

import java.util.HashMap;
import java.util.Scanner;

import com.sapient.calculator.CommonMethod;
import com.sapient.calculator.models.DateInput;
import com.sapient.calculator.persistance.Query;

import org.joda.time.DateTime;

public class NDays extends CommonMethod{
    private DateTime t1;
    private final Scanner temp;
    private Query query;

    public NDays(Scanner d, Query q) {
        this.temp = d;
        this.query = q;
    }

    public Query init() {
        // set query start time to now
        query.setQueryStartTime(new DateTime());
        // set type of query
        query.setType(2);
        
        // Boiler plate message for type 1 operation
        boilerplateMessage();

        // input choice for type of format of the date
        int choice = inputChoice(temp);

        // set format
        query.setFormat(choice);
        try {
            // input specified date
            DateInput date = new DateInput(temp, choice);
            this.t1 = date.init();
            query.setDate1(this.t1);

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
        sop("Enter value of n");
        final int value = temp.nextInt();
        query.setN(value);

        sop("Select operation to be operated on the dates");
        sop("Enter 1 for addtion of value of n from given date.");
        sop("Enter 2 for substraction of value of n from given date.");

        // Choice of operation to be applied to the two dates
        choice = inputChoice(temp);

        // Execture the type of operation to be done on the date
        executeOperation(choice, value);

        return query;
    }


    // adding n days/weeks/months to given date
    public void addition(int n) {
        final DateTime day = this.t1.plusDays(n);
        final DateTime week = this.t1.plusWeeks(n);
        final DateTime month = this.t1.plusMonths(n);
        sop("Date afer adding "+ n +" days: " + day.toLocalDate());
        sop("Date afer adding "+ n +" weeks: " + week.toLocalDate());
        sop("Date afer adding "+ n +" months: " + month.toLocalDate());
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
        sop("Date afer subtracting n days: " + day.toLocalDate());
        sop("Date afer subtracting n weeks: " + week.toLocalDate());
        sop("Date afer subtracting n months: " + month.toLocalDate());

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
            sop("Result after addition is: ");
            addition(n);
        });
        map.put(2, () -> {
            query.setAddition(false);
            sop("Result after substraction is: ");
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