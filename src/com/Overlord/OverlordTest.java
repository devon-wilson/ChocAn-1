package com.Overlord;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class OverlordTest {

  @org.junit.jupiter.api.Test
  void login() {
    Overlord overlord = new Overlord();
    String[] line;

    try {
      CSVReader reader = new CSVReader("tests/loginTests.csv");

      while ((line = reader.getLine()) != null) {
        assertEquals(Integer.parseInt(line[0]), overlord.login(Integer.parseInt(line[1]),line[2]), line[3]);
      }
      //assertEquals(-1, overlord.login(0, "000000"), "login with null reports failure");
      //assertEquals(1, overlord.login(1, "000001"), "login with provider reports 1");
      //assertEquals(2, overlord.login(2, "999999"), "login with provider reports 1");

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


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
    Arrays.fill(testArray, "");

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

class CSVReader {
  /*
   *  Class to read CSV data from .csv files
   */

  private String fileName;
  private BufferedReader reader;

  CSVReader(String newFile) throws FileNotFoundException {
    /*
     *  Overloaded constructor
     *
     *  Input:
     *  String newFile: file to try to be opened
     *  Throws:
     *  FileNotFoundException: file can not be opened properly
     */

    this.fileName = newFile;
    this.reader = setFile(newFile);
  }

  private BufferedReader setFile(String newFile) throws FileNotFoundException {
    /*
     *  Returns BufferedReader object based on file
     *
     *  Input:
     *  String newFile: file to try to be opened
     *  Throws:
     *  FileNotFoundException: file can not be opened properly
     *  Output:
     *  Returns BufferedReader object pointing to file if opened properly
     */

    return reader = new BufferedReader(new FileReader(newFile));
  }

  String[] getLine() {
    /*
     *  Returns current line in CSV file
     *
     *  Output:
     *  String[]: Returns current line in CSV as array of String objects
     */

    //  Returns String array of current line, already broken up by deliminator ","
    //  In case of readLine() exception
    try {
      //  Read next line, check if eof
      String currentLine;
      if ((currentLine = reader.readLine()) == null)
        return null;

      return currentLine.split(",");
    }
    //  Catch IOException
    catch (IOException a) {
      System.out.println("Could not read line from " + fileName);
      return null;
    }
  }
}