import java.util.Scanner;

public class Main {

    private static String getInput() {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter an 8 digit user number or q to quit.\n>");
        return keyboard.nextLine();
    }

    public static void main(String[] args) {
        /* Main function,
           Will primarily be used for tests
         */
        IOAuthorization IO = new IOAuthorization();

        // get user input
        String userInput = getInput();
        while (!userInput.equalsIgnoreCase("q")) {

            int ret = IO.validateUserID(userInput);

            // Passes all tests
            if (ret == 0)
                System.out.println("Success!");
            // Incorrect input size
            else if (ret == 1)
                System.out.println("Input is not the expected character size.");
            // Check if input is a type
            else if (ret == 2)
                System.out.println("Input has characters that aren't numbers");

            userInput = getInput();
        }
    }
}