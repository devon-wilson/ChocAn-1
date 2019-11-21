import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

abstract class Terminal {
    String user_type = " ";
    String id = " ";
    Terminal(String user_type, String id){
        this.user_type = user_type;
        this.id = id;
    }
    abstract void print();
}

class Provider_Terminal extends Terminal {
    Provider_Terminal(String name, String id){
        super(name,id);
    }
    void print() {
        System.out.print("1 - Check in member\n" +
                "2 - Generate record of service\n" +
                "3 - Request provider directory\n" +
                "4 - Generate Chocan bill\n");
    }
}

class Manager_Terminal extends Terminal{
    Manager_Terminal(String name, String id){
        super(name,id);
    }
    void print() {
        System.out.print("5 - Manage members\n" +
                "6 - Manage providers and services\n" +
                "7 - Generate reports\n" +
                "8 - View reports\n" +
                "9 - Quit\n");
    }
}

class main{
    public static void main(String[] args)
    {
        int prov = 0;
        int mana = 1;
        IOAuthorization IO = new IOAuthorization();
        Scanner in = new Scanner(System.in);
        System.out.print("Are you a provider(0) or manager(1)");
        int user_type = in.nextInt();
        System.out.print("Enter your provider/member name: ");
        String name = in.next();
        System.out.print("Enter your provider/member id: ");
        String id = in.next();


        if(user_type == prov){
            Provider_Terminal a_terminal = new Provider_Terminal(name, id);
            a_terminal.print();
        }
        else{
            Manager_Terminal a_terminal = new Manager_Terminal(name, id);
            a_terminal.print();
        }
        int choice = in.nextInt();
        switch(choice){
            case 1:
                // Statements
                break; // break is optional
            case 2:
                // Statements
                break; // break is optional
            default :
                // No break is needed in the default case.
        }
    }
}