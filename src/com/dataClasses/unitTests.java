
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class unitTests {

    public unitTests(){
        file = null;
        sc = null;
    }

    public int testDataClasses(){
        int success = 1;
        String [] components = buildUser();
        if(components == null){
            return 0;
        }
        Member testMember = new Member();
        success *= testMember.build(components);
        success *= testMember.display();
        success *= testUser(testMember, components);

        Provider testProvider = new Provider();
        success *= testProvider.build(components);
        success *= testProvider.display();
        success *= testUser(testProvider, components);

        Manager testManager = new Manager();
        success *= testManager.build(components);
        success *= testManager.display();
        success *= testUser(testManager, components);

        return success;
    }
    public int testUser(User toTest, String [] original){
        int success = 1;
        success *= testAll(toTest, original);
        success *= getTest(toTest, original);
        success *= changeTest(toTest);
        return success;
    }

    public String [] buildUser(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        file = new File("src/com/dataClasses/user.csv");
        if(file == null){
            return null;
        }
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        sc.useDelimiter(",");
        String [] components = new String [6];
        for(int i = 0; i < 6; ++i){
            components[i] = sc.next();
        }
        return components;
    }

    public int testAll(User toTest, String [] original){
        String [] components = toTest.getAll();
        int success = 1;
        for(int i = 0; i < 6; ++i){
            if(components[i].equals(original[i]) == false){
                success = 0;
            }
        }
        if(success == 1){
            System.err.println("Get all test Passed.");
        }
        else{
            System.err.println("Get all test failed");
        }
        return success;
    }
    public int getTest(User toTest, String [] original){
        int success = 1;
        String toGet;
        for(int i = 0; i < 6; ++i){
            toGet = toTest.get(i);
            if(toGet.equals(original[i]) == false){
                success = 0;
            }
        }
        if(success == 1){
            System.err.println("Get individual passed");
        }
        else{
            System.err.println("Get individual failed");
        }
        return success;
    }
    public int changeTest(User toTest){
        String change = "changed";
        int success = 1;
        success *= toTest.changeName(change);
        success *= toTest.changeAddress(change);
        success *= toTest.changeCity(change);
        success *= toTest.changeID(change);
        success *= toTest.changeState(change);
        success *= toTest.changeZip(change);

        for(int i = 0; i < 6; ++i){
            if(toTest.get(i).equals(change) == false){
                success = 0;
            }
        }
        if(success == 1){
            System.err.println("Change field passed");
        }
        else{
            System.err.println("Change field failed.");
        }

        return success;
    }


    private File file;
    private Scanner sc;

}
