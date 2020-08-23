package com.sapient.calculator.persistance;

import org.joda.time.DateTime;

public class Query {
    int type;
    DateTime date1, date2;
    int format;
    int n;
    String phrase;
    DateTime queryStartTime, queryEndTime;
    String result;
    Session session;
    Boolean error, addition;

    public Query() {
        this.date1 = null;
        this.date2 = null;
        this.n = 0;
        this.phrase = "";
        this.error = false;
        this.addition = true;
        this.format = 0;
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

    public DateTime getQueryStartTime() {
        return queryStartTime;
    }

    public void setQueryStartTime(DateTime queryStartTime) {
        this.queryStartTime = queryStartTime;
    }

    public DateTime getQueryEndTime() {
        return queryEndTime;
    }

    public void setQueryEndTime(DateTime queryEndTime) {
        this.queryEndTime = queryEndTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Query [date1=" + date1 + ", date2=" + date2 + ", n=" + n + ", phrase=" + phrase + ", queryEndTime="
                + queryEndTime + ", queryStartTime=" + queryStartTime + ", result=" + result + ", session=" + session
                + ", type=" + type + "]";
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }


    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getAddition() {
        return addition;
    }

    public void setAddition(Boolean addition) {
        this.addition = addition;
    }

    
}