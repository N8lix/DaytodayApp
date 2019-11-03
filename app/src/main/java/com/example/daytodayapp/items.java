package com.example.daytodayapp;

public class items {

    private String name;
    private int point;
    private String done;
    private int whose;



    public String getName() {
        return name;
    }

    public items (String a, int b , String c, int d){
        this.name = a;
        this.point = b;
        this.done = c;
        this.whose = d;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }


    public int getWhose() {
        return whose;
    }

    public void setWhose(int whose) {
        this.whose = whose;
    }
}