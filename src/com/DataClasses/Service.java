package com.DataClasses;

public class Service {

    private String name;
    private String ID;
    private String cost;

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

    public String getCost() { return cost; }

    public String getCode(){
        return ID;
    }

    public String[] getAll(){
        String [] components = new String [3];
        components[0] = name;
        components[1] = ID;
        components[2] = cost;
        return components;
    }

    public void display() {
        System.out.println("Name:\t" + name + "\n" +
                            "Code:\t" + ID + "\n" +
                            "Fee:\t" + cost);
    }
}
