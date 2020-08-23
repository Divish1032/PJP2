package com.sapient.calculator;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.sapient.calculator.operations.DayWeek;
import com.sapient.calculator.operations.NDays;
import com.sapient.calculator.operations.NLP;
import com.sapient.calculator.operations.TwoDate;
import com.sapient.calculator.operations.WeekNumber;
import com.sapient.calculator.persistance.Query;
import com.sapient.calculator.persistance.Session;

import org.joda.time.DateTime;


public class DateTimeCalculator extends CommonMethod {

    Session ses;
    Scanner d;

    public DateTimeCalculator(){
        this.ses = new Session();
        this.d = new Scanner(System.in);
    }

    public void start(){

        // Session started 
        this.ses.setStartTime(new DateTime());
        this.ses.setStart(true);
        while (this.ses.getStart() == true) {
            init();
        }
        this.ses.setEndTime(new DateTime());
        this.d.close();
    }

    public void init(){
        // Display introductory messages
        introDisplay();
        
        String choice;
        
        // Get input from user for his choice of the operation
        choice = this.d.next();
        // clearing the console for better visualization
        //clearConsole();
        if(choice.equals("quit")){
            sop("Session ended");
            this.ses.setStart(false);
            return;
        }
    
        try {
            int ch = Integer.parseInt(choice);
            checkOperation(ch, d);
            sop("--------------------------------------------------------");
            sop("Restarting....");
        } catch (NumberFormatException e) {
            sop("Enter a valid input!!!");
            sop("--------------------------------------------------------");
            sop("Restarting....");
        }
        return;
    }

    public void checkOperation(int choice, Scanner d) {
        HashMap<Integer, Runnable> map = new HashMap<>();
        map.put(1, () -> {
            Query query = new Query();
            query.setSession(this.ses);
            TwoDate operation = new TwoDate(d, query);
            query = operation.init();
            query.setQueryEndTime(new DateTime());
            sop(query.toString());
        });
        map.put(2, () -> {
            Query query = new Query();
            query.setSession(this.ses);
            NDays ndays = new NDays(d, query);
            query = ndays.init();
            query.setQueryEndTime(new DateTime());
            sop(query.toString());
        });
        map.put(3, () -> {
            Query query = new Query();
            query.setSession(this.ses);
            DayWeek dw = new DayWeek(d, query);
            query =  dw.init(); 
            query.setQueryEndTime(new DateTime());
            sop(query.toString());
        });
        map.put(4, () -> {
            Query query = new Query();
            query.setSession(this.ses);
            WeekNumber wn = new WeekNumber(d, query);
            query =  wn.init(); 
            query.setQueryEndTime(new DateTime());
            sop(query.toString()); 
        });
        map.put(5, () -> {
            Query query = new Query();
            query.setSession(this.ses);
            NLP nlp = new NLP(d, query);
            query = nlp.translator();
            query.setQueryEndTime(new DateTime());
            sop(query.toString());
        });
        
        Runnable action = map.get(choice);
        if (action != null) {
            action.run();
        } else {
            sop("Invalid input for operation. Restart the app if you want to use it again");
            sop("--------------------------------------------------------");
            return;
        }
    }

    public static void introDisplay() {
        sop("Welcome to Date Time calculator");
        sop("Choose a menu item from the list of given operations.");
        sop("Enter 1 for addition/subtraction of two dates.");
        sop("Enter 2 for addition/subtraction of n days/weeks/months to a given date.");
        sop("Enter 3 to determine day of the week of a given date.");
        sop("Enter 4 to determine week number of a given date.");
        sop("Enter 5 to translate natural language phrases");
        sop("Enter quit to exit the session");
    }

}