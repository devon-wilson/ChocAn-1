package com.Overlord;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class OverlordTest {

  @ParameterizedTest(name = "{3}")
  @CsvFileSource(resources = "tests/logintests.csv")
  void login(int rc, int type, String id, String desc) {
    Overlord overlord = new Overlord();
    System.out.println("Testing: " + desc);
    assertEquals(rc, overlord.login(type, id), desc);
  }

  @Test
  void logout() {
  }

  @Test
  void memberCheckIn() {
  }

  @Test
  void memberCheckOut() {
  }

  @Test
  void addMember() {
  }

  @Test
  void removeMember() {
  }

  @Test
  void suspendMember() {
  }

  @Test
  void renewMember() {
  }

  @Test
  void searchMember() {
  }

  @Test
  void addProvider() {
    Overlord overlord = new Overlord();
    String[] testArray = new String[7];
    Arrays.fill(testArray, "");

    assertEquals(-1, overlord.addProvider(testArray), "add member fails on null");
    assertEquals(1, overlord.addProvider(testArray), "add member fails on null");
  }

  @Test
  void removeProvider() {
  }

  @Test
  void searchProvider() {
  }

  @Test
  void addService() {
  }

  @Test
  void removeService() {
  }

  @Test
  void searchService() {
  }

  @Test
  void genMemberReport() {
  }

  @Test
  void genProviderReport() {
  }

  @Test
  void genAllMemberReports() {
  }

  @Test
  void genAllProvidersReports() {
  }

  @Test
  void sendReports() {
  }

  @Test
  void generateServiceRecord() {
  }

  @Test
  void requestDirectory() {
  }

  @Test
  void generateBill() {
  }
}

