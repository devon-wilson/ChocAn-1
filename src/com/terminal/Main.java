import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static String member_number = " ";
    public static String member_status = "unsuspended";//suspended or unsuspended
    public static String date_of_service = " ";
    public static IOAuthorization IO = new IOAuthorization();


    public static void main(String[] args) {
        String prov = "provider";
        String mana = "manager";
        Scanner in = new Scanner(System.in);
        System.out.print("Are you a 'provider' or 'manager'");
        String user_type = in.next();
        while (true) {
            if (user_type.equalsIgnoreCase("provider")) {
                break;
            }
            else if(user_type.equalsIgnoreCase("manager")) {
                break;
            }
            else {
                    System.out.print("invalid input, please enter 'provider' or 'manager'");
                    user_type = in.next();
            }
        }
        /*System.out.print("Enter your provider/member name (name < 25 characters)");
        String name = in.next();
        int result = IO.validateTextLength(name, 25);
        while (true) {
            if (result == -1) {
                System.out.println("value entered is invalid");
                name = in.next();
                result = IO.validateTextLength(name, 25);
            } else {
                break;
            }
        }*/
        if(validate_member_number(in)){
            System.out.print("Validated\n");
        }
        if (stringCompare(prov, user_type) == 0) {
            //Provider_Terminal a_terminal = new Provider_Terminal(name, member_number);
            //a_terminal.print();
            System.out.print("1 - Check in member\n" +
                    "2 - Generate record of service\n" +
                    "3 - Request provider directory\n" +
                    "4 - Generate Chocan bill\n");
        } else {
            //Manager_Terminal a_terminal = new Manager_Terminal(name, member_number);
            //a_terminal.print();
            System.out.print("5 - Manage members\n" +
                    "6 - Manage providers and services\n" +
                    "7 - Generate reports\n" +
                    "8 - View reports\n" +
                    "9 - Quit\n");
        }
        String aChoice = in.next();
        while (true) {
            if (IO.validateMenu(aChoice, 9) == -1) {
                System.out.print("invalid choice(1-9), try again");
                aChoice = in.next();
            } else {
                break;
            }
        }
        int choice = Integer.parseInt(aChoice); //convert string to integer for menu switch
        switch (choice) {
            case 1:
                Check_in_member(in,member_number);
                break; // break is optional
            case 2:
                Generate_record_of_service(in);
                break;
            case 3:
                Request_provider_directory(in);
                break;
            case 4:
                Generate_Chocan_bill(in);
                break;
            case 5:
                Manage_members(in);
                break;
            case 6:
                Manage_providers_and_services(in);
                break;
            case 7:
                Generate_reports(in);
                break;
            case 8:
                View_reports(in);
                break;
            case 9:
                break;
            default:
                // No break is needed in the default case.
        }
    }

    public static boolean validate_member_number(Scanner in) {
        System.out.print("Enter your member number: ");
        member_number = in.next();
        int result = IO.validateID(member_number, 9);
        while (true) {
            if (result == -1) {
                System.out.print("invalid input, try again(9 digits)");
                member_number = in.next();
                result = IO.validateID(member_number, 9);
            } else {
                break;
            }
        }
        return true;
    }

    //function's for each menu item
    public static void Check_in_member(Scanner in, String member_number) {
        //memberCheckIn();
    }

    public static void Generate_record_of_service(Scanner in) {
        // generateServiceRecord();
    }

    public static void Request_provider_directory(Scanner in) {
        //requestDirectory();
    }
    public static boolean Generate_Chocan_bill(Scanner in) {
        System.out.println("Generate ChocAn Bill");
        String date = " ";
        String service_code = " ";
        validate_member_number(in);
        System.out.print("Enter date of service(MM-DD-YYYY)");
        date = in.next();
        int result = IO.validateDate(date);
        while (true) {
            if (result == -1) {
                System.out.print("invalid input, try again(MM-DD-YYYY)");
                date = in.next();
                result = IO.validateDate(date);
            } else {
                break;
            }
        }
        service_code = get_service_code(in);
        /*System.out.println("Would you like to add comments to record?");
        boolean to_comment = in.nextBoolean();
        if(to_comment){
            System.out.print("Enter comments: ");
            String comments = in.next();
         }

         */
        //generateBill()
        System.out.println("Fake ChocAn Bill generated(user needs to validate service code, additioanl comments also optional");
        return true;
    }

    public static void Manage_members(Scanner in) {
        /*name,number,address,city,state,zip*/
        System.out.print("1 - Add member\n" +
                "2 - Delete member\n" +
                "3 - Suspend member\n" +
                "4 - Renew member\n" +
                "5 - Search member\n");
        String choice = in.next();
        while (true) {
            if (IO.validateMenu(choice, 5) == -1) {
                System.out.print("invalid choice(1-9), try again");
                choice = in.next();
            } else {
                break;
            }
        }
        int aChoice = Integer.parseInt(choice); //convert string to integer for menu switch
        switch (aChoice) {
            case 1:
                //addMember(int MEMBER_PLACEHOLDER);
                break; // break is optional
            case 2:
                //removeMember();
                break;
            case 3:
                //suspendMember();
                break;
            case 4:
                //renewMember();
            case 5:
                //searchMember(String query);
                break;
        }

    }

    public static void Manage_providers_and_services(Scanner in) {
            System.out.print("1 - Add provider\n" +
                    "2 - Delete provider\n" +
                    "3 - Search provider\n" +
                    "4 - Add services\n" +
                    "5 - Remove services\n" +
                    "6 - Search services\n");
            String choice = in.next();
        while (true) {
            if (IO.validateMenu(choice, 6) == -1) {
                System.out.print("invalid choice(1-9), try again");
                choice = in.next();
            } else {
                break;
            }
        }
        int aChoice = Integer.parseInt(choice); //convert string to integer for menu switch
            switch (aChoice) {
                case 1:
                    //addProvider(int PROVIDER_PLACEHOLDER);
                    break; // break is optional
                case 2:
                    //removeProvider(int providerID)
                    break;
                case 3:
                    //searchProvider(String query)
                    break;
                case 4:
                    //addService(int SERVICE_PLACEHOLDER)
                case 5:
                    // removeService(int serviceID)
                case 6:
                    //searchService(String query)
                    break;
            }

    }

    public static void Generate_reports(Scanner in) {
        // genMemberReport(int memberID);
            // genProviderReport(int memberID);
            //sendReports(String input)?????
    }

    public static void View_reports(Scanner in) {
        //genAllMemberReports()
            //genAllProvidersReports()
            //sendReports(String input)???????
    }

    public static int stringCompare(String str1, String str2){//had problems with comparing strings, so I did it lexicographically{
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }
        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }

    public static String get_service_code(Scanner in) {
        System.out.print("please enter service code");
        String service_code = in.next();
        int result = IO.validateID(service_code, 6);
        while (true) {
            if (result == -1) {
                System.out.print("invalid code, try again(6 digit #)");
                service_code = in.next();
                result = IO.validateID(service_code, 6);
            } else {
                break;
            }
        }
        //String service = get_Service("String service_code");
        //System.out.print("service found: " + service);
        //System.out.print("is this service correct?(yes(y) or no(n))");
        //String correct = in.next();
        /*while (true) {
            if (correct != 'y' || correct != 'n') {
                System.out.print("invalid reply, try again(yes(y) or no(n))");
                code = in.next();
            } else {
                break;
            }
        }*/
        //if(correct == 'n'){
        //return String get_service(in);
        //}
        //else{
        //  return service_code;
        //}
        return service_code;
    }
}
