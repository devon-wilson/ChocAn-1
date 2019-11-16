// used to get input from user
import java.util.Scanner;
// used by CSV Reader for tests
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        /* Main function,
            Will primarily be used for tests
         */

        // Set to false to disable reading EVERY test result
        boolean verbose = true;

        // USER INPUT TEST
        //String userInput = getInput();

        String IDTestFile = "tests/idtests.csv";
        String dateTestFile = "tests/datetests.csv";
        String menuTestFile = "tests/menutests.csv";

        runTest(dateTestFile, "date", verbose);
        runTest(IDTestFile, "ID", verbose);
        runTest(menuTestFile, "menu", verbose);
    }

    private static boolean runTest(String fileName, String testName, boolean verbose) {
        IOAuthorization IO = new IOAuthorization();
        int result = 0;
        testName = testName.toUpperCase();

        try {
            CSVReader reader = new CSVReader(fileName);
            String[] line;
            boolean failed = false;

            System.out.println("\nSTARTING " + testName + " TESTS");
            while ((line = reader.getLine()) != null) {
                int expectedResult = Integer.parseInt(line[1]);

                if (verbose)
                    System.out.print("Testing:\t" + line[0] + "\tExpecting:\t" + expectedResult + "\tResult:\t");

                // Add more cases to add include more tests
                if (testName.equals("ID"))
                    result = IO.validateID(line[0], 8);
                else if (testName.equals("DATE"))
                    result = IO.validateDate(line[0]);
                else if (testName.equals("MENU"))
                    result = IO.validateMenu(line[0], 1);

                if (result == expectedResult) {
                    if (verbose)
                        System.out.println(result + "\tPASSED");
                }
                else {
                    failed = true;
                    if (verbose)
                        System.out.println(result + "\tFAILED");
                }

            }
            // end of while loop

            if (!failed) {
                System.out.println("ALL " + testName + " TESTS PASSED");
                return true;
            }
            else
                System.out.println("SOME " + testName + " TESTS FAILED");
        }
        catch (FileNotFoundException a) {
            System.out.println("Could not open " + fileName + ".");
        }
        return false;
    }

    private static String getInput() {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter an 8 digit user number or q to quit.\n>");
        return keyboard.nextLine();
    }
}
class CSVReader {
    /*
     *  Class to read CSV data from .csv files
     */

    private String fileName;
    private BufferedReader reader;

    CSVReader(String newFile) throws FileNotFoundException {
        /*
         *  Overloaded constructor
         *
         *  Input:
         *  String newFile: file to try to be opened
         *  Throws:
         *  FileNotFoundException: file can not be opened properly
         */

        this.fileName = newFile;
        this.reader = setFile(newFile);
    }

    private BufferedReader setFile(String newFile) throws FileNotFoundException {
        /*
         *  Returns BufferedReader object based on file
         *
         *  Input:
         *  String newFile: file to try to be opened
         *  Throws:
         *  FileNotFoundException: file can not be opened properly
         *  Output:
         *  Returns BufferedReader object pointing to file if opened properly
         */

        return reader = new BufferedReader(new FileReader(newFile));
    }

    String[] getLine() {
        /*
         *  Returns current line in CSV file
         *
         *  Output:
         *  String[]: Returns current line in CSV as array of String objects
         */

        //  Returns String array of current line, already broken up by deliminator ","
        //  In case of readLine() exception
        try {
            //  Read next line, check if eof
            String currentLine;
            if ((currentLine = reader.readLine()) == null)
                return null;

            return currentLine.split(",");
        }
        //  Catch IOException
        catch (IOException a) {
            System.out.println("Could not read line from " + fileName);
            return null;
        }
    }
}
