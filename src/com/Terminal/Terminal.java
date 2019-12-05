package com.Terminal;

import com.IOAuth.IOAuthorization;
import com.Overlord.Overlord;

import java.util.Scanner;

public class Terminal extends IOAuthorization {
  private String memberID = " ";
  private String memberStatus = "unsuspended";//Valid or Suspended
  private String dateOfService = " ";
  private Overlord overlord;

  public static void main(String[] args) {
    Overlord overlord = new Overlord();
    Terminal terminal = new Terminal(overlord);
    terminal.start();
  }

  public Terminal(Overlord overlord) {
    super();
    this.overlord = overlord;
  }

  /**
   * Begin terminal emulation
   */
  public void start() {
    while (true) {
      boolean switcher = true;
      String prov = "provider";
      String mana = "manager";
      Scanner in = new Scanner(System.in);
      System.out.print("Are you a 'provider' or 'manager'");
      String userType = in.next();
      while (true) {
        if (userType.equalsIgnoreCase("provider")) {
          break;
        } else if (userType.equalsIgnoreCase("manager")) {
          break;
        } else {
          System.out.print("invalid input, please enter 'provider' or 'manager'");
          userType = in.next();
        }
      }
      if (validateMemberNumber(in)) {
        System.out.print("Validated\n");
      }
      if (prov.equals(userType)) {
        //Provider_Terminal a_terminal = new Provider_Terminal(name, member_number);
        //a_terminal.print();
        System.out.print("1 - Check in member\n" +
                "2 - Generate record of service\n" +
                "3 - Request provider directory\n" +
                "4 - Generate Chocan bill\n" +
                "9 - Quit\n");
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
        if (validateMenu(aChoice, 9) == -1) {
          System.out.print("invalid choice(1-9), try again");
          aChoice = in.next();
        } else {
          break;
        }
      }
      int choice = Integer.parseInt(aChoice); //convert string to integer for menu switch
      switch (choice) {
        case 1:
          checkInMember(in, memberID);
          break; // break is optional
        case 2:
          generateRecordOfService(in);
          break;
        case 3:
          requestProviderDirectory(in);
          break;
        case 4:
          generateChocanBill(in);
          break;
        case 5:
          manageMembers(in);
          break;
        case 6:
          manageProvidersAndServices(in);
          break;
        case 7:
          generateReports(in);
          break;
        case 8:
          viewReports(in);
          break;
        case 9:
          switcher = false;
          break;
        default:
          // No break is needed in the default case
      }
      if (!switcher) {
        break;
      }
    }
  }

  private void startProvider() {}

  private void startManager() {}

  private boolean validateMemberNumber(Scanner in) {
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
    validateMemberNumber(in);
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
