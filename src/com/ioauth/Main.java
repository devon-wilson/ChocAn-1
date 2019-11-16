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

        // USER INPUT TEST
        //String userInput = getInput();

        String fileName = "tests/ioauthtests.csv";
        try {
            CSVReader reader = new CSVReader(fileName);
            String[] line;
            boolean failed = false;
            while ((line = reader.getLine()) != null) {
                int expectedResult = Integer.parseInt(line[1]);
                if (!testValidateID(line[0], expectedResult)) {
                    System.out.print("FAILED\n");
                    failed = true;
                }
                else
                    System.out.print("PASSED\n");
            }
            if (!failed)
                System.out.println("ALL TESTS PASSED");
        }
        catch (FileNotFoundException a) {
            System.out.println("Could not open " + fileName + ".");
        }

    }

    private static String getInput() {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter an 8 digit user number or q to quit.\n>");
        return keyboard.nextLine();
    }

    private static boolean testValidateID(String input, int expectedResult) {
        // Test User ID input. All user IDs should be 8 digits long
        IOAuthorization IO = new IOAuthorization();
        int result = IO.validateID(input, 8);
        System.out.print("Tested " + input + " expected " + expectedResult + "\t");
        if (result == expectedResult)
            return true;
        else
            return false;
    }
}
class CSVReader {
    /**
     *  Class to read CSV data from .csv files
     */

    private String fileName;
    private BufferedReader reader;
    private String currentLine;

    public CSVReader(String newFile) throws FileNotFoundException {
        /**
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
        /**
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

    public String[] getLine() {
        /**
         *  Returns current line in CSV file
         *
         *  Output:
         *  String[]: Returns current line in CSV as array of String objects
         */

        //  Returns String array of current line, already broken up by deliminator ","
        //  In case of readLine() exception
        try {
            //  Read next line, check if eof
            if ((currentLine = reader.readLine()) == null)
                return null;

            String[] lineArr = currentLine.split(",");
            return lineArr;
        }
        //  Catch IOException
        catch (IOException a) {
            System.out.println("Could not read line from " + fileName);
            return null;
        }
    }
}
