package com.DataClasses;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ProviderReportTest {

    @Test
    void addService() {


    }

    @Test
    void getAll() {


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