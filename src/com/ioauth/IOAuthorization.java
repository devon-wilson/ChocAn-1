public class IOAuthorization {

    // check if input is size of expected length
    public int validateUserID(String input) {

    	// Check if input is correct size
		if(!checkInputSize(input, 8))
			return 1;

		// Check if input is an int
		if(!isInputNumber(input))
			return 2;

		// If it reached this far, success!
		return 0;
	}

    private boolean checkInputSize(String input, int expected) {
        return input.length() == expected;
    }

    private boolean isInputNumber(String input) {
        return this.isType(input, "int");
    }

	private Boolean isType(String testStr, String type) {
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