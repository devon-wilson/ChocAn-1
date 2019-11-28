package com.DataClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class UnitTests {

    private File file;
    private Scanner sc;

    public UnitTests(){
        file = null;
        sc = null;
    }

    public String [] buildUser(){
        file = new File("data/users.csv");
        if(file == null){
            return null;
        }
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        sc.useDelimiter(",");
        String [] components = new String [6];
        for(int i = 0; i < 6; ++i){
            components[i] = sc.next();
        }
        return components;
    }
    public String [] buildMService(){
        file = new File("data/Mservices.csv");
        if(file == null){
            return null;
        }
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        sc.useDelimiter(",");
        String [] components = new String [3];
        for(int i = 0; i < 3; ++i){
            components[i] = sc.next();
        }
        return components;
    }
    public String [] buildPService(){
        file = new File("data/Pservices.csv");
        if(file == null){
            return null;
        }
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        sc.useDelimiter(",");
        String [] components = new String [6];
        for(int i = 0; i < 6; ++i){
            components[i] = sc.next();
        }
        return components;
    }

    public int testDataClasses(){
        int success = 0;
        String [] components = buildUser();
        if(components == null){
            return -1;
        }
        Member testMember = new Member();
        success += testMember.build(components);
        //success += testMember.display();
        success += testUser(testMember, components);

        Provider testProvider = new Provider();
        success += testProvider.build(components);
        //success += testProvider.display();
        success += testUser(testProvider, components);

        Manager testManager = new Manager();
        success += testManager.build(components);
        //success += testManager.display();
        success += testUser(testManager, components);

        testManager.build(components);
        success += testPReport(testManager, components);
        success += testMReport(testManager, components);

        return success;
    }
    public int testUser(User toTest, String [] original){
        int success = 0;
        success += testAllUser(toTest, original);
        success += getTestUser(toTest, original);
        success += changeTestUser(toTest);
        return success;
    }
    public int testPReport(User toTest, String [] original){
        int success = 0;
        ProviderReport test = new ProviderReport();
        test.build(toTest);

        String [] service = buildPService();
        success += test.addService(service);
        success += test.addService(service);
        success += testPRServices(test, service);

        success += testAllReport(test, original);
        success += getTestReport(test, original);
        success += changeTestReport(test);
        return success;
    }
    public int testMReport(User toTest, String [] original){
        int success = 0;
        MemberReport test = new MemberReport();
        test.build(toTest);

        String [] service = buildMService();
        success += test.addService(service);
        success += test.addService(service);
        success += testMRServices(test, service);

        success += testAllReport(test, original);
        success += getTestReport(test, original);
        success += changeTestReport(test);
        return success;
    }

    public int testAllUser(User toTest, String [] original){
        String [] components = toTest.getAll();
        int success = 0;
        for(int i = 0; i < 6; ++i){
            if(components[i].equals(original[i]) == false){
                success = -1;
            }
        }
        if(success == 0){
            System.err.println("Get all test Passed.");
        }
        else{
            System.err.println("Get all test failed");
        }
        return success;
    }
    public int getTestUser(User toTest, String [] original){
        int success = 0;
        String toGet;
        for(int i = 0; i < 6; ++i){
            toGet = toTest.get(i);
            if(toGet.equals(original[i]) == false){
                success = -1;
            }
        }
        if(success == 0){
            System.err.println("Get individual passed");
        }
        else{
            System.err.println("Get individual failed");
        }
        return success;
    }
    public int changeTestUser(User toTest){
        String change = "changed";
        int success = 0;
        success += toTest.changeName(change);
        success += toTest.changeAddress(change);
        success += toTest.changeCity(change);
        success += toTest.changeID(change);
        success += toTest.changeState(change);
        success += toTest.changeZip(change);

        for(int i = 0; i < 6; ++i){
            if(toTest.get(i).equals(change) == false){
                success = -1;
            }
        }
        if(success == 0){
            System.err.println("Change field passed");
        }
        else{
            System.err.println("Change field failed.");
        }

        return success;
    }

    public int testAllReport(Report toTest, String [] original){
        String [] components = toTest.getAll();
        int success = 0;
        for(int i = 0; i < 6; ++i){
            if(components[i].equals(original[i]) == false){
                success = -1;
            }
        }
        if(success == 0){
            System.err.println("Get all test Passed.");
        }
        else{
            System.err.println("Get all test failed");
        }
        return success;
    }
    public int getTestReport(Report toTest, String [] original){
        int success = 0;
        String toGet;
        for(int i = 0; i < 6; ++i){
            toGet = toTest.get(i);
            if(toGet.equals(original[i]) == false){
                success = -1;
            }
        }
        if(success == 0){
            System.err.println("Get individual passed");
        }
        else{
            System.err.println("Get individual failed");
        }
        return success;
    }
    public int changeTestReport(Report toTest){
        String change = "changed";
        int success = 0;
        success *= toTest.changeName(change);
        success *= toTest.changeAddress(change);
        success *= toTest.changeCity(change);
        success *= toTest.changeID(change);
        success *= toTest.changeState(change);
        success *= toTest.changeZip(change);

        for(int i = 0; i < 6; ++i){
            if(toTest.get(i).equals(change) == false){
                success = -1;
            }
        }
        if(success == 0){
            System.err.println("Change field passed");
        }
        else{
            System.err.println("Change field failed.");
        }

        return success;
    }

    public int testPRServices(ProviderReport toTest, String [] original){
        int success = 0;
        Vector<String[]> services = toTest.getServices();
        int number = toTest.getNumber();
        float fee = toTest.getFee();

        int size = services.size();
        if(number != size){
            success = -1;
        }
        if(fee != 100.0){
            success = -1;
        }

        for (int i = 0; i < size; ++i) {
            String[] service = services.get(i);
            for (int j = 0; j < 6; ++j) {
                if(service[j].equals(original[j]) == false){
                    success = -1;
                }
            }
        }
        return success;
    }
    public int testMRServices(MemberReport toTest, String [] original){
        int success = 0;
        Vector<String[]> services = toTest.getServices();
        int size = services.size();

        for (int i = 0; i < size; ++i) {
            String[] service = services.get(i);
            for (int j = 0; j < 3; ++j) {
                if(service[j].equals(original[j]) == false){
                    success = -1;
                }
            }
        }
        return success;
    }
}
