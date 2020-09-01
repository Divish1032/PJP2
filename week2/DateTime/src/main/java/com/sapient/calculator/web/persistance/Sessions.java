package com.sapient.calculator.web.persistance;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class 
Sessions {
    @Id
    String id;
    Timestamp startTime;
    Timestamp endTime;
    Boolean start;

    public Sessions(){
        this.start = false;
        this.id = UUID.randomUUID().toString();
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Session [endTime=" + endTime + ", start=" + start + ", startTime=" + startTime + "]";
    }

    
    
}