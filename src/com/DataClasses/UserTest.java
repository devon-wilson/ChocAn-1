package com.DataClasses;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
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
        if(buildUser(0, components) == -1){
            fail("File read failed.");
        }
        if(buildUser(1, components2) == -1){
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

    @Test
    void changeName() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeName(toChange) != -1){
            fail("Change name to null failed");
        }
        toChange = new String("Test");
        if(myMember.changeName(toChange) != 0){
            fail("Valid name change failed");
        }
        String check = myMember.get(0);
        if(check.equals(toChange) == false){
            fail("Name does not match");
        }
    }

    @Test
    void changeID() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeID(toChange) != -1){
            fail("Change ID to null failed");
        }
        toChange = new String("Test");
        if(myMember.changeID(toChange) != 0){
            fail("Valid ID change failed");
        }
        String check = myMember.get(1);
        if(check.equals(toChange) == false){
            fail("ID does not match");
        }
    }

    @Test
    void changeAddress() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeAddress(toChange) != -1){
            fail("Change address to null failed");
        }
        toChange = new String("Test");
        if(myMember.changeAddress(toChange) != 0){
            fail("Valid address change failed");
        }
        String check = myMember.get(2);
        if(check.equals(toChange) == false){
            fail("Address does not match");
        }
    }

    @Test
    void changeCity() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeCity(toChange) != -1){
            fail("Change City to null failed");
        }
        toChange = new String("Test");
        if(myMember.changeCity(toChange) != 0){
            fail("Valid city change failed");
        }
        String check = myMember.get(3);
        if(check.equals(toChange) == false){
            fail("City does not match");
        }
    }

    @Test
    void changeState() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeState(toChange) != -1){
            fail("Change state to null failed");
        }
        toChange = new String("Test");
        if(myMember.changeState(toChange) != 0){
            fail("Valid state change failed");
        }
        String check = myMember.get(4);
        if(check.equals(toChange) == false){
            fail("State does not match");
        }
    }

    @Test
    void changeZip() {
        Member myMember = new Member();
        String toChange = null;
        if(myMember.changeZip(toChange) != -1){
            fail("Change zip to null failed");
        }
        toChange = new String("Test");
        if(myMember.changeZip(toChange) != 0){
            fail("Valid zip change failed");
        }
        String check = myMember.get(5);
        if(check.equals(toChange) == false){
            fail("Zip does not match");
        }
    }


    @Test
    void get() {
        Member myMember = new Member();
        String [] components = new String [6];
        buildUser(0, components);
        myMember.build(components);
        for(int i = 0; i < 6; ++i){
            if(components[i].equals(myMember.get(i)) == false){
                fail("Get report item failed.");
            }
        }
    }

    private int buildUser(int line, String [] components){
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
        for(int i =0; i < line; ++i){
            sc.nextLine();
        }
        for(int i = 0; i < 6; ++i){
            components[i] = sc.next();
        }
        return 0;
    }


    private File file;
    private Scanner sc;
}