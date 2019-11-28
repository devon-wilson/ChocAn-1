package com.DataClasses;

public class Manager extends User{

    public Manager(){
        super();
    }

    public Manager(String[] userData){
        super(userData);
    }

    public int display(){
        System.out.println("Manager Information:");
        System.out.println(super.name);
        System.out.println(super.ID);
        System.out.println(super.address);
        System.out.println(super.city);
        System.out.println(super.state);
        System.out.println(super.zip);
        return 0;
    }


}
