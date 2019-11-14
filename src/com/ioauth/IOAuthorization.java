public class IOAuthorization {

    // check if input is size of expected length
    public boolean checkInputSize(String input, int expected) {
        return input.length() == expected;
    }

    public boolean isInputNumber(String input) {
        return this.isType(input, "int");
    }

	public Boolean isType(String testStr, String type) {
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
