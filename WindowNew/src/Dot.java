package com.company;

public class Dot {
    protected double x;
    protected double y;

    public Dot() {

    }

    public Dot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "x = " + x + " y = " + y;

    }



}