package com.Terminal;

import com.IOAuth.IOAuthorization;
import com.Overlord.Overlord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Terminal extends IOAuthorization {
  private String member_number = " ";
  private String member_status = "unsuspended";//Valid or Suspended
  private String date_of_service = " ";
  private Overlord overlord;

  public static void main(String[] args) {
    Overlord overlord = new Overlord();
    Terminal terminal = new Terminal(overlord);
    terminal.begin();
  }

  public Terminal(Overlord overlord) {
    super();
    this.overlord = overlord;
  }

  /**
   * Begin terminal emulation
   */
  public void begin() {
    while (true) {
      boolean switcher = true;
      String prov = "provider";
      String mana = "manager";
      Scanner in = new Scanner(System.in);
      System.out.print("Are you a 'provider' or 'manager'");
      String user_type = in.next();
      while (true) {
        if (user_type.equalsIgnoreCase("provider")) {
          break;
        } else if (user_type.equalsIgnoreCase("manager")) {
          break;
        } else {
          System.out.print("invalid input, please enter 'provider' or 'manager'");
          user_type = in.next();
        }
      }
      if (validate_member_number(in)) {
        System.out.print("Validated\n");
      }
      if (prov.equals(user_type)) {
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
          Check_in_member(in, member_number);
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

  private boolean validate_member_number(Scanner in) {
    System.out.print("Enter your member number: ");
    member_number = in.next();
    int result = validateID(member_number, 9);
    while (true) {
      if (result == -1) {
        System.out.print("invalid input, try again(9 digits)");
        member_number = in.next();
        result = validateID(member_number, 9);
      } else {
        break;
      }
    }
    return true;
  }

  //function's for each menu item
  private void Check_in_member(Scanner in, String member_number) {
    //overlord.memberCheckIn(member_number);
  }

  private void Generate_record_of_service(Scanner in) {
    // overlord.generateServiceRecord();
  }

  private void Request_provider_directory(Scanner in) {
    //overlord.requestDirectory();
  }

  private boolean Generate_Chocan_bill(Scanner in) {
    System.out.println("Generate ChocAn Bill");
    String date;
    String service_code;
    validate_member_number(in);
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
    service_code = get_service_code(in);
        /*System.out.println("Would you like to add comments to record?");
        boolean to_comment = in.nextBoolean();
        if(to_comment){
            System.out.print("Enter comments: ");
            String comments = in.next();
         }

         */
    //overlord.generateBill()
    System.out.println("Fake ChocAn Bill generated(user needs to validate service code, additioanl comments also optional");
    return true;
  }

  private void Manage_members(Scanner in) {
    /*name,number,address,city,state,zip*/
    System.out.print("1 - Add member\n" +
            "2 - Delete member\n" +
            "3 - Suspend member\n" +
            "4 - Renew member\n" +
            "5 - Search member\n");
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

  private void Manage_providers_and_services(Scanner in) {
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

  private void Generate_reports(Scanner in) {
    //overlord.genMemberReport;
  }

  private void View_reports(Scanner in) {
    //overlord.
  }

  private String get_service_code(Scanner in) {
    System.out.print("please enter service code");
    String service_code = in.next();
    int result = validateID(service_code, 6);
    while (true) {
      if (result == -1) {
        System.out.print("invalid code, try again(6 digit #)");
        service_code = in.next();
        result = validateID(service_code, 6);
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
