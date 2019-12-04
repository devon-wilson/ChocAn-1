package com.DataClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @org.junit.jupiter.api.Test
    void build() {
        User myMember = new Member();
        String [] components = null;
        String [] components2 = null;
        if(myMember.build(components) != -1){
            fail("Build with null failed");
        }
        components = new String[6];
        for(int i = 0; i < 6; ++i){
            components[i] = null;
        }
        if(myMember.build(components) != -1){
            fail("Build user with Null Strings failed.");
        }

        components2 = new String [6];
        if(buildUser(components, components2) == -1){
            fail("File read failed.");
        }
        if(myMember.build(components) != 0){
            fail("Build user with info failed");
        }
        String [] data = myMember.getAll();
        for(int i = 0; i < 6; ++i){
            if(data[i].equals(components[i]) == false){
                fail("First build data not correct");
            }
        }
        if(myMember.build(components2) != 0){
            fail("Build user with info overwrite failed");
        }
        data = myMember.getAll();
        for(int i = 0; i < 6; ++i){
            if(data[i].equals(components2[i]) == false){
                System.out.println(components2[i]);
                System.out.println(components[i]);
                fail("Second build data not correct");
            }
        }
        components[0] = null;
        if(myMember.build(components) != -1){
            fail("Build with one null field failed");
        }
    }

    @org.junit.jupiter.api.Test
    void changeName() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeName(toChange) != -1){
            fail("Change name to null failed");
        }
        String toChange = new String("Test");
        if(myMember.changeName(toChange) != 0){
            fail("Valid name change failed");
        }
        String [] components = myMember.getAll();
        if(components[0].equals(toChange) == false){
            fail("Name does not match");
        }
    }

    @org.junit.jupiter.api.Test
    void changeID() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeID(toChange) != -1){
            fail("Change ID to null failed");
        }
        String toChange = new String("Test");
        if(myMember.changeID(toChange) != 0){
            fail("Valid ID change failed");
        }
        String [] components = myMember.getAll();
        if(components[1].equals(toChange) == false){
            fail("ID does not match");
        }
    }

    @org.junit.jupiter.api.Test
    void changeAddress() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeAddress(toChange) != -1){
            fail("Change address to null failed");
        }
        String toChange = new String("Test");
        if(myMember.changeAdress(toChange) != 0){
            fail("Valid adress change failed");
        }
        String [] components = myMember.getAll();
        if(components[2].equals(toChange) == false){
            fail("Adress does not match");
        }
    }

    @org.junit.jupiter.api.Test
    void changeCity() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeCity(toChange) != -1){
            fail("Change City to null failed");
        }
        String toChange = new String("Test");
        if(myMember.changeCity(toChange) != 0){
            fail("Valid city change failed");
        }
        String [] components = myMember.getAll();
        if(components[3].equals(toChange) == false){
            fail("City does not match");
        }
    }

    @org.junit.jupiter.api.Test
    void changeState() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeState(toChange) != -1){
            fail("Change state to null failed");
        }
        String toChange = new String("Test");
        if(myMember.changeState(toChange) != 0){
            fail("Valid state change failed");
        }
        String [] components = myMember.getAll();
        if(components[4].equals(toChange) == false){
            fail("State does not match");
        }
    }

    @org.junit.jupiter.api.Test
    void changeZip() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeZip(toChange) != -1){
            fail("Change zip to null failed");
        }
        String toChange = new String("Test");
        if(myMember.changeZip(toChange) != 0){
            fail("Valid zip change failed");
        }
        String [] components = myMember.getAll();
        if(components[5].equals(toChange) == false){
            fail("Zip does not match");
        }
    }


    @org.junit.jupiter.api.Test
    void get() {
        
    }

    private int buildUser(String [] comp1, String [] comp2){
        file = new File("data/users.csv");
        if(file == null){
            return -1;
        }
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
        sc.useDelimiter(",");
        for(int i = 0; i < 6; ++i){
            comp1[i] = sc.next();
        }
        sc.nextLine();
        for(int i = 0; i < 6; ++i){
            comp2[i] = sc.next();
        }
        return 0;
    }


    private File file;
    private Scanner sc;
}