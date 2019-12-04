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
    }

    @org.junit.jupiter.api.Test
    void changeID() {
    }

    @org.junit.jupiter.api.Test
    void changeAddress() {
    }

    @org.junit.jupiter.api.Test
    void changeCity() {
    }

    @org.junit.jupiter.api.Test
    void changeState() {
    }

    @org.junit.jupiter.api.Test
    void changeZip() {
    }

    @org.junit.jupiter.api.Test
    void getAll() {
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