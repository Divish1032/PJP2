package com.sapient.calculator;

import java.util.Scanner;

import com.sapient.calculator.controller.BulkDataGenerator;
import com.sapient.calculator.controller.BulkDataOperation;
import com.sapient.calculator.controller.SessionHistory;

public class BulkController extends CommonMethod {
    Scanner temp; 
    
    BulkController(Scanner d){
        this.temp = d;
    }

    public void init(){
        bolierplateMessage();
        // Input from user to select any one of the three menu items.
        int choice = temp.nextInt();
        userSelection(choice);
    }

    private void bolierplateMessage() {
        sop("Pick a option");
        sop("Enter 1 for bulk operation on DateTime calculator.");
        sop("Enter 2 for automating creation of bulk operation file");
        sop("Enter 3 to view the dashboard for the history of last 100 session done users.");
    }

    private void userSelection(int choice) {
        switch (choice) {
            case 1:
                BulkDataOperation bo = new BulkDataOperation();
                bo.init();
                break;
            
            case 2:
                BulkDataGenerator bulk = new BulkDataGenerator();
                bulk.init(1000);
                break;
            case 3:
                SessionHistory sh = new SessionHistory();
                sh.init();
                break;
            default:
                sop("Enter a valid input");
                break;
        }
    }
}