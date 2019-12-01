package com.DataClasses;

public class Member extends User{

    protected int valid;
    protected String status;

    public Member(){
        super();
        this.status = null;
        this.valid = 0;
    }

    public Member(String[] userData){
        super(userData);
        updateStatus(userData[6]);
    }

    public void display(){
        System.out.println("Member Information:");
        super.display();
        System.out.println("Status:\t\t" + status);
    }

    public String getStatus(){
        return status;
    }

    public int updateStatus(String update){
        if(update == null)
            return -1;
        this.status = update;
        if(status.equals("Valid"))
            this.valid = 1;
        else
            this.valid = 0;

        return 1;
    }
}
