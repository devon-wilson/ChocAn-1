package com.DataClasses;

public class Member extends User{

    public Member(){
        super();
    }

    public Member(String[] userData){
        super(userData);
    }

    public int display(){
        System.out.println("Member Information:");
        System.out.println(super.name);
        System.out.println(super.ID);
        System.out.println(super.address);
        System.out.println(super.city);
        System.out.println(super.state);
        System.out.println(super.zip);
        return 0;
    }

}
