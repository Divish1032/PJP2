package com.sapient.calculator.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import com.sapient.calculator.web.CommonMethod;
import com.sapient.calculator.web.models.AutomatedQuery;

import org.joda.time.DateTime;

public class BulkDataGenerator extends CommonMethod{
    ArrayList<AutomatedQuery> data = new ArrayList<>();

    static String[] temp = {
        "today", "tomorrow", "next day", "last year", "last week", "last day",
         "next month", "next year", "4 months from now", "3 days earlier"
    };
    
    public void init(int limit) {
        sop("Bulk data generation process started...");
        sop(limit + "");
        for(int i = 0; i< limit; i++){
            data.add(initiateBUilding());
        }

        writeDataToFile();
        sop("Bulk data generation process completed.");
        sop("File generated at resources folders with name java_bulk_query_output.csv");
        sop("------------------------------------------------------");
    }

    public void writeDataToFile() {
        PrintWriter pw = null;
       try {
           pw = new PrintWriter(new File("C:\\Users\\hp\\Desktop\\Sapients\\Assignment\\PJP2\\week2\\DateTime\\src\\main\\resources\\java_bulk_query_output.csv"));
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       StringBuilder builder = new StringBuilder();
       String columnNamesList = "Type, N_value, Format, Date1, Date2, Phrase, Addition;";
       builder.append(columnNamesList);
       int i = 0;
       int l = this.data.size();
       for(AutomatedQuery x : this.data) {
           i++;
        builder.append(x.getType()+",");
        builder.append(x.getN() == 0 ? "null," : x.getN()+",");
        builder.append(x.getFormat() == 0 ? "null," : x.getFormat()+",");
        builder.append(x.getDate1() == null ? "null," : x.getDate1().getMillis()+",");
        builder.append(x.getDate2() == null ? "null," : x.getDate2().getMillis()+",");
        builder.append(x.getPhrase() == null ? "null," : x.getPhrase()+",");
        builder.append(x.getAddition() == null ? "null" : x.getAddition());
        if(i != l)
        builder.append(";");
    }
    
    pw.write(builder.toString());
    pw.close();
       
    }

    public AutomatedQuery initiateBUilding(){
        Random rand = new Random();
        int type = rand.nextInt(5) + 1;
        int format = rand.nextInt(2) + 1;
        DateTime date1 = randomDate();
        DateTime date2 = randomDate();
        int n = rand.nextInt(1000) + 1;
        Boolean addition = rand.nextInt(2) == 0 ? true : false;
        String phrase = temp[rand.nextInt(10)];
        return new AutomatedQuery(type, date1, date2, format, n, phrase, addition);
    }

    public DateTime randomDate(){
        Random rand = new Random();
        int year = rand.nextInt(2100 -  + 1) + 1900;
        int month = rand.nextInt(12) + 1;
        int day = rand.nextInt(28) + 1;
        DateTime dt = new DateTime(year, month, day, 0, 0, 0, 0);
        return dt;
    }
}