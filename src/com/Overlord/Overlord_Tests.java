package com.Overlord;// used to get input from user
import java.util.Scanner;
// used by CSV Reader for tests
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Overlord_Tests {
    public static void main(String[] args) {
        String smiley = "pass 😀";
        String frown = "fail ☹";
        boolean verbose = true;

        String loginTestFile = "tests/logintests.csv";

        System.out.println("Overlord tests");
        Overlord overlord = new Overlord();
        int rc = overlord.login(0, "0");
        System.out.println("Login test: " + (rc > 1 ? smiley : frown));
        // Set to false to disable reading EVERY test result

        // USER INPUT TEST
        //String userInput = getInput();


        runTest(loginTestFile, "LOGIN", verbose);
    }

    private static boolean runTest(String fileName, String testName, boolean verbose) {
        Overlord overlord = new Overlord();
        int result;
        testName = testName.toUpperCase();

        try {
            CSVReader reader = new CSVReader(fileName);
            String[] line;
            boolean failed = false;

            System.out.println("\nSTARTING " + testName + " TESTS");
            int testNum = 1;
            while ((line = reader.getLine()) != null) {
                int expectedResult = Integer.parseInt(line[1]);

                System.out.print("Test" + testNum + ": \t" + line[0]);
                if (verbose)
                    System.out.print("\tExpecting:\t" + expectedResult + "\tResult:\t");

                // Add more cases to add include more tests
                switch (testName) {
                    case "LOGIN":
                        result = overlord.login(1, line[0]);
                        break;
                    default:
                        return false;
                }

                if (result == expectedResult) {
                    System.out.println(result + "\tPASSED");
                }
                else {
                    failed = true;
                    System.out.println(result + "\tFAILED");
                }
                testNum++;
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