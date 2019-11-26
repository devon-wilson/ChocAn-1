public class Main {
    public static void main (String[] args) {
        unitTests myTest = new unitTests();
        int success = myTest.testDataClasses();
        if(success == 0){
            System.err.println("all tests passed");
        }
        else{
            System.err.println("tests failed");
        }
    }
}