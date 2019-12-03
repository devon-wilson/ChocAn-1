package com.Overlord;

import static org.junit.jupiter.api.Assertions.*;

class OverlordTest {

    @org.junit.jupiter.api.Test
    void login() {
        Overlord overlord = new Overlord();


        assertEquals(-1, overlord.login(0, "000000"), "login with null reports failure");
        assertEquals(1, overlord.login(1, "000001"), "login with provider reports 1");
        assertEquals(2, overlord.login(2, "999999"), "login with provider reports 1");
    }

    @org.junit.jupiter.api.Test
    void logout() {
    }

    @org.junit.jupiter.api.Test
    void memberCheckIn() {
    }

    @org.junit.jupiter.api.Test
    void memberCheckOut() {
    }

    @org.junit.jupiter.api.Test
    void addMember() {
    }

    @org.junit.jupiter.api.Test
    void removeMember() {
    }

    @org.junit.jupiter.api.Test
    void suspendMember() {
    }

    @org.junit.jupiter.api.Test
    void renewMember() {
    }

    @org.junit.jupiter.api.Test
    void searchMember() {
    }

    @org.junit.jupiter.api.Test
    void addProvider() {
        Overlord overlord = new Overlord();
        String[] testArray = new String[7];
        for (int i = 0; i < testArray.length; ++i)
            testArray[i] = "";

        assertEquals(-1, overlord.addProvider(testArray), "add member fails on null");
        assertEquals(1, overlord.addProvider(testArray), "add member fails on null");
    }

    @org.junit.jupiter.api.Test
    void removeProvider() {
    }

    @org.junit.jupiter.api.Test
    void searchProvider() {
    }

    @org.junit.jupiter.api.Test
    void addService() {
    }

    @org.junit.jupiter.api.Test
    void removeService() {
    }

    @org.junit.jupiter.api.Test
    void searchService() {
    }

    @org.junit.jupiter.api.Test
    void genMemberReport() {
    }

    @org.junit.jupiter.api.Test
    void genProviderReport() {
    }

    @org.junit.jupiter.api.Test
    void genAllMemberReports() {
    }

    @org.junit.jupiter.api.Test
    void genAllProvidersReports() {
    }

    @org.junit.jupiter.api.Test
    void sendReports() {
    }

    @org.junit.jupiter.api.Test
    void generateServiceRecord() {
    }

    @org.junit.jupiter.api.Test
    void requestDirectory() {
    }

    @org.junit.jupiter.api.Test
    void generateBill() {
    }
}