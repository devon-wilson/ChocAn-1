package com.DataClasses;

public class Manager extends User{

    public Manager(){
        super();
    }

    public Manager(String[] userData){
        super(userData);
    }

    public void display(){
        System.out.println("Manager Information:");
        super.display();
    }


}
