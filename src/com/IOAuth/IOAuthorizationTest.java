package com.IOAuth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class IOAuthorizationTest {

  @Test
  void constructor() {
    IOAuthorization IO = new IOAuthorization();
    assertNotNull(IO, "IOAuth instantiated");
  }

  @ParameterizedTest(name = "Input string: {0} expecting: {1}")
  @CsvFileSource(resources =  "tests/menutests.csv")
  void validateMenu(String input, int expected) {
    IOAuthorization IO = new IOAuthorization();
    assertEquals(expected, IO.validateMenu(input, 8), String.format("%s expects %d", input, expected));
  }

  @ParameterizedTest(name = "{3}")
  @CsvSource({
          "-1,6,-1,negative",
          "0,6,0,zero",
          "1,6,0,one",
          "999.99,6,0,below 1000",
          "1000,6,-1,1000",
          "9999.99,6,-1,below 10000",
          "99999.99,8,0,99999.99 is too long",
          "100000,8,-1,10000",
          "999999.99,8,-1,999999.99 is too long",
  })
  void validateCurrency(String input, int maxLength, int expected, String message) {
    IOAuthorization IO = new IOAuthorization();
    assertEquals(expected, IO.validateCurrency(input, maxLength), message);
  }

  @ParameterizedTest(name = "Input date string: {0} expecting: {1}")
  @CsvFileSource(resources =  "tests/datetests.csv")
  void validateDate(String input, int expected) {
    IOAuthorization IO = new IOAuthorization();
    assertEquals(expected, IO.validateDate(input));
  }

  @Test
  void validateDateTime() {
    IOAuthorization IO = new IOAuthorization();
  }

  @ParameterizedTest(name = "Input tie string: {0} expecting: {1}")
  @CsvFileSource(resources =  "tests/timetests.csv")
  void validateTime() {
    IOAuthorization IO = new IOAuthorization();
  }


  @ParameterizedTest(name = "Input ID: {0} expecting: {1}")
  @CsvSource({
          "12345678,0",
          "01245352,0",
          "0.012345,-1",
          "123.1234,-1",
          "01234,-1",
          "-12345678,-1",
          "-1234567,-1",
          "texttest,-1",
          "test,-1",
          "!@#$%^&*,-1",
          "!!!!!!!!,-1",
          ".      .,-1",
  })
  void validateID(String input, int expected) {
    IOAuthorization IO = new IOAuthorization();

    assertEquals(expected, IO.validateID(input, 8));

  }

  @ParameterizedTest(name = "Input string: {0} expecting: {1}")
  @CsvFileSource(resources =  "tests/commenttests.csv")
  void validateTextLength(String input, int expected) {
    IOAuthorization IO = new IOAuthorization();
    assertEquals(expected, IO.validateTextLength(input, 100));
  }
}