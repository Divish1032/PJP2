package com.sapient.calculator;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.sapient.calculator.operations.DayWeek;
import com.sapient.calculator.operations.NDays;
import com.sapient.calculator.operations.NLP;
import com.sapient.calculator.operations.TwoDate;
import com.sapient.calculator.operations.WeekNumber;


public class DateTimeCalculator extends CommonMethod {


    public static void main(String[] args){
        Scanner d = new Scanner(System.in);
        // Display introductory messages
        introDisplay();
        int choice;
        // Get input from user for his choice of the operation
        try {
            choice = inputChoice(d);
            // clearing the console for better visualization
            clearConsole();
            
        } catch (InputMismatchException e) {
            sop("Input should be a number");
            return;
        }


        checkOperation(choice, d);
        d.close();
        return;
    }

    public static void checkOperation(int choice, Scanner d) {
        HashMap<Integer, Runnable> map = new HashMap<>();
        map.put(1, () -> {
            TwoDate operation = new TwoDate(d);
            operation.init();
        });
        map.put(2, () -> {
            NDays ndays = new NDays(d);
            ndays.init(); 
        });
        map.put(3, () -> {
            DayWeek dw = new DayWeek(d);
            dw.init(); 
        });
        map.put(4, () -> {
            WeekNumber wn = new WeekNumber(d);
            wn.init(); 
        });

        map.put(5, () -> {
            NLP nlp = new NLP();
            nlp.translator();
        });
        
        Runnable action = map.get(choice);
        if (action != null) {
            action.run();
        } else {
            sop("Invalid input for operation. Restart the app if you want to use it again");
        }
    }


    public static void nlpTransformation() {
        sop("You opted for nlp transformation of a given date.");
    }



    public static void introDisplay() {
        sop("Welcome to Date Time calculator");
        sop("Choose a menu item from the list of given operations.");
        sop("Enter 1 for addition/subtraction of two dates.");
        sop("Enter 2 for addition/subtraction of n days/weeks/months to a given date.");
        sop("Enter 3 to determine day of the week of a given date.");
        sop("Enter 4 to determine week number of a given date.");
        sop("Enter 5 to translate natural language phrases");
    }

}