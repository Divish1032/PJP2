package com.sapient.calculator.web.operations;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import com.sapient.calculator.web.CommonMethod;
import com.sapient.calculator.web.models.AutomatedQuery;
import com.sapient.calculator.web.persistance.Queries;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.function.Consumer;
import org.joda.time.DateTime;


public class NLP extends CommonMethod{
    private String txt;
    DateTime date;
    HashMap<String, Consumer<Integer>> translator;
    Queries query;
    AutomatedQuery aq;
    private final Scanner temp;
    String answer;

    public NLP(Scanner d, Queries q, AutomatedQuery aq){
        this.txt = "";
        this.date = new DateTime();
        this.translator = new HashMap<>();
        this.query = q;
        this.temp = d;
        this.aq = aq;
        this.answer = "";
    }



    public int findDigit() {
        Pattern digit = Pattern.compile("\\d+");
        Matcher regex = digit.matcher(txt);

        if (regex.find()){
            return Integer.parseInt(regex.group(0));
        }
        return 0;
    }

    public void cleanText() {
        txt = txt.replaceAll("\\d","");
        txt = txt.trim();
    }

    public void input() {
        // set query start time to now
        query.setQueryStartTime(new Timestamp( new DateTime().getMillis()));
        // set type of query
        query.setType(5);
    }

    public Queries translator(){
        input();
        
        if(this.aq == null)  {
            sopSL("Enter a phrase to be translated to date: ");
            this.txt = temp.next();
        }
        else this.txt = this.aq.getPhrase();


        query.setPhrase(this.txt);

        txt = txt.toLowerCase();

        // Find if there is any digit
        int number = 0;
        number = findDigit();

        // remove extra spaces and digits
        cleanText();

        // design of the translator
        designTranslator();

        // check if given input is present or not
        if(translator.containsKey(txt)){
            translator.get(txt).accept(number);
            if(this.aq == null)  {
                sop(this.answer);
            }
            query.setResult(this.answer);
        } else {
            String res = "Our tranlator is not able to understand the given text, please stay with us, we are improving our algorithm.";
            if(this.aq == null)  {
                sop(res);
            }
            query.setResult(res);
        }

        return query;
    }


    public void designTranslator() {
        translator.put("today", (Integer n) -> {
            this.answer = date.toString();
        });
        translator.put("tomorrow", (Integer n) -> {
            this.answer = date.plusDays(1).toString();
        });
        translator.put("days earlier", (Integer n) -> {
            
            this.answer = date.minusDays(n).toString();
        });
        translator.put("month after", (Integer n) -> {
            
            this.answer = date.plusMonths(1).toString();
        });
        translator.put("next month", (Integer n) -> {
            
            this.answer = date.plusMonths(1).toString();
        });
        translator.put("yesterday", (Integer n) -> {
            
            this.answer = date.minusDays(1).toString();
        });
        translator.put("day before yesterday", (Integer n) -> {
            
            this.answer = date.minusDays(2).toString();
        });
        translator.put("next week", (Integer n) -> {
            
            this.answer = date.plusWeeks(1).toString();
        });
        translator.put("month before", (Integer n) -> {
            
            this.answer = date.minusMonths(1).toString();
        });
        translator.put("weeks from now", (Integer n) -> {
            
            this.answer = date.plusWeeks(n).toString();
        });        
        translator.put("previous week", (Integer n) -> {
            
            this.answer = date.minusWeeks(1).toString();
        });
        translator.put("last month", (Integer n) -> {
            
            this.answer = date.minusMonths(1).toString();
        });
        translator.put("last week", (Integer n) -> {
        
            this.answer = date.minusWeeks(1).toString();
        });
        translator.put("months earlier", (Integer n) -> {
            
            this.answer = date.minusMonths(n).toString();
        });
        translator.put("years earlier", (Integer n) -> {
            
            this.answer = date.minusYears(n).toString();
        });
        translator.put("day after tomorrow", (Integer n) -> {
            
            this.answer = date.plusDays(2).toString();
        });
        translator.put("last year", (Integer n) -> {
            
            this.answer = date.minusYears(1).toString();
        });
        translator.put("next year", (Integer n) -> {
            
            this.answer = date.plusYears(1).toString();
        });
        translator.put("days from now", (Integer n) -> {
            
            this.answer = date.plusDays(n).toString();
        });
        translator.put("months from now", (Integer n) -> {
            
            this.answer = date.plusMonths(n).toString();
        });
        translator.put("years from now", (Integer n) -> {
            this.answer = date.plusYears(n).toString();
        });
        translator.put("weeks earlier", (Integer n) -> {
            
            this.answer = date.minusWeeks(n).toString();
        });
    }

}