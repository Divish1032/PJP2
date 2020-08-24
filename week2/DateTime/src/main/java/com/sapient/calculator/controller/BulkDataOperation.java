package com.sapient.calculator.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.joda.time.DateTime;
import com.sapient.calculator.CommonMethod;
import com.sapient.calculator.models.AutomatedQuery;
import com.sapient.calculator.operations.DayWeek;
import com.sapient.calculator.operations.NDays;
import com.sapient.calculator.operations.NLP;
import com.sapient.calculator.operations.TwoDate;
import com.sapient.calculator.operations.WeekNumber;
import com.sapient.calculator.persistance.Queries;

public class BulkDataOperation extends CommonMethod{
    ArrayList<AutomatedQuery> data = new ArrayList<>();

    static ArrayList<AutomatedQuery> result = new ArrayList<>();


    public void init() {
        Scanner d = new Scanner(System.in);
        // read queries from the file
        readQueryFile();
        sop("------------------------");
        for (AutomatedQuery aq : data) {
            checkOperation(d, aq);   
        }
        d.close();

        // write queries to a csv file
        writeQueryResultToFile();
    }

    public void checkOperation(Scanner d, AutomatedQuery aq) {
        HashMap<Integer, Runnable> map = new HashMap<>();
        map.put(1, () -> {
            Queries query = new Queries();
            TwoDate operation = new TwoDate(d, query, aq);
            query = operation.init();
            aq.setAnswer(query.getResult());
            result.add(aq);
        });
        map.put(2, () -> {
            Queries query = new Queries();
            NDays ndays = new NDays(d, query, aq);
            query = ndays.init();
            aq.setAnswer(query.getResult());
            result.add(aq);
        });
        map.put(3, () -> {
            Queries query = new Queries();
            DayWeek dw = new DayWeek(d, query, aq);
            query =  dw.init(); 
            aq.setAnswer(query.getResult());
            result.add(aq);
        });
        map.put(4, () -> {
            Queries query = new Queries();
            WeekNumber wn = new WeekNumber(d, query, aq);
            query =  wn.init(); 
            aq.setAnswer(query.getResult());
            result.add(aq);
        });
        map.put(5, () -> {
            Queries query = new Queries();
            NLP nlp = new NLP(d, query, aq);
            query = nlp.translator();
            aq.setAnswer(query.getResult());
            result.add(aq);
        });
        
        Runnable action = map.get(aq.getType());
        if (action != null) {
            action.run();
        } else {
            sop("Invalid input for operation. Restart the app if you want to use it again");
            sop("--------------------------------------------------------");
            return;
        }
    }

    public void readQueryFile() {
        try {
            Scanner sc = new Scanner(new File(
                    "C:\\Users\\hp\\Desktop\\Sapients\\Assignment\\PJP2\\week2\\DateTime\\src\\main\\resources\\java_bulk_query_output.csv"));
            sc.useDelimiter(";");
            sc.next();
           //sets the delimiter pattern  
            while (sc.hasNext())
            {  
                String s = sc.next();
                String[] read = s.split(",");            
                AutomatedQuery save = saveQuery(read);
                data.add(save);
            }   
            sc.close(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } 

    public AutomatedQuery saveQuery(String[] read) {
        int type = Integer.parseInt(read[0]);
        int n= read[1].equals("null") ? 0 : Integer.parseInt(read[1]);
        int format = read[2].equals("null") ? 0 : Integer.parseInt(read[2]);
        DateTime date1 = read[3].equals("null") ? new DateTime() :  new DateTime(Long.parseLong(read[3]));
        DateTime date2 = read[4].equals("null") ? new DateTime() :  new DateTime(Long.parseLong(read[4]));
        String phrase = read[5].equals("null") ? "" :  read[5];
        Boolean addition = (read[6] == "false") ? false : true;
        return new AutomatedQuery(type, date1, date2, format, n, phrase, addition);
    }




    public void writeQueryResultToFile() {
        PrintWriter pw = null;
       try {
           pw = new PrintWriter(new File("C:\\Users\\hp\\Desktop\\Sapients\\Assignment\\PJP2\\week2\\DateTime\\src\\main\\resources\\bulk_query_result.csv"));
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       StringBuilder builder = new StringBuilder();
       String columnNamesList = "Type,N_value,Format,Date1,Date2,Phrase,Addition,Query_Answer";
       builder.append(columnNamesList +"\n");

       for(AutomatedQuery x : result) {
        builder.append(x.getType()+",");
        builder.append(x.getN() == 0 ? "null," : x.getN()+",");
        builder.append(x.getFormat() == 0 ? "null," : x.getFormat()+",");
        builder.append(x.getDate1() == null ? "null," : x.getDate1()+",");
        builder.append(x.getDate2() == null ? "null," : x.getDate2()+",");
        builder.append(x.getPhrase() == null ? "null," : x.getPhrase()+",");
        builder.append(x.getAddition() == null ? "null," : x.getAddition() +",");
        builder.append(x.getAnswer() == null ? "null," : x.getAnswer() +",");
        builder.append('\n');
        builder.append("Query ended\n");
        builder.append("---------------------------------\n");
    }
    
    pw.write(builder.toString());
    pw.close();
       
    }


}