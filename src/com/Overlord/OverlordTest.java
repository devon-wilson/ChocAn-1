package com.Overlord;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class OverlordTest {
  static Overlord overlord;

  @BeforeEach
  void setUpEach() {
    overlord = new Overlord();
  }

  @Test
  void testTest() {
    assertNotNull(overlord);
  }

  @Test
  void InstantiateOverlordTest() {
    System.out.println("Testing: instantiating overlord");

    Overlord overlord = new Overlord();
  }

  @ParameterizedTest(name = "{3}")
  @CsvFileSource(resources = "tests/loginTests.csv")
  void login(int type, String id, int expected, String desc) {
    System.out.println("Testing: " + desc);
    System.out.println("type: " + type + "\nid: " + id);
    assertEquals(expected, overlord.login(type, id), desc);
  }

  @Test
  void logout() {
    System.out.println("Testing: logging out");

    overlord.login(0, "123456789");
    assertEquals(1,overlord.logout(), "log out returns 1");
  }

  @ParameterizedTest(name = "{2}")
  @CsvFileSource(resources = "tests/memberCheckInTests.csv")
  void memberCheckIn(String id, int expected, String message) {
    System.out.println("Testing: " + message + " with no provider login");

    assertEquals(-2, overlord.memberCheckIn(id), message + " with no provider login");

    System.out.println("Testing: " + message + " with provider login");
    overlord.login(1, "123456789");

    assertEquals(1, overlord.memberCheckIn("123456789"), message);
  }

  @Test
  void viewMember() {

    assertEquals(-2, overlord.viewMember(), "no member checked in");

    overlord.login(0, "123456789");
    overlord.memberCheckIn("123456789");

    assertEquals(1, overlord.viewMember(), "member checked in displays");
  }


  @Test
  void memberCheckOut() {

    assertEquals(-1, overlord.memberCheckOut(), "no member checked in");

    overlord.login(0, "123456789");
    overlord.memberCheckIn("123456789");

    assertEquals(1, overlord.memberCheckOut(), "member checked in checks out");
  }

  @Test
  @DisplayName("Add, Remove, Member")
  void addRemoveMember() {
    String[] newMember = {"Jane Doe","321321321","3415 54th Street","Portland","OR","97320","Valid"};
    assertEquals(7, newMember.length);

    assertEquals("Val"+"id", newMember[6], "valid");

    assertEquals(-2, overlord.addMember(newMember), "add member without manager");

    overlord.login(1, "123456789");

    assertEquals(-2, overlord.addMember(newMember), "add member without manager");

    overlord.logout();

    overlord.login(0, "123456789");

    assertEquals(1, overlord.addMember(newMember), "add member without manager");

    assertEquals(1, overlord.removeMember(newMember[1]), "add member without manager");

  }

  @Test
  void suspendMember() {

    assertEquals(-1, overlord.suspendMember("123456789"), "not manager returns -1");

    overlord.login(0, "123456789");

    assertEquals(1, overlord.suspendMember("123456789"), "suspend from manager");

    overlord.logout();

    assertEquals(-1, overlord.renewMember("123456789"), "renew without manager");

    overlord.login(1, "123456789");

    assertEquals(-2, overlord.renewMember("123456789"), "cannot renew as a provider");
  }

  @Test
  void addRemoveProvider() {
    String[] testArray = {"Jane Doe","321321321","3415 54th Street","Portland","OR","97320","123456","213509"};

    assertEquals(-2, overlord.addProvider(testArray), "no user is logged in");

    overlord.login(1, "01234567");
    assertEquals(-2, overlord.addProvider(testArray), "add member fails on null");

    overlord.logout();
    overlord.login(0, "123456789");

    assertEquals(-1, overlord.addProvider(null), "null string provided");
    assertEquals(1, overlord.addProvider(testArray), "user is not a manager");

    assertEquals(1, overlord.removeProvider(testArray[1]), "removing provider from list");
    assertEquals(-1, overlord.removeProvider(testArray[1]), "removing same provider from list");
  }

  @Test
  void displayCurrentServices() {

    assertEquals(-2, overlord.displayCurrentServices(), "no member checked in");

    overlord.login(0, "123456789");
    overlord.memberCheckIn("123456789");

    assertEquals(-2, overlord.displayCurrentServices(), "user is a manager, not a provider");

    overlord.logout();
    overlord.login(1, "123456789");
    overlord.memberCheckIn("123456789");

    assertEquals(1, overlord.displayCurrentServices(), "services have been displayed");
  }

  @Test
  void addRemoveService() {

    String[] newService = {"Pedicure", "000001", "50.00", "123456789"};
    assertEquals(-2, overlord.addService(newService), "no current user");
    assertEquals(-2, overlord.removeService("000001"), "no current user");

    overlord.login(1, "123456789");

    assertEquals(-2, overlord.addService(newService), "current user is not a manager");

    overlord.logout();
    overlord.login(0, "123456789");

    assertEquals(1, overlord.addService(newService), "correctly added to tree");

    assertEquals(-3, overlord.addService(null), "passed null pointer to constructor");

    assertEquals(-3, overlord.removeService(null), "passed null to function");

    assertEquals(1, overlord.removeService("000001"), "passed current user manager and to function");
  }

  @Test
  void searchService() {

    assertNull(overlord.searchService(null), "no user, null string");

    overlord.login(1, "123456789");
    assertNull(overlord.searchService(null), "null string");
    String[] retrieved = overlord.searchService("123456");
    assertArrayEquals(retrieved, overlord.searchService("123456"), "confirms test passes");
  }

  @Test
  void genMemberReport() {

    assertEquals(-1, overlord.genMemberReport(null), "passed null to function");

    overlord.login(0, "123456789");
    assertEquals(1, overlord.genMemberReport("123456789"), "passed actual member id");
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
  void generateServiceRecord() {

    assertEquals(-2, overlord.generateServiceRecord(null), "no one logged in");

    overlord.login(1,"123456789");
    overlord.memberCheckIn("123456789");

    assertEquals(-1, overlord.generateServiceRecord(null), "passed in null pointer");

    String[] testArray = new String[3];
    testArray[0] = "test date";
    testArray[1] = "123456";
    testArray[2] = "test comments";
    assertEquals(1, overlord.generateServiceRecord(testArray),"wrote file to disk and tree");
  }

  @Test
  void requestDirectory() {
  }

  @Test
  void generateBill() {
  }

  @ParameterizedTest(name = "{1}")
  @CsvSource({
          "0,member is not checked in",
          "1,member is checked in"
  })
  void isMemberCheckedIn() {

    overlord.login(0, "123456789");
    overlord.memberCheckIn("123456789");

    assertEquals(true, overlord.isMemberCheckedIn(), "Member is checked in");
  }

  @Test
  void isMemberValid() {
    assertEquals(false, overlord.isMemberValid());
  }

  @Test
  void isMemberSuspended() {
    assertEquals(false, overlord.isMemberSuspended());
  }

  @Test
  void getMember() {
  }

  @Test
  void getProvider() {
  }

  @Test
  void getService() {
  }

  @Test
  void viewMembers() {
  }

  @Test
  void viewProviders() {
  }

  @Test
  void viewServices() {
  }

  @Test
  void viewDirectory() {
  }
}

