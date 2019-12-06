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
  final private Stack<String> breadcrumbs;

  /**
   * Terminal Constructor
   *
   * @param overlord ChocaAn API to send and recieve information from users
   */
  public Terminal(Overlord overlord) {
    super();
    this.overlord = overlord;
    in = new Scanner(System.in);
    breadcrumbs = new Stack<>();
  }

  /**
   * Start terminal emulation
   * <p>
   *   Emulates the terminals using the ChocAn API
   * </p>
   */
  public void start() {
    char choice;
    System.out.println("\nsTerminal.start");
    breadcrumbs.push("Terminal");
    do {
      System.out.println("\nChoose terminal:" +
              "\n p - Provider terminal" +
              "\n m - Manager terminal" +
              "\n q - Quit"
      );
      breadcrumbPrompt();
      choice = getChoice();

      switch (choice) {
        case 'p':
          startProvider();
          break;
        case 'm':
          startManager();
          break;
        case 'q':
          break;
        default:
          System.out.println("Not a valid response");
          break;
      }

    } while (choice != 'q');
  }

  private void startProvider() {
    char choice;
    String providerID;
    System.out.println("\nTerminal.startProvider");
    breadcrumbs.push("Provider");
    System.out.println("Authorized Access Only");

    System.out.print("\nEnter Provider ID" +
            "\nLogin" + prompt
    );
    providerID = in.nextLine();
    int returnCode;
    if (isNotValidID(providerID, 9, "Provider ID")) {
      breadcrumbs.pop();
      return;
    }
    returnCode = overlord.login(1, providerID);
    if (returnCode < 0) {
      System.out.println("Could not login");
      breadcrumbs.pop();
      return;
    }

    do {
      System.out.println("\nChoose Provider option:" +
              "\n 1 - Check in member" +
              "\n q - Quit (logout)"
      );
      breadcrumbPrompt();
      choice = getChoice();

      if (choice == '1') {
        if (checkInMember()) {
          providerCheckedIn();
        }
      }

    } while (choice != 'q');
    breadcrumbs.pop();
    overlord.logout();
  }

  private void providerCheckedIn() {
    char choice;
    System.out.println("Terminal.providerCheckedIn");
    breadcrumbs.push("checkedIn");

    do {
      System.out.println("\nChoose Provider option:" +
              "\n 1 - view member info" +
              "\n 2 - 2" +
              "\n 3 - 3" +
              "\n 4 - 4" +
              "\n q - Quit (check out)"
      );
      breadcrumbPrompt();
      choice = getChoice();

      switch (choice) {
        case '1':
          overlord.viewMember();
          break;
        case '2':
          break;
        case '3':
          break;
        case '4':
          break;
        case 'q':
          break;
        default:
          System.out.println("Not a valid response");
          break;
      }

    } while (choice != 'q');

    overlord.memberCheckOut();
    breadcrumbs.pop();
  }

  private void startManager() {
    char choice;
    String managerID;
    System.out.println("\nTerminal.startManager");
    breadcrumbs.push("Manager");
    System.out.println("Authorized Access Only");

    System.out.print("Login" + prompt);
    managerID = in.nextLine();

    if (isNotValidID(managerID, 9, "Member ID")) {
      breadcrumbs.pop();
      return;
    }

    int returnCode = overlord.login(0, managerID);
    if (returnCode < 0) {
      System.out.println("Could not login");
      breadcrumbs.pop();
      return;
    }

    do {
      System.out.println("\nChoose Manager option:" +
              "\n 1 - Manage members" +
              "\n 2 - Manage providers" +
              "\n 3 - Manage services" +
              "\n q - Quit"
      );
      breadcrumbPrompt();
      choice = getChoice();

      switch (choice) {
        case '1':
          manageMembers();
          break;
        case '2':
          manageProvidersAndServices();
          break;
        case '3':
          System.out.println("should this be separate?");
          break;
        case 'q':
          break;
        default:
          System.out.println("Not a valid response");
          break;
      }

    } while (choice != 'q');

    breadcrumbs.pop();
  }

  private void manageMembers() {
    char choice;
    String managerID;
    System.out.println("Terminal.manageMembers");
    breadcrumbs.push("members");

    do {
      System.out.print("\nManage member options:" +
              "\n 1 - Select" +
              "\n 2 - View" +
              "\n 3 - Add" +
              "\n 4 - Delete" +
              "\n 5 - Suspend" +
              "\n 6 - Renew" +
              "\n q - Quit"
      );
      breadcrumbPrompt();
      choice = getChoice();

      switch (choice) {
        case '1': // select
          checkInMember();
          break;
        case '2': // view
          overlord.viewMember();
          break;
        case '3': // add
          addMember();
          break;
        case '4': // delete
          overlord.removeMember("");
          break;
        case '5': // suspend

          overlord.suspendMember("");
          break;
        case '6': // renew

          overlord.renewMember("");
          break;
        case 'q':
          break;
        default:
          System.out.println("Not a valid response");
          break;
      }

    } while (choice != 'q');

    breadcrumbs.pop();
  }

  void breadcrumbPrompt() {
    int last = breadcrumbs.size() - 1;
    for (int i = 0; i < last; ++i) {
      System.out.print(breadcrumbs.get(i) + ".");
    }
    System.out.print(breadcrumbs.get(last) + prompt);
  }

  private char getChoice() {
    try {
      String input = in.nextLine();
      return Character.toLowerCase(input.charAt(0));
    } catch (StringIndexOutOfBoundsException e) {
      return '\0';
    }
  }

  private boolean isNotValidID(String ID, int expected, String name) {
    int returnCode = validateID(ID, expected);
    if (returnCode < 0) {
      System.out.print("Not valid " + name);
      return true;
    }
    return false;
  }

  //function's for each menu item

  private boolean checkInMember() {
    System.out.print("Enter memberID:\n$ ");
    String memberID = in.nextLine();
    int returnCode;
    if (isNotValidID(memberID, 9, "Member ID"))
      return false;
    returnCode = overlord.memberCheckIn(memberID);
    if (returnCode < 0) {
      System.out.println("Could not check in member: " + memberID);
      // needs reason valid or suspended?
      System.out.println("Invalid");
      // or suspended
      return false;
    }
    System.out.println("Validated");

    overlord.viewMember();
    return true;
  }

  private void addMember() {
    final String[] fields = {"Name", "ID", "Address", "City", "State", "ZIP"};
    final int[] inputLengths = {25, 9, 25, 14, 2, 5};
    String[] newMember = new String[fields.length + 1];
    boolean accepted;
    for (int i = 0; i < fields.length; i++) {
      String field = fields[i];
      do {
        System.out.printf("Enter %s: ", field);
        newMember[i] = in.nextLine();
        if (i == 1) {
          accepted = validateID(newMember[i], inputLengths[i]) >= 0;
          if (!accepted)
            System.out.println("\nMember number requires 9 digits");
        }
        else {
          accepted = validateTextLength(newMember[i], inputLengths[i]) >= 0;
          if (!accepted)
            System.out.printf("\nEntered %s is too long\n", field);
        }
      } while (!accepted);
    }
    int returnCode = overlord.addMember(newMember);
    if (returnCode < 0) {
      String[] translations = {"Failed to add", "Not authorized for this action"};
      System.out.println(translations[getReturnCodeIndex(returnCode)]);
    }
  }

  private int getReturnCodeIndex(int returnCode) {
    return -1 - returnCode;
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
    //String serviceID;
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
    //serviceID = getServiceCode();
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
