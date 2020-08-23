package com.sapient.calculator.models;

import java.util.Scanner;

import com.sapient.calculator.CommonMethod;
import com.sapient.calculator.operations.MethodInterface;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateInput extends CommonMethod{
    Scanner temp;
    int choice;
    // Type 1 of the format of date input - dd/MM/yy
    static MethodInterface type1 = (final Scanner dup) -> {
        try {
            final String date = dup.next();
            final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
            return formatter.parseDateTime(date);
        } catch (final Exception e) {
            //sop(e.getMessage());
            throw e;
        }
    };

    //Type 2 of the format of date input with intuitive process.
    static MethodInterface type2 = (final Scanner dup) -> {
        sopSL("Enter year: ");
        final int year = dup.nextInt();
        sopSL("Enter Month: ");
        final int month = dup.nextInt();
        sopSL("Enter day: ");
        final int day = dup.nextInt();
        final Dates date = new Dates(day, month, year);
        return date.toDate();
    };

    public DateInput(final Scanner d, int choice) {
        this.temp = d;
        this.choice = choice;
    }


    public DateTime init(){
        try {
            // input specified date
            return inputDate(this.choice);
        } catch (final Exception e) {
            sop(e.getMessage());
            return null;
        }
    }




    public DateTime inputDate(final int choice) throws Exception {
        switch (choice) {
            case 1:
                return dateInputType1();
            case 2:
                return dateInputType2();
            default:
                return null;
        }
    }


    // dd/MM/yyy
    public DateTime dateInputType1() throws Exception {
        sop("You opted for format 1");
        sop("Enter a date");
        return inputDatesOperation(type1);
    }

    // intuitive input date
    public DateTime dateInputType2() throws Exception {
        sop("You opted for format 2");
        sop("Enter a date");
        return inputDatesOperation(type2);
    }

    public DateTime inputDatesOperation(final MethodInterface obj1) throws Exception {
        try {
            return obj1.formatDate(temp);
        } catch (final Exception e) {
            throw e;
        }
    }
    
}