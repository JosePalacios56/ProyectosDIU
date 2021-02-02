package com.example.tutoriales.service;

import java.util.Date;

public class Notification {


    String text;
    Date time;
    public static Integer jobId=0;


    public Notification(String text, Date time){
        super();
        this.text=text;
        this.time=time;

    }

    public static Integer getNextJobId(){
        return ++jobId;
    }

    public String getText() {
        return text;
    }

    public Date getTime() {
        return time;
    }

    public static Integer getJobId() {
        return jobId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public static void setJobId(Integer jobId) {
        Notification.jobId = jobId;
    }
}
