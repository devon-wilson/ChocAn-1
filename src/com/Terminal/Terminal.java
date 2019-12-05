package com.Terminal;

import com.IOAuth.IOAuthorization;
import com.Overlord.Overlord;

import java.util.Scanner;

public class Terminal extends IOAuthorization {
  private String memberID = " ";
  private String memberStatus = "unsuspended";//Valid or Suspended
  private String dateOfService = " ";
  private Overlord overlord;
  private Scanner in;
  final String prompt = "$ ";

  public Terminal(Overlord overlord) {
    super();
    this.overlord = overlord;
    in = new Scanner(System.in);
  }

  char getChoice() {
    String input = in.nextLine();
    return Character.toLowerCase(input.charAt(0));
  }

  /**
   * Start terminal emulation
   */
  public void start() {
    char choice = '\0';
    System.out.println("\nsTerminal.start");
    do {
      System.out.print("\nChoose terminal:" +
              "\n p - Provider terminal" +
              "\n m - Manager terminal" +
              "\n q - Quit" +
              "\nTerminal" + prompt
      );
      choice = getChoice();

      switch (choice) {
        case 'p':
          startProvider();
          break;
        case 'm':
          startManager();
          break;
        default:
          break;
      }

    } while (choice != 'q');
  }

  private void startProvider() {
    char choice = '\0';
    String providerID;
    System.out.println("\nTerminal.startProvider");

    System.out.print("\nEnter Provider ID" +
            "\nLogin" + prompt
    );
    providerID = in.nextLine();
    int rc = overlord.login(1, providerID);
    if (rc < 0) {
      System.out.println("Could not login");
      return;
    }

    do {
      System.out.print("\nChoose Provider option:");

      // change this to a separate function
      if (overlord.isMemberCheckedIn())
        System.out.print("\n 1 - Check in member");
      else
        System.out.println(" 1 - Other options ");

      System.out.println("\n q - Quit" +
                      "\nProvider" + prompt
      );
      choice = getChoice();

      switch (choice) {
        case '1':
          checkInMember();
        default:
          break;
      }

    } while (choice != 'q');
  }

  private void startManager() {
    char choice = '\0';
    String managerID;
    System.out.println("\nTerminal.startManager");

    System.out.print("Login" + prompt);
    managerID = in.nextLine();
    int rc = overlord.login(0, managerID);
    if (rc < 0) {
      System.out.println("Could not login");
      return;
    }

    do {
      System.out.print("\nChoose Manager option:" +
              "\n q - Quit" +
              "\nManager" + prompt
      );
      choice = getChoice();

      switch (choice) {
        default:
          break;
      }

    } while (choice != 'q');
  }

  private boolean validateMemberID(Scanner in) {
    System.out.print("Enter your Member ID: ");
    memberID = in.next();
    int result = validateID(memberID, 9);
    while (true) {
      if (result == -1) {
        System.out.print("invalid input, try again(9 digits)");
        memberID = in.next();
        result = validateID(memberID, 9);
      } else {
        break;
      }
    }
    return true;
  }

  //function's for each menu item

  private void checkInMember() {
    System.out.print("Enter memberID:\n$ ");
    String memberID = in.nextLine();
    int rc = overlord.memberCheckIn(memberID);
    if (rc < 0) {
      System.out.println("Could not check in member " + memberID);
      return;
    }

    overlord.viewMember();
  }

  private void generateRecordOfService(Scanner in) {
    // overlord.generateServiceRecord();
  }

  private void requestProviderDirectory(Scanner in) {
    //overlord.requestDirectory();
  }

  private boolean generateChocanBill(Scanner in) {
    System.out.println("Generate ChocAn Bill");
    String date;
    String serviceID;
    boolean usethis = validateMemberID(in);
    System.out.print("Enter date of service(MM-DD-YYYY)");
    date = in.next();
    int result = validateDate(date);
    while (true) {
      if (result == -1) {
        System.out.print("invalid input, try again(MM-DD-YYYY)");
        date = in.next();
        result = validateDate(date);
      } else {
        break;
      }
    }
    serviceID = getServiceCode(in);
        /*System.out.println("Would you like to add comments to record?");
        boolean toComment = in.nextBoolean();
        if(toComment){
            System.out.print("Enter comments: ");
            String comments = in.next();
         }

         */
    //overlord.generateBill()
    System.out.println("Fake ChocAn Bill generated(user needs to validate service code, additional comments also optional");
    return true;
  }

  private void manageMembers(Scanner in) {
    /*name,number,address,city,state,zip*/
    System.out.print("\n" +
            "\n 1 - Add" +
            "\n 2 - Delete" +
            "\n 3 - Suspend" +
            "\n 4 - Renew"
    );
    String choice = in.next();
    while (true) {
      if (validateMenu(choice, 5) == -1) {
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
        //overlord.removeMember(String memberID)
        break;
      case 3:
        //overlord.suspendMember(String memberID);
        break;
      case 4:
        //overlord.renewMember(String memberID);
      case 5:
        //overlord.searchMember(String query);
        break;
    }

  }

  private void manageProvidersAndServices(Scanner in) {
    System.out.print("1 - Add provider\n" +
            "2 - Delete provider\n" +
            "3 - Search provider\n" +
            "4 - Add services\n" +
            "5 - Remove services\n" +
            "6 - Search services\n");
    String choice = in.next();
    while (true) {
      if (validateMenu(choice, 6) == -1) {
        System.out.print("invalid choice(1-9), try again");
        choice = in.next();
      } else {
        break;
      }
    }
    int aChoice = Integer.parseInt(choice); //convert string to integer for menu switch
    switch (aChoice) {
      case 1:
        //overlord.addProvider(String providerData);
        break; // break is optional
      case 2:
        //removeProvider(String providerID);
        break;
      case 3:
        //overlord.searchProvider(String query);
        break;
      case 4:
        //overlord.addService(String[] serviceData);
      case 5:
        //overlord.removeService(String serviceID);
      case 6:
        //overlord.searchService(String query);
        break;
    }

  }

  private void generateReports(Scanner in) {
    //overlord.genMemberReport;
  }

  private void viewReports(Scanner in) {
    //overlord.
  }

  private String getServiceCode(Scanner in) {
    System.out.print("please enter service code");
    String serviceID = in.next();
    int result = validateID(serviceID, 6);
    while (true) {
      if (result == -1) {
        System.out.print("invalid code, try again(6 digit #)");
        serviceID = in.next();
        result = validateID(serviceID, 6);
      } else {
        break;
      }
    }
    //String service = get_Service("String serviceID");
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
    //return String getService(in);
    //}
    //else{
    //  return serviceID;
    //}
    return serviceID;
  }

}
