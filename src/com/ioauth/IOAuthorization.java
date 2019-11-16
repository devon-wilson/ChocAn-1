public class IOAuthorization {

	// Verify that date entered is in correct format
	// Expects MM/DD/YEAR
	public int validateDate(String input) {
	    //TODO: Actually implement this function
		return 0;
	}

    // check if input is size of expected length
    public int validateID(String input, int expectedLength) {

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

    private boolean isCorrectSize(String input, int expectedLength) {
        return input.length() == expectedLength;
    }

    private boolean isNegative(int input) { return input < 0; }

	private boolean isType(String testStr, String type) {
		try {
			if (type.equalsIgnoreCase("int")) {
				Integer.parseInt(testStr);
			} else if (type.equalsIgnoreCase("double")) {
				Double.parseDouble(testStr);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}