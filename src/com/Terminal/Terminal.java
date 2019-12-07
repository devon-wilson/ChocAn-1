package com.Terminal;

import com.IOAuth.IOAuthorization;
import com.Overlord.Overlord;

import java.util.Date;
import java.util.Scanner;
import java.util.Stack;

import static com.Terminal.FieldType.*;

/**
 * Terminal
 * <p>
 *   extends ChocAn's IO Authorization class
 * </p>
 */
public class Terminal extends IOAuthorization {
  private enum dataType {MEMBER, PROVIDER, SERVICE;}
  final private Overlord overlord;
  final private Scanner in;
  final String prompt = "$ ";
  final private Stack<String> breadcrumbs;

  /**
   * Terminal Constructor
   *
   * @param overlord ChocAn API to send and receive information from users
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
          System.out.println("\nNot a valid response");
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
              "\n 2 - Generate record of service" +
              "\n 3 - Request provider directory" +
              "\n 4 - Generate ChocAn bill" +
              "\n q - Quit (logout)"
      );
      breadcrumbPrompt();
      choice = getChoice();

      switch (choice) {
        case '1':
          if (checkInMember()) {
            providerCheckedIn();
          }
          break;
        case '2':
          generateRecordOfService();
          break;
        case '3':
          requestProviderDirectory();
          break;
        case '4':
          generateChocAnBill();
          break;
        case 'q':
          break;
        default:
          System.out.println("\nNot a valid response");
          break;
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
              "\n 2 - Generate record of service" +
              "\n 3 - Request provider directory" +
              "\n 4 - Generate ChocAn bill" +
              "\n 5 - 5" +
              "\n 6 - 6" +
              "\n 7 - 7" +
              "\n q - Quit (check out)"
      );
      breadcrumbPrompt();
      choice = getChoice();

      switch (choice) {
        case '1':
          overlord.viewMember();
          break;
        case '2':
          generateRecordOfService();
          break;
        case '3':
          requestProviderDirectory();
          break;
        case '4':
          generateChocAnBill();
          break;
        case '5':
          break;
        case '6':
          break;
        case '7':
          break;
        case 'q':
          break;
        default:
          System.out.println("\nNot a valid response");
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

    if (isNotValidID(managerID, 9, "Manager ID")) {
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
              "\n 4 - Manage provider directories" +
              "\n q - Quit"
      );
      breadcrumbPrompt();
      choice = getChoice();

      switch (choice) {
        case '1':
          manageMembers();
          break;
        case '2':
          manageProviders();
          break;
        case '3':
          manageServices();
          break;
        case '4':
          manageProviderDirectory();
          break;
        case 'q':
          break;
        default:
          System.out.println("\nNot a valid response");
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
      System.out.println("\nManage member options:" +
              "\n 1 - View one" +
              "\n 2 - View all" +
              "\n 3 - Add" +
              "\n 4 - Delete" +
              "\n 5 - Suspend" +
              "\n 6 - Renew" +
              "\n q - Quit"
      );
      breadcrumbPrompt();
      choice = getChoice();
      String MID = null;

      switch (choice) {
        case '1': // view
          System.out.println("Enter member ID");
          MID = getID(dataType.MEMBER);
          if(MID == null){
            break;
          }
          String [] components = overlord.getMember(MID);
          System.out.println("Member info");
          display(components);
          break;
        case '2':
          overlord.viewMembers();
          break;
        case '3': // add
          addMember();
          break;
        case '4': // delete
          System.out.println("Enter member ID");
          MID = getID(dataType.MEMBER);
          if(MID == null){
            break;
          }
          overlord.removeMember(MID);
          break;
        case '5': // suspend
          System.out.println("Enter member ID");
          MID = getID(dataType.MEMBER);
          if(MID == null){
            break;
          }
          overlord.suspendMember(MID);
          break;
        case '6':
          System.out.println("Enter member ID");
          MID = getID(dataType.MEMBER);
          if(MID == null){
            break;
          }
          overlord.renewMember(MID);
          break;
        case 'q':
          break;
        default:
          System.out.println("\nNot a valid response");
          break;
      }

    } while (choice != 'q');

    breadcrumbs.pop();
  }
  private void manageProviders() {
    char choice;
    String managerID;
    System.out.println("Terminal.manageProviders");
    breadcrumbs.push("providers");

    do {
      System.out.println("\nManage provider options:" +
              "\n 1 - View one" +
              "\n 2 - View all" +
              "\n 3 - Add" +
              "\n 4 - Delete" +
              "\n q - Quit"
      );
      breadcrumbPrompt();
      choice = getChoice();
      String PID = null;

      switch (choice) {
        case '1': // view
          System.out.println("Enter provider ID");
          PID = getID(dataType.PROVIDER);
          if(PID == null){
            break;
          }
          String [] components = overlord.getProvider(PID);
          System.out.println("Provider info");
          display(components);
          break;
        case '2':
          overlord.viewProviders();
          break;
        case '3': // add
          addProvider();
          break;
        case '4': // delete
          System.out.println("Enter provider ID");
          PID = getID(dataType.PROVIDER);
          if(PID == null){
            break;
          }
          overlord.removeProvider("");
          break;
        case 'q':
          break;
        default:
          System.out.println("\nNot a valid response");
          break;
      }

    } while (choice != 'q');

    breadcrumbs.pop();
  }
  private void manageServices() {
    char choice;
    String managerID;
    System.out.println("Terminal.manageServices");
    breadcrumbs.push("members");

    do {
      System.out.println("\nManage service options:" +
              "\n 1 - View one" +
              "\n 2 - View all" +
              "\n 3 - Add" +
              "\n 4 - Delete" +
              "\n q - Quit"
      );
      breadcrumbPrompt();
      choice = getChoice();
      String SID = null;
      switch (choice){
        case '1': // view
          System.out.println("Enter service code");
          SID = getID(dataType.SERVICE);
          if(SID == null){
            break;
          }
          String [] components = overlord.getService(SID);
          System.out.println("Service info");
          display(components);
          break;
        case '2':
          overlord.viewServices();
          break;
        case '3': // add
          addService();
          break;
        case '4': // delete
          System.out.println("Enter service code");
          SID = getID(dataType.SERVICE);
          if(SID == null){
            break;
          }
          overlord.removeService(SID);
          break;
        case 'q':
          break;
        default:
          System.out.println("\nNot a valid response");
          break;
      }
    } while (choice != 'q');

    breadcrumbs.pop();
  }
  private void manageProviderDirectory() {
    char choice;
    String managerID;
    System.out.println("Terminal.manageDirectory");
    breadcrumbs.push("members");
    System.out.println("Enter Provider ID");
    String PID = getID(dataType.PROVIDER);
    if(PID == null){
      return;
    }

    do {
      System.out.println("\nManage directory options:" +
              "\n 1 - View" +
              "\n 2 - Add" +
              "\n 3 - Delete" +
              "\n q - Quit"
      );
      breadcrumbPrompt();
      choice = getChoice();
      String SID = null;
      switch (choice){
        case '1': // view
          overlord.viewDirectory(PID);
          break;
        case '2': // add
          System.out.println("Enter service code");
          SID = getID(dataType.SERVICE);
          if(SID == null){
            break;
          }
          addService(PID, SID);
          break;
        case '3': // delete
          SID = getID(dataType.SERVICE);
          if(SID == null){
            break;
          }
          overlord.removeService(PID, SID);
          break;
        case 'q':
          break;
        default:
          System.out.println("\nNot a valid response");
          break;
      }
    } while (choice != 'q');

    breadcrumbs.pop();
  }


  private void display(String [] toDisplay){
    if(toDisplay == null){
      return;
    }
    for(int i = 0; i < toDisplay.length; ++i){
      System.out.println(toDisplay[i]);
    }
  }
  private String getLine(){
    String line = null;

    return line;
  }

  private void breadcrumbPrompt() {
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
      System.out.println("Not valid " + name);
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
      System.out.println("\nInvalid Member");
      System.out.println("");
      return false;
    }

    if (overlord.isMemberValid()) {
      System.out.println("\nValidated");
      return true;
    } else {
      System.out.println("\nSuspended Member ");
      overlord.memberCheckOut();
      return false;
    }
  }

  private String getID(dataType type) {
    String name = null;
    int length = 0;
    if(type == type.MEMBER){
      name = "Member ID";
      length = 9;
    }
    else if(type == type.PROVIDER){
      name = "Provider ID";
      length = 9;
    }
    else if(type == type.SERVICE){
      name = "Service ID";
      length = 6;
    }
    else{
      return null;
    }
    String input;
    String quit = new String("q");
    boolean valid = false;
    do {
      input = in.nextLine();
      valid = isNotValidID(input, length, name);
      if(valid == false){
        System.out.println("Press q to quit.");
        if(input.equals(quit) == true){
          return null;
        }
      }
    } while(isNotValidID(input, length, name));
    return input;
  }

  private void addMember() {
    final Field[] addMemberFields = {
            new Field("Name", 25, text),
            new Field("Member ID", 9, id),
            new Field("Address", 25, text),
            new Field("City", 14, text),
            new Field("State", 2, text),
            new Field("ZIP Code", 5, text),
    };
    String[] newMember = new String[addMemberFields.length + 1];
    queryFields(addMemberFields, newMember);

    int returnCode = overlord.addMember(newMember);
    if (returnCode < 0) {
      String[] translations = {"Failed to add", "Not authorized for this action"};
      System.out.println(translations[getReturnCodeIndex(returnCode)]);
    }
  }
  private void addProvider() {
    final Field[] addProviderFields = {
            new Field("Name", 25, text),
            new Field("Member ID", 9, id),
            new Field("Address", 25, text),
            new Field("City", 14, text),
            new Field("State", 2, text),
            new Field("ZIP Code", 5, text),
    };
    String[] newProvider = new String[addProviderFields.length + 1];
    queryFields(addProviderFields, newProvider);

    int returnCode = overlord.addProvider(newProvider);
    if (returnCode < 0) {
      String[] translations = {"Failed to add", "Not authorized for this action"};
      System.out.println(translations[getReturnCodeIndex(returnCode)]);
    }
  }
  private void addService() {
    final Field[] addServiceFields = {
            new Field("Name", 25, text),
            new Field("Code", 6,text),
            new Field("cost", 6, currency),
            new Field("ID", 9, text),
    };
    String[] newService = new String[addServiceFields.length + 1];
    queryFields(addServiceFields, newService);

    int returnCode = overlord.addService(newService);
    if (returnCode < 0) {
      String[] translations = {"Failed to add", "Not authorized for this action"};
      System.out.println(translations[getReturnCodeIndex(returnCode)]);
    }
  }
  private void addService(String PID, String SID) {
    final Field[] addServiceFields = {
            new Field("Name", 25, text),
            new Field("Code", 6,empty),
            new Field("cost", 6, currency),
            new Field("ID", 9, empty),
    };
    String[] newService = new String[addServiceFields.length + 1];
    queryFields(addServiceFields, newService);
    newService[1] = SID;
    newService[3] = PID;

    int returnCode = overlord.addService(newService);
    if (returnCode < 0) {
      String[] translations = {"Failed to add", "Not authorized for this action"};
      System.out.println(translations[getReturnCodeIndex(returnCode)]);
    }
  }

  void queryFields(Field[] fields, String[] inputArray) {
    boolean rejected = true;
    for (int i = 0; i < fields.length; i++) {
      Field field = fields[i];
      if(field.fieldType == FieldType.empty){
        continue;
      }
      String input;
      do {
        System.out.printf("Enter %s: ", field);
        input = in.nextLine();
        switch (field.fieldType) {
          case text:
            rejected = validateTextLength(input, field.maxLength) < 0;
            if (rejected)
              System.out.printf("\nEntered %s is too long\n", field);
            break;
          case id:
            rejected = validateID(input, field.maxLength) < 0;
            if (rejected)
              System.out.printf("\nEntered is not valid %s\n", field);
            break;
          case datetime:
            input = dateTimeFormat.format(new Date());
            System.out.println("Time stamping record at " + input);
            rejected = false;
            break;
          case date:
            rejected = validateDate(input) < 0;
            if (rejected)
              System.out.println("\nEntered %s not valid format (MM-DD-YYYY)");
            break;
          case currency:
            rejected = validateCurrency(input, field.maxLength) < 0;
            if (rejected)
              System.out.println("\nEntered %s too high");
            break;
          default:
            break;
        }
      } while (rejected);
      inputArray[i] = input;
    }
  }

  private int getReturnCodeIndex(int returnCode) {
    return -1 - returnCode;
  }

  private void generateRecordOfService() {
    /*
    dateProvided
    serviceCode
    comment
     */

    final Field[] serviceReportFields = {
            new Field("Date Provided", 0, date),
            new Field("Service Code", 6, id),
            new Field("Comments", 100, text),
    };
    String[] reportInfo = new String[serviceReportFields.length];
    queryFields(serviceReportFields, reportInfo);

    int returnCode = overlord.generateServiceRecord(reportInfo);
    if (returnCode < 0) {
      String[] translations = {"Failed to generate service record", "Not authorized for this action or no member checked in"};
      System.out.println(translations[getReturnCodeIndex(returnCode)]);
    }
  }

  private void requestProviderDirectory() {
    overlord.requestDirectory();
  }

  private boolean generateChocAnBill() {
    System.out.println("Generate ChocAn Bill");
    String date;
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
