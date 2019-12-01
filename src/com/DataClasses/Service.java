package com.DataClasses;

public class Service {
    private String name;
    private String ID;

    public Service(){
        name = null;
        ID = null;
    }

    public Service(String[] info){
        this.name = info[0];
        this.ID = info[1];
    }

    public String getName(){
        return name;
    }
}
