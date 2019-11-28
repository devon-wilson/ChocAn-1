package com.DataClasses;

public class Main {
    public static void main (String[] args) {
        UnitTests myTest = new UnitTests();
        int success = myTest.testDataClasses();
        if(success == 0){
            System.err.println("all tests passed");
        }
        else{
            System.err.println("tests failed");
        }
    }
}