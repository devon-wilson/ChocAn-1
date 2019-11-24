import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Overlord_Tests {
    public static void main(String[] args) {
        String smiley = "pass 😀";
        String frown = "no pass ☹";
        boolean verbose = true;

        String loginTestFile = "tests/logintests.csv";

        System.out.println("Overlord tests" + smiley);
        Overlord overlord = new Overlord();
        int rc = overlord.login("0", "0");
        System.out.println("Login test: " + (rc > 1 ? smiley : frown));
    }
}
