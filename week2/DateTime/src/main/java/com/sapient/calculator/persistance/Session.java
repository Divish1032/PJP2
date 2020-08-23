package com.sapient.calculator.persistance;

import org.joda.time.DateTime;

public class Session {
    DateTime startTime;
    DateTime endTime;
    Boolean start;

    public Session(){
        this.start = false;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
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