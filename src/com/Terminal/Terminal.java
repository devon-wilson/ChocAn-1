package com.Terminal;

import com.IOAuth.IOAuthorization;
import com.Overlord.Overlord;

import java.util.Scanner;
import java.util.Stack;

/**
 * Terminal
 * <p>
 *   extends Chocan's IO Authorization class
 * </p>
 */
public class Terminal extends IOAuthorization {
  private Overlord overlord;
  final private Scanner in;
  final String prompt = "$ ";
  final private Stack<String> breadcrumb;

  /**
   * Terminal Constructor
   *
   * @param overlord ChocaAn API to send and recieve information from users
   */
  public Terminal(Overlord overlord) {
    super();
    this.overlord = overlord;
    in = new Scanner(System.in);
    breadcrumb = new Stack<>();
  }

  /**
   * Start terminal emulation
   * <p>
   *   Emulates the terminals using the ChocAn API
   * </p>
   */
  public void start() {
    char choice = '\0';
    System.out.println("\nsTerminal.start");
    breadcrumb.push("Terminal");
    do {
      System.out.println("\nChoose terminal:" +
              "\n p - Provider terminal" +
              "\n m - Manager terminal" +
              "\n q - Quit"
      );
      prompt();
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
    breadcrumb.push("Provider");
    System.out.println("Authorized Access Only");

    System.out.print("\nEnter Provider ID" +
            "\nLogin" + prompt
    );
    providerID = in.nextLine();
    int returnCode;
    if (!isValidID(providerID, 9, "Provider ID"))
      return;
    returnCode = overlord.login(1, providerID);
    if (returnCode < 0) {
      System.out.println("Could not login");
      return;
    }

    do {
      System.out.println("\nChoose Provider option:" +
              "\n 1 - Check in member" +
              "\n q - quit (logout)"
      );
      prompt();
      choice = getChoice();

      if (choice == '1') {
        checkInMember();
      }

    } while (choice != 'q');
    breadcrumb.pop();
    overlord.logout();
  }

  private void providerCheckedIn() {
    char choice = '\0';
    System.out.println("Terminal.providerCheckedIn");
    breadcrumb.add("checkedIn");

    do {
      System.out.println("\nChoose Provider option:" +
              "\n 1 - 1" +
              "\n 2 - 2" +
              "\n 3 - 3" +
              "\n 4 - 4" +
              "\n q - quit (check out)"
      );
      prompt();
      choice = getChoice();

      switch (choice) {
        case '1':
          break;
        case '2':
          break;
        case '3':
          break;
        case '4':
          break;
        default:
          break;
      }

    } while (choice != 'q');

    overlord.memberCheckOut();
    breadcrumb.pop();
  }

  private void startManager() {
    char choice = '\0';
    String managerID;
    System.out.println("\nTerminal.startManager");
    breadcrumb.push("Manager");
    System.out.println("Authorized Access Only");

    System.out.print("Login" + prompt);
    managerID = in.nextLine();

    if (!isValidID(managerID, 9, "Member ID"))
      return;

    int returnCode = overlord.login(0, managerID);
    if (returnCode < 0) {
      System.out.println("Could not login");
      return;
    }

    do {
      System.out.println("\nChoose Manager option:" +
              "\n q - Quit"
      );
      prompt();
      choice = getChoice();

      switch (choice) {
        default:
          break;
      }

    } while (choice != 'q');

    breadcrumb.pop();
  }

  void prompt() {
    int last = breadcrumb.size() - 1;
    for (int i = 0; i < last; ++i) {
      System.out.print(breadcrumb.get(i) + ".");
    }
    System.out.print(breadcrumb.get(last) + prompt);
  }

  private char getChoice() {
    try {
      String input = in.nextLine();
      return Character.toLowerCase(input.charAt(0));
    } catch (StringIndexOutOfBoundsException e) {
      return '\0';
    }
  }

  private boolean isValidID(String ID, int expected, String name) {
    int returnCode = validateID(ID, expected);
    if (returnCode < 0) {
      System.out.print("Not valid " + name);
      return false;
    }
    return true;
  }

  /*
  private boolean validateMemberID() {
    System.out.print("Enter your Member ID: ");
    String memberID = in.next();
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
   */

  //function's for each menu item

  private void checkInMember() {
    System.out.print("Enter memberID:\n$ ");
    String memberID = in.nextLine();
    int returnCode;
    if (!isValidID(memberID, 9, "Member ID"))
      return;
    returnCode = overlord.memberCheckIn(memberID);
    if (returnCode < 0) {
      System.out.println("Could not check in member: " + memberID);
      return;
    }

    overlord.viewMember();
    providerCheckedIn();
  }

  private void generateRecordOfService() {
    //overlord.generateServiceRecord();
  }

  private void requestProviderDirectory() {
    //overlord.requestDirectory();
  }

  private boolean generateChocanBill() {
    System.out.println("Generate ChocAn Bill");
    String date;
    String serviceID;
    //boolean usethis = validateMemberID(member);
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
    serviceID = getServiceCode();
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

  private void manageMembers() {
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

  private void manageProvidersAndServices() {
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

  private void generateReports() {
    //overlord.genMemberReport;
  }

  private void viewReports() {
    //overlord.
  }

  private String getServiceCode() {
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
