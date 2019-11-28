package com.DataClasses;

public class Provider extends User{

    public Provider(){
        super();
    }

    public Provider(String[] userData) {
        super(userData);
    }

    public int display(){
        System.out.println("Provider Information:");
        System.out.println(super.name);
        System.out.println(super.ID);
        System.out.println(super.address);
        System.out.println(super.city);
        System.out.println(super.state);
        System.out.println(super.zip);
        return 0;
    }
}
