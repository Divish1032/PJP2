package com.sapient.calculator.web.persistance;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Queries {
    
    @Id
    String id;
    int type;
    Timestamp date1, date2;
    int format;
    int n;
    String phrase;
    Timestamp queryStartTime, queryEndTime;
    String result;
    @Embedded
    Sessions session;
    Boolean error, addition;

    public Queries() {
        this.id = UUID.randomUUID().toString();
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

    public Timestamp getDate1() {
        return date1;
    }

    public void setDate1(Timestamp date1) {
        this.date1 = date1;
    }

    public Timestamp getDate2() {
        return date2;
    }

    public void setDate2(Timestamp date2) {
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

    public Timestamp getQueryStartTime() {
        return queryStartTime;
    }

    public void setQueryStartTime(Timestamp queryStartTime) {
        this.queryStartTime = queryStartTime;
    }

    public Timestamp getQueryEndTime() {
        return queryEndTime;
    }

    public void setQueryEndTime(Timestamp queryEndTime) {
        this.queryEndTime = queryEndTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Sessions getSession() {
        return session;
    }

    public void setSession(Sessions session) {
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