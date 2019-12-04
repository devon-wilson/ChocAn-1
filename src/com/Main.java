package com;

import com.Overlord.*;

public class Main {

    public static void main (String[] args) {

        Overlord overlord = new Overlord();



        if(overlord.login(1, "012345678") == -1)
            System.out.println("Couldn't login.");
        else
            System.out.println("Welcome to ChocAn");

        overlord.displayCurrentServices();

        overlord.memberCheckIn("012345678");

        overlord.viewMember();
    }
}
