package com.IOAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOAuthorization {
		final protected SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		final protected SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    // Validates menu choice. Menus shouldn't be longer than 2 digits
		protected int validateMenu(String input, int maxMenuSize) {

        if (isNotType(input, "int"))
        	return -1;

		// should be an int, okay to cast
		int menu = Integer.parseInt(input);

		if (menu <= 0 || menu > maxMenuSize)
			return -1;

        // all tests passed
		return 0;
    }

	/**
	 * Verify that dollars entered is in correct format
	 * @param input String to test containing currency
	 * @return 0 for success, -1 for failure
	 */
	protected int validateCurrency(String input, int maxLength) {
		double maxValue;

		if (isNotType(input, "double") || input.length() > maxLength || maxLength > 311)
			return -1;

		maxValue = Math.pow(10, maxLength - 3);

		// should be an int, okay to cast
		double inputCurrency = Double.parseDouble(input);

		if (inputCurrency < 0 || inputCurrency >= maxValue)
			return -1;

		// all tests passed
		return 0;
	}

	/**
   * Verify that date entered is in correct format
	 * <p>
	 *   Expects MM-DD-YYYY
	 * </p>
	 * @param input String to test containing date
	 * @return 0 for success, -1 for failure
	 */
	protected int validateDate(String input) {
		dateFormat.setLenient(false);
		try {
			Date inputDate = dateFormat.parse(input);
			return 0;
		}
		catch (ParseException a) {
			return -1;
		}
	}

	/**
	 * Verify that date and time entered is in correct format
	 * <p>
	 *   Expects MM-DD-YYYY HH:MM:SS
	 * </p>
	 * @param input String to test
	 * @return 0 for success, -1 for failure
	 */
	protected int validateDateTime(String input) {
		dateTimeFormat.setLenient(false);
		try {
			Date inputDate = dateTimeFormat.parse(input);
			return 0;
		}
		catch (ParseException a) {
			return -1;
		}
	}

	/**
	 * Verify that time entered is in correct format
	 * @param input String to test containing a time
	 * @return 0 for success, -1 for failure
	 */
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
	protected int validateID(String input, int expectedLength) {

    	// Check if input is correct size
		if(!isCorrectSize(input, expectedLength))
			return -1;

		// Check if input is an int
		if(isNotType(input, "int"))
			return -1;

		// should be an int, okay to cast
		int ID = Integer.parseInt(input);

		if(isNegative(ID))
			return -1;

		// If it reached this far, success!
		return 0;
	}

	// Validates most input. Most input expects a certain length of characters
	// See requirements document for specific lengths of specific fields
	protected int validateTextLength(String input, int maxLength) {
	    if (input.length() > maxLength)
	    	return -1;
	    return 0;
	}

    private boolean isCorrectSize(String input, int expectedLength) { return input.length() == expectedLength; }

    private boolean isNegative(int input) { return input < 0; }

	private boolean isNotType(String testStr, String type) {
		try {
			if (type.equalsIgnoreCase("int")) {
				Integer.parseInt(testStr);
			} else if (type.equalsIgnoreCase("double")) {
				Double.parseDouble(testStr);
			}
			return false;
		} catch (NumberFormatException a) {
			return true;
		}
	}
}