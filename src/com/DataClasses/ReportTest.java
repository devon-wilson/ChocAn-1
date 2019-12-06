package com.DataClasses;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void build() {
        User myMember = null;
        MemberReport myReport = new MemberReport();
        String [] components = null;
        String [] components2 = null;
        if(myReport.build(myMember) != -1){
            fail("Build report with null failed");
        }
        components = new String[6];
        for(int i = 0; i < 6; ++i){
            components[i] = null;
        }
        if(myReport.build(myMember) != -1){
            fail("Build report with Null Strings failed.");
        }

        components2 = new String [6];
        if(buildUser(0, components) == -1){
            fail("File read failed.");
        }
        if(buildUser(1, components2) == -1){
            fail("File read failed.");
        }
        myMember = new Member();
        myMember.build(components);
        if(myReport.build(myMember) != 0){
            fail("Build report failed.");
        }
        myMember.build(components2);
        if(myReport.build(myMember) != 0){
            fail("Build report with info overwrite failed");
        }
    }

    @Test
    void changeName() {
        MemberReport myReport = new MemberReport();
        String toChange = null;
        if(myReport.changeName(toChange) != -1){
            fail("Change name to null failed");
        }
        toChange = new String("Test");
        if(myReport.changeName(toChange) != 0){
            fail("Valid name change failed");
        }
        String check = myReport.get(0);
        if(check.equals(toChange) == false){
            fail("Name does not match");
        }
    }

    @Test
    void changeID() {
        MemberReport myReport = new MemberReport();
        String toChange = null;
        if(myReport.changeID(toChange) != -1){
            fail("Change ID to null failed");
        }
        toChange = new String("Test");
        if(myReport.changeID(toChange) != 0){
            fail("Valid ID change failed");
        }
        String check = myReport.get(1);
        if(check.equals(toChange) == false){
            fail("ID does not match");
        }
    }

    @Test
    void changeAddress() {
        MemberReport myReport = new MemberReport();
        String toChange = null;
        if(myReport.changeAddress(toChange) != -1){
            fail("Change address to null failed");
        }
        toChange = new String("Test");
        if(myReport.changeAddress(toChange) != 0){
            fail("Valid adress change failed");
        }
        String check = myReport.get(2);
        if(check.equals(toChange) == false){
            fail("Adress does not match");
        }
    }

    @Test
    void changeCity() {
        MemberReport myReport = new MemberReport();
        String toChange = null;
        if(myReport.changeCity(toChange) != -1){
            fail("Change City to null failed");
        }
        toChange = new String("Test");
        if(myReport.changeCity(toChange) != 0){
            fail("Valid city change failed");
        }
        String check = myReport.get(3);
        if(check.equals(toChange) == false){
            fail("City does not match");
        }
    }

    @Test
    void changeState() {
        MemberReport myReport = new MemberReport();
        String toChange = null;
        if(myReport.changeState(toChange) != -1){
            fail("Change state to null failed");
        }
        toChange = new String("Test");
        if(myReport.changeState(toChange) != 0){
            fail("Valid state change failed");
        }
        String check = myReport.get(4);
        if(check.equals(toChange) == false){
            fail("State does not match");
        }
    }

    @Test
    void changeZip() {
        MemberReport myReport = new MemberReport();
        String toChange = null;
        if(myReport.changeZip(toChange) != -1){
            fail("Change zip to null failed");
        }
        toChange = new String("Test");
        if(myReport.changeZip(toChange) != 0){
            fail("Valid zip change failed");
        }
        String check = myReport.get(5);
        if(check.equals(toChange) == false){
            fail("Zip does not match");
        }
    }


    @Test
    void get() {
        Member myMember = new Member();
        String [] components = new String[6];
        buildUser(0, components);
        myMember.build(components);
        MemberReport myReport = new MemberReport();
        myReport.build(myMember);
        for(int i = 0; i < 6; ++i){
            if(components[i].equals(myReport.get(i)) == false){
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