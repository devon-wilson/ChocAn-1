package com.Overlord;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class OverlordTest {
  @Test
  void InstantiateOverlordTest() {
    System.out.println("Testing: instantiating overlord");

    Overlord overlord = new Overlord();
  }

  @ParameterizedTest(name = "{3}")
  @CsvFileSource(resources = "tests/loginTests.csv")
  void login(int type, String id, int expected, String desc) {
    Overlord overlord = new Overlord();
    System.out.println("Testing: " + desc);
    System.out.println("type: " + type + "\nid: " + id);
    assertEquals(expected, overlord.login(type, id), desc);
  }

  @Test
  void logout() {
    Overlord overlord = new Overlord();
    System.out.println("Testing: logging out");


    overlord.login(0, "012345678");
    assertEquals(1,overlord.logout(), "log out returns 1");
  }

  @ParameterizedTest(name = "{2}")
  @CsvFileSource(resources = "tests/memberCheckInTests.csv")
  void memberCheckIn(String id, int expected, String message) {
    Overlord overlord = new Overlord();
    System.out.println("Testing: " + message + " with no provider login");

    assertEquals(-2, overlord.memberCheckIn(id), message + " with no provider login");

    System.out.println("Testing: " + message + " with provider login");
    overlord.login(0, "012345678");

    assertEquals(expected, overlord.memberCheckIn(id), message);
  }

  @Test
  void viewMember() {
    Overlord overlord = new Overlord();

    assertEquals(-2, overlord.viewMember(), "no member checked in");

    //overlord.login(0, "012345678");
    //overlord.memberCheckIn("012345678");

    //assertEquals(1, overlord.viewMember(), "member checked in displays");
  }


  @Test
  void memberCheckOut() {
    Overlord overlord = new Overlord();

    assertEquals(-1, overlord.memberCheckOut(), "no member checked in");

    overlord.login(0, "012345678");
    overlord.memberCheckIn("012345678");

    assertEquals(1, overlord.memberCheckOut(), "member checked in checks out");
  }

  @Test
  @DisplayName("Add, Remove, Member")
  void addRemoveMember() {
    Overlord overlord = new Overlord();
    String[] newMember = {"Jane Doe","321321321","3415 54th Street","Portland","OR","97320","Valid"};
    assertEquals(7, newMember.length);

    assertEquals("Val"+"id", newMember[6], "valid");

    assertEquals(-2, overlord.addMember(newMember), "add member without manager");

    overlord.login(1, "012345678");

    assertEquals(1, overlord.addMember(newMember), "add member without manager");

    assertEquals(-1, overlord.removeMember(newMember[1]), "add member without manager");

  }

  @Test
  void suspendMember() {
    Overlord overlord = new Overlord();

    assertEquals(-2, overlord.suspendMember("012345678"), "not manager returns -2");

    overlord.login(1, "012345678");

    assertEquals(-1, overlord.suspendMember("012345678"));
    assertEquals(-1, overlord.renewMember("012345678"));
  }

  @ParameterizedTest(name = "{2}")
  @CsvFileSource(resources = "tests/searchMemberTests.csv")
  void searchMember(boolean expectedNotNull, String query, String message) {
    Overlord overlord = new Overlord();
    //assertEquals(null, overlord.searchMember("the one that got away"), "search for the one that got away returns null");

    //Object member = overlord.searchMember("John");
    //assertNotNull(member, "search for John");

    Object member = overlord.searchMember(query);
    if (expectedNotNull) {
      assertNotNull(member, message);
    } else {
      assertNull(member, message);
    }
  }

  @Test
  void addRemoveProvider() {
    Overlord overlord = new Overlord();
    String[] testArray = new String[7];
    //Arrays.fill(testArray, "");

    //assertEquals(-1, overlord.addProvider(testArray), "add member fails on null");
    //assertEquals(1, overlord.addProvider(testArray), "add member fails on null");
  }

  @Test
  void searchProvider() {
    Overlord overlord = new Overlord();
  }

  @Test
  void displayCurrentServices() {
    Overlord overlord = new Overlord();

    assertEquals(-2, overlord.displayCurrentServices(), "no member checked in");

    overlord.login(0, "012345678");
    overlord.memberCheckIn("012345678");

    assertEquals(1, overlord.displayCurrentServices(), "member checked in displays");
  }

  @Test
  void addRemoveService() {
    Overlord overlord = new Overlord();
  }

  @Test
  void searchService() {
    Overlord overlord = new Overlord();
  }

  @Test
  void genMemberReport() {
    Overlord overlord = new Overlord();
  }

  @Test
  void genProviderReport() {
    Overlord overlord = new Overlord();
  }

  @Test
  void genAllMemberReports() {
    Overlord overlord = new Overlord();
  }

  @Test
  void genAllProvidersReports() {
    Overlord overlord = new Overlord();
  }

  @Test
  void sendReports() {
    Overlord overlord = new Overlord();
  }

  @Test
  void generateServiceRecord() {
    Overlord overlord = new Overlord();
  }

  @Test
  void requestDirectory() {
    Overlord overlord = new Overlord();
  }

  @Test
  void generateBill() {
    Overlord overlord = new Overlord();
  }

}

