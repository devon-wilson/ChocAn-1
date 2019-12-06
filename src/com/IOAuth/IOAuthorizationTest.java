package com.IOAuth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class IOAuthorizationTest {

  @Test
  void constructor() {
    IOAuthorization IO = new IOAuthorization();
    assertNotNull(IO, "IOAuth instantiated");
  }

  @Test
  void validateMenu() {
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

  @Test
  void validateDate() {
  }

  @Test
  void validateDateTime() {
  }

  @Test
  void validateTime() {
  }

  @Test
  void validateID() {
  }

  @Test
  void validateTextLength() {
  }
}