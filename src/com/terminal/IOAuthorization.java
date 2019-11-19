package com.terminal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class IOAuthorization {

    // Validates menu menu. Menus shouldn't be longer than 2 digits
	int validateMenu(String input, int maxMenuSize) {

        if (!isCorrectSize(input, maxMenuSize))
        	return -1;
        if (!isType(input, "int"))
        	return -1;

		// should be an int, okay to cast
		int menu = Integer.parseInt(input);
        if (isNegative(menu))
        	return -1;
        if (menu == 0)
        	return -1;

        // all tests passed
		return 0;
    }

	// Verify that date entered is in correct format
	// Expects MM/DD/YEAR
	int validateDate(String input) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		format.setLenient(false);
		try {
			Date inputDate = format.parse(input);
			return 0;
		}
		catch (ParseException a) {
			return -1;
		}
	}

	// Verify that time entered is in correct format
	int validateTime(String input) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		format.setLenient(false);
		try {
			Date inputTime = format.parse(input);
			return 0;
		}
		catch (ParseException a) {
			return -1;
		}
	}

    // check if input is size of expected length
	int validateID(String input, int expectedLength) {

    	// Check if input is correct size
		if(!isCorrectSize(input, expectedLength))
			return -1;

		// Check if input is an int
		if(!isType(input, "int"))
			return -1;

		// should be an int, okay to cast
		int ID = Integer.parseInt(input);

		if(isNegative(ID))
			return -1;

		// If it reached this far, success!
		return 0;
	}

	int validateTextLength(String input, int maxLength) {
	    if (input.length() > maxLength)
	    	return -1;
	    return 0;
	}

    private boolean isCorrectSize(String input, int expectedLength) { return input.length() == expectedLength; }

    private boolean isNegative(int input) { return input < 0; }

	private boolean isType(String testStr, String type) {
		try {
			if (type.equalsIgnoreCase("int")) {
				Integer.parseInt(testStr);
			} else if (type.equalsIgnoreCase("double")) {
				Double.parseDouble(testStr);
			}
			return true;
		} catch (NumberFormatException a) {
			return false;
		}
	}
}