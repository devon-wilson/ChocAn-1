import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /* Main function,
           Will primarily be used for tests
         */

        Scanner keyboard = new Scanner(System.in);
        IOAuthorization IO = new IOAuthorization();

        // get user input
        System.out.print("Enter an 8 digit user number\n-> ");
        String userInput = keyboard.nextLine();

        if(IO.checkInputSize(userInput, 8))
            System.out.println("Success!");
        else
            System.out.println("Failure");
    }
}
