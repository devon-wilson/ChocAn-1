package com.DataClasses;

public class Service {


    public Service(){
        name = null;
        ID = null;
        cost = null;
    }

    public Service(String[] info){
        this.name = info[0];
        this.ID = info[1];
        this.cost = info[2];
    }

    public String getName(){
        return name;
    }

    private String name;
    private String ID;
    private String cost;
}
