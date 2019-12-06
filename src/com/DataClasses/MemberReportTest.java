package com.DataClasses;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MemberReportTest {

    @Test
    void addService() {
        MemberReport myReport = new MemberReport();
        String service [] = null;
        if(myReport.addService(service) != -1){
            fail("add service null failed");
        }
        Member myMember = new Member();
        String components [] = new String[6];
        service = new String[3];
        buildUser(0, components);
        myReport.build(myMember);
        buildService(0, service);
        if(myReport.addService(service) != 0){
            fail("Build with valid failed.");
        }
        buildService(1, service);
        if(myReport.addService(service) != 0){
            fail("Build with valid second time failed.");
        }


    }

    @Test
    void getAll() {

        MemberReport myReport = new MemberReport();
        String service [] = null;
        if(myReport.getAll() != null){
            fail("get all empty failed.");
        }

        Member myMember = new Member();
        String components [] = null;
        service = new String[3];
        components = new String[6];
        buildUser(0, components);
        myReport.build(myMember);
        buildService(0, service);
        myReport.addService(service);
        buildService(1, service);
        myReport.addService(service);

        components = myReport.getAll();
        String second = service[0] + "," + service[1] + "," + service[2];
        if(second.equals(components[2])){
            fail("get all failed right info.");
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

    private int buildService(int line, String [] components){
        file = new File("data/providerDirectory.csv");
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
        for(int i =0; i < line + 1; ++i){
            sc.nextLine();
        }
        for(int i = 0; i < 3; ++i){
            components[i] = sc.next();
        }
        return 0;
    }

    private File file;
    private Scanner sc;
}