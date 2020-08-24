package com.sapient.calculator;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App extends CommonMethod
{
    public static void main( String[] args )
    {
        sop("Select any option from the given menu: ");
        sop("Enter 1 to perform various operation on date using DateTime calculator.");
        sop("Enter 2 to perform automation, bulk processing and visit history of the sessions.");
        Scanner d = new Scanner(System.in);
        int choice = d.nextInt();
        

        if(choice == 1){
            DateTimeCalculator dt = new DateTimeCalculator(d);
            dt.start();
        }
        else if(choice == 2){
            BulkController bc = new BulkController(d);
            bc.init();
        }
        else{
            sop("Enter a valid input");
        }

        d.close();
    }
}
