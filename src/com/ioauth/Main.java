import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Main function,
           Will primarily be used for tests
         */
        Scanner keyboard = new Scanner(System.in);
        IOAuthorization IO = new IOAuthorization();

        // get user input

        System.out.print("Enter an 8 digit user number or q to quit.\n>");
        String userInput = keyboard.nextLine();

        while(!userInput.equalsIgnoreCase("q")) {

            if (IO.checkInputSize(userInput, 8))
                if (IO.isInputNumber(userInput))
                    System.out.println("Success!");
                else
                    System.out.println("User ID must be a number, no characters.");
            else
                System.out.println("User ID must be 8 digits long.");

            System.out.print("Enter an 8 digit user number or q to quit.\n>");
            userInput = keyboard.nextLine();
        }
    }
}