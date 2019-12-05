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
    System.out.println("Terminal.start");
    do {
      System.out.println("Choose terminal:");
      System.out.println("p - Provider terminal");
      System.out.println("m - Manager terminal");
      System.out.println("q - Quit");
      System.out.printf("Terminal" + prompt);
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
    System.out.println("Enter Provider ID");
    System.out.printf("Login" + prompt);
    providerID = in.nextLine();
    int rc = overlord.login(1, providerID);
    if (rc < 0) {
      System.out.println("Could not login");
      return;
    }

    do {
      System.out.println("\nChoose Provider option:");
      System.out.println("1 - Check in member");
      System.out.println("q - Quit");
      System.out.printf("Provider" + prompt);
      choice = getChoice();

      switch (choice) {
        default:
          break;
      }

    } while (choice != 'q');
  }

  private void startManager() {
    char choice = '\0';
    String managerID;
    System.out.println("\nTerminal.startManager");

    System.out.printf("Login" + prompt);
    managerID = in.nextLine();
    int rc = overlord.login(0, managerID);
    if (rc < 0) {
      System.out.println("Could not login");
      return;
    }

    do {
      System.out.println("\nChoose Manager option:");
      System.out.println("q - Quit");
      System.out.printf("Manager" + prompt);
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
  private void checkInMember(Scanner in, String memberID) {
    //overlord.memberCheckIn(memberID);
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
    validateMemberID(in);
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
        boolean to_comment = in.nextBoolean();
        if(to_comment){
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
    System.out.println("1 - Add");
    System.out.println("2 - Delete");
    System.out.println("3 - Suspend");
    System.out.println("4 - Renew");
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
