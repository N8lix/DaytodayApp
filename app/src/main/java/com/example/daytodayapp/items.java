package com.example.daytodayapp;

public class items {

    private String name;
    private String point;
    private String done;



    public String getName() {
        return name;
    }

    public items (String a, String b , String c){
        this.name = a;
        this.point = b;
        this.done = c;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }



}