package com.sapient.calculator.web.models;

import org.joda.time.DateTime;

public class AutomatedQuery {
    int type;
    DateTime date1, date2;
    int format;
    int n;
    String phrase;
    Boolean addition;
    String answer;

    public AutomatedQuery(int type, DateTime date1, DateTime date2, int format, int n, String phrase,
         Boolean addition) {
        this.type = type;
        this.date1 = (type != 5) ? date1 : null;
        this.date2 = (type == 1) ? date1 : null;
        this.format = (type != 5) ? format : 0;
        this.n = (type == 2 ) ? n : 0;
        this.phrase = (type == 5) ? phrase : "";
        this.addition = (type == 1 || type == 2) ? addition : false;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DateTime getDate1() {
        return date1;
    }

    public void setDate1(DateTime date1) {
        this.date1 = date1;
    }

    public DateTime getDate2() {
        return date2;
    }

    public void setDate2(DateTime date2) {
        this.date2 = date2;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Boolean getAddition() {
        return addition;
    }

    public void setAddition(Boolean addition) {
        this.addition = addition;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }




}