package com.sapient.calculator.operations;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import com.sapient.calculator.CommonMethod;
import java.util.HashMap;
import java.util.function.Consumer;
import org.joda.time.DateTime;


public class NLP extends CommonMethod{
    private String txt;
    DateTime date;
    HashMap<String, Consumer<Integer>> translator;


    public NLP(){
        this.txt = "";
        this.date = new DateTime();
        this.translator = new HashMap<>();
        input();
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
        Scanner d = new Scanner(System.in);
        sopSL("Enter a phrase to be translated to date: ");
        this.txt = d.nextLine();
        d.close();
    }

    public void translator(){
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
        } else {
            sop("We tranlator is not able to understand the given text, please stay with us, we are improving our algorithm.");
        }
    }


    public void designTranslator() {
        translator.put("today", (Integer n) -> {
            sop(date.toString());
        });
        translator.put("tomorrow", (Integer n) -> {
            sop(date.plusDays(1).toString());
        });
        translator.put("days earlier", (Integer n) -> {
            sop(date.minusDays(n).toString());
        });
        translator.put("month after", (Integer n) -> {
            sop(date.plusMonths(1).toString());
        });
        translator.put("next month", (Integer n) -> {
            sop(date.plusMonths(1).toString());
        });
        translator.put("yesterday", (Integer n) -> {
            sop(date.minusDays(1).toString());
        });
        translator.put("day before yesterday", (Integer n) -> {
            sop(date.minusDays(2).toString());
        });
        translator.put("next week", (Integer n) -> {
            sop(date.plusWeeks(1).toString());
        });
        translator.put("month before", (Integer n) -> {
            sop(date.minusMonths(1).toString());
        });
        translator.put("weeks from now", (Integer n) -> {
            sop(date.plusWeeks(n).toString());
        });        
        translator.put("previous week", (Integer n) -> {
            sop(date.minusWeeks(1).toString());
        });
        translator.put("last month", (Integer n) -> {
            sop(date.minusMonths(1).toString());
        });
        translator.put("last week", (Integer n) -> {
            sop(date.minusWeeks(1).toString());
        });
        translator.put("months earlier", (Integer n) -> {
            sop(date.minusMonths(n).toString());
        });
        translator.put("years earlier", (Integer n) -> {
            sop(date.minusYears(n).toString());
        });
        translator.put("day after tomorrow", (Integer n) -> {
            sop(date.plusDays(2).toString());
        });
        translator.put("last year", (Integer n) -> {
            sop(date.minusYears(1).toString());
        });
        translator.put("next year", (Integer n) -> {
            sop(date.plusYears(1).toString());
        });
        translator.put("days from now", (Integer n) -> {
            sop(date.plusDays(n).toString());
        });
        translator.put("months from now", (Integer n) -> {
            sop(date.plusMonths(n).toString());
        });
        translator.put("years from now", (Integer n) -> {
            sop(date.plusYears(n).toString());
        });
        translator.put("weeks earlier", (Integer n) -> {
            sop(date.minusWeeks(n).toString());
        });
    }

}