public class Main {
    public static void main (String[] args) {
        System.out.print("Hello World.");

        Manager myManager = new Manager();
        String first = new String("Hello");
        String second = new String("World");
        myManager.changeName(first);
        myManager.display();
        myManager.changeName(second);
        myManager.display();
    }
}