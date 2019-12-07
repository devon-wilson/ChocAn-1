package com.Overlord;

import com.DataBaseManager.DataBaseManager;
import com.DataClasses.*;
import com.ReadWrite.ReadWrite;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Overlord of layers
 *
 * <p>
 *     Overlord manages core functionality
 * </p>
 *
 * @author Jason Forral
 * @version 1.0.0
 */
public class Overlord extends DataBaseManager<Object> {
  private User currentUser;
  private Member currentMember;
  private ArrayList<Service> currentServices;

  /**
   * Default Constructor
   */
  public Overlord() {
    super();

    this.currentUser = null;
    this.currentMember = null;
    this.currentServices = null;
  }

  /**
   * Login
   *
   * @param userID Provider identification number
   * @return returns int: 1 for provider; 2 for manager; -1 could not log in
   */
  public int login(int userType, String userID) {
    // matches string to provider directory
    // if no match return -1

    // userType must be either 0 or 1
    if (userType < 0 || userType > 1)
      return -1;

    try {
      User login = (User) findData(userType, userID);
      if (login == null)
        return -1;

      // sets currentUser
      currentUser = login;

      // Fetch all services from service tree
      if (currentUser instanceof Provider) {
        currentServices = new ArrayList<Service>();
        String[] services = ((Provider) currentUser).getServices();
        Service service;
        for (String i : services) {
          if ((service = (Service) findData(3, i)) != null)
            currentServices.add(service);
        }
      }
      return 1;
    }
    catch (ClassCastException a){
      return -1;
    }
  }

  /**
   * Logout
   *
   * @return 1 and cannot fail.
   */
  public int logout() {
    // Need to write all trees to disk on logout

    this.currentUser = null;
    this.currentMember = null;
    this.currentServices = null;
    return 1;
  }

  /**
   * Member Check In
   * @param memberID contains id of member
   * @return returns 1 for success, -1 for failure.
   */
  public int memberCheckIn(String memberID) {
    if (currentUser == null)
      return -2;
    try {
      this.currentMember = (Member) findData(2, memberID);
      if (currentMember == null)
        return -1;
      return 1;
    }
    catch (ClassCastException a) {
      return -1;
    }
  }

  public boolean isMemberCheckedIn() {
    return (currentMember != null);
  }

  public boolean isMemberValid() {
    if (currentMember == null)
      return false;

    return (currentMember.getStatus().equalsIgnoreCase("valid"));
  }

  public boolean isMemberSuspended() {
    if (currentMember == null)
      return false;

    return (currentMember.getStatus().equalsIgnoreCase("suspended"));
  }
  /**
   * View Member
   *
   * @return 1 for success; -1 for no member checked in; -2 for no user checked in
   */
  public int viewMember() {
    if (currentUser == null) {
      return -2;
    }
    if (currentMember == null) {
      System.out.println("No member currently checked in.");
      return -1;
    }
    currentMember.display();
    return 1;
  }

  /**
   * Member Check Out
   * @return 1 for success, -1 for failure.
   */
  public int memberCheckOut() {
    // Check if member is checked in
    if(currentMember == null)
      return -1;
    // Set current member to null
    this.currentMember = null;
    return 1;
  }

  // Manage members
  /**
   * Add Member
   *
   * @param memberData Array of strings containing data in the correct order.
   * @return 1 for success, -1 for failure
   */
  public int addMember(String[] memberData) {
    // check if user is a manager
    if (currentUser == null || !(currentUser instanceof Manager))
      return -2;

    Member toAdd = new Member(memberData);
    if(addTreeData(2, toAdd.get(1), toAdd) == null)
      return 1;
    return -1;
  }

  /**
   * RemoveMember
   *
   * @param memberID member ID to remove
   * @return 1 for success, -1 for failure
   */
  public int removeMember(String memberID) {
    Object removed = removeTreeData(2, memberID);
    if (removed == null)
      return -1;
    return 1;
  }

  public int suspendMember(String memberID) {
    // check if there is a user logged in
    if (currentUser == null)
      return -1;
    // check if user is a manager
    if (!(currentUser instanceof Manager))
      return -2;

    try {
      // check if member to be suspended is current member
      if (currentMember != null && currentMember.get(1).equals(memberID)) {
        currentMember.updateStatus("SUSPENDED");
        // update tree with current info
        updateTreeData(2, memberID, currentMember);
      }

      else {
        // pull info from tree
        Member toUpdate = (Member) findData(2, memberID);
        if(toUpdate == null){
          return -1;
        }
        toUpdate.updateStatus("SUSPENDED");
        // put info back on tree
        updateTreeData(2, memberID, toUpdate);
      }
      return 1;
    }
    catch (ClassCastException a) {
      return -1;
    }
  }

  public int renewMember(String memberID) {
    // check if there is a user logged in
    if (currentUser == null)
      return -1;
    // check if user is a manager
    if (!(currentUser instanceof Manager))
      return -2;

    try {
      if (currentMember != null && currentMember.get(1).equals(memberID)) {
        currentMember.updateStatus("SUSPENDED");
        // update tree with current info
        updateTreeData(2, memberID, currentMember);
      }
      else {
        // pull info from tree
        Member toUpdate = (Member) findData(2, memberID);
        toUpdate.updateStatus("Validated");

        // put info back on tree
        updateTreeData(2, memberID, toUpdate);
      }
      return 1;
    }
    catch (ClassCastException a) {
      return -1;
    }
  }

  // Manage providers
  public int addProvider(String[] providerData) {
    // check if user is a manager
    if (currentUser == null || !(currentUser instanceof Manager))
      return -2;

    if(providerData == null)
      return -1;

    Provider toAdd = new Provider(providerData);

    if(addTreeData(1, providerData[1], toAdd) == null)
      return 1;
    return -1;
  }

  public int removeProvider(String providerID) {
    // check if user is a manager
    if (currentUser == null || !(currentUser instanceof Manager))
      return -2;

    if(removeTreeData(1,providerID) == null)
      return -1;
    return 1;
  }

  public int displayCurrentServices() {
    if (currentUser == null || !(currentUser instanceof Provider))
      return -2;
    try {
      for (Service i : currentServices)
        i.display();
      return 1;
    }
    catch (ClassCastException a) {
      System.out.println("Current user is not a Provider. Cannot display services");
      return -1;
    }
  }

  // Manage services
  public int addService(String[] serviceData) {
    if (currentUser == null || !(currentUser instanceof Manager))
      return -2;
    if (serviceData == null)
      return -3;


    Service toAdd = new Service(serviceData);
    toAdd = (Service) addTreeData(3, serviceData[1], toAdd);
    try {
        Provider toReturn = (Provider) findData(1, serviceData[3]);
        if (toReturn == null)
          return -1;
        toReturn.addService(serviceData[1]);
        return 1;
    } catch (ClassCastException a) {
        return -1;
      }
  }

  public int removeService(String serviceID) {
    if (currentUser == null || !(currentUser instanceof Manager))
      return -2;
    if (serviceID == null)
      return -3;

    Service service = (Service) removeTreeData(3, serviceID);
    if(service == null){
      return -1;
    }
    String code = service.getCode();
    ArrayList<Provider> providers = getAll(1);
    for(Provider i : providers){
      i.removeService(code);
    }
    return 1;
  }

  public String[] searchService(String query) {
    if (currentUser == null || query == null)
        return null;
    try {
      Service found = (Service) findData(3, query);
      if (found == null)
        return null;
      return found.getAll();
    }
    catch (ClassCastException a) {
      return null;
    }
  }

  public int genMemberReport(String memberID) {
    if (memberID == null)
      return -1;
    try {

      Member member;
      if (currentMember != null && currentMember.get(1).equals(memberID))
        member = currentMember;
      else
        member = (Member) findData(2, memberID);

      if (member == null)
        return -1;

      String outputFile = "data/reports/members/" + memberID + ".txt";

      // Write member data to text file
      ReadWrite.fileWrite(outputFile, "Member Report:", false);
      for (String i : member.getAll()) {
        ReadWrite.fileWrite(outputFile, i, true);
      }

      ReadWrite.fileWrite(outputFile, "", true);
      ReadWrite.fileWrite(outputFile, "Reports:", true);
      ArrayList<Record> userRecords = (ArrayList<Record>) findData(4, memberID);
      for (Record userRecord : userRecords) {
        ReadWrite.fileWrite(outputFile, userRecord.getAll(), true);
      }
      return 1;
    }
    catch (ClassCastException | IOException a) {
      return -1;
    }
  }
  public int genProviderReport(String providerID) {
    if (providerID == null)
      return -1;
    try {

      Provider current;
      if (currentUser != null && currentUser.get(1).equals(providerID))
        current = (Provider) currentUser;
      else
        current = (Provider) findData(1, providerID);

      if (current == null)
        return -1;

      String outputFile = "data/reports/providers/" + providerID + ".txt";

      // Write member data to text file
      ReadWrite.fileWrite(outputFile, "Provider Report:", false);
      for (String i : current.getAll()) {
        ReadWrite.fileWrite(outputFile, i, true);
      }

      ReadWrite.fileWrite(outputFile, "", true);
      ReadWrite.fileWrite(outputFile, "Reports: ", true);
      ArrayList<Record> providerRecords = (ArrayList<Record>) findData(4, providerID);
      String [] record;
      int fee = 0;
      for(Record providerRecord : providerRecords){
        record = providerRecord.getAll();
        try{
          fee += Integer.parseInt(record[6]);
        }
        catch(NumberFormatException e){
          return -1;
        }
        ReadWrite.fileWrite(outputFile, record, true);
      }
      try{
        String number = Integer.toString(providerRecords.size());
        ReadWrite.fileWrite(outputFile, number, true);
      }
      catch(Exception e){
        return -1;
      }
      try{
        String totalFee = Integer.toString(fee);
        ReadWrite.fileWrite(outputFile, totalFee, true);
      }
      catch(Exception e){
        return -1;
      }


      return 1;
    }
    catch (ClassCastException | IOException a) {
      return -1;
    }
  }
  public int genAllMemberReports() {
    ArrayList<Member> member = getAll(2);
    for(Member i : member){
      genMemberReport(i.get(1));
    }
    return 0;
  }
  public int genAllProvidersReports() {
    ArrayList<Provider> provider = getAll(2);
    for(Provider i : provider){
      genMemberReport(i.get(1));
    }
    return 0;
  }

  public int sendReports(String input) {
    return 0;
  }

  public String[] getMember(String code){
    try {
      Member toReturn = (Member) findData(2, code);
      if (toReturn == null)
        return null;
      return toReturn.getAll();
    }
    catch (ClassCastException a) {
      return null;
    }
  }
  public String[] getProvider(String code){
    try {
      Provider toReturn = (Provider) findData(1, code);
      if (toReturn == null)
        return null;
      return toReturn.getAll();
    }
    catch (ClassCastException a) {
      return null;
    }
  }
  public String[] getService(String code) {
      try {
        Service toReturn = (Service) findData(3, code);
        if (toReturn == null)
          return null;
        return toReturn.getAll();
      }
      catch (ClassCastException a) {
        return null;
      }
  }

  public void viewMembers(){
    ArrayList<Object> allMembers = getAll(2);
    if (allMembers == null)
      return;
    try {
      for (Object i : allMembers) {
        Member member = (Member) i;
        member.display();
      }
    }
    catch (ClassCastException a) {
      return;
    }
  }
  public void viewProviders(){
    ArrayList<Object> allProviders = getAll(1);
    if (allProviders == null)
      return;
    try {
      for (Object i : allProviders) {
        Provider provider = (Provider) i;
        provider.display();
      }
    }
    catch (ClassCastException a) {
      return;
    }
  }
  public void viewServices(){
    ArrayList<Object> allServices = getAll(3);
    if (allServices == null)
      return;
    try {
      for (Object i : allServices) {
        Service service = (Service) i;
        service.display();
      }
    }
    catch (ClassCastException a) {
      return;
    }
  }
  public void viewDirectory(String PID){
    try {
      Provider toReturnP = (Provider) findData(1, PID);
      if (toReturnP == null)
        return;
      String [] serviceCodes = toReturnP.getServices();
      Service[] serviceArray = new Service[serviceCodes.length];

      for (int i = 0; i < serviceArray.length; i++){
        serviceArray[i] = (Service) findData(3, serviceCodes[i]);
      }

      for (Service i : serviceArray)
        i.display();
    }
    catch (ClassCastException a) {
      return;
    }
  }

  private String getCurrentTime() {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/YYYY");
    LocalDateTime time = LocalDateTime.now();
    String currentDate = dateFormat.format(time);
    dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    currentDate += " " + dateFormat.format(time);
    return currentDate;
  }

  public int generateServiceRecord(String[] input) {
    /* input should be in the following format:
     *  date
     *  serviceID
     *  comments
     */

    if (currentMember == null || currentUser == null)
      return -2;
    if (!(currentUser instanceof Provider))
      return -2;
    if (input == null)
      return -1;

    // get the service specified by provider
    Service service = (Service) findData(3, input[1]);
    if (service == null)
      return -1;

    String[] data = new String[6];
    data[0] = getCurrentTime();
    data[1] = input[0];
    data[2] = currentMember.get(1);
    data[3] = currentUser.get(1);
    data[4] = input[1];
    data[5] = input[2];

    String[] recordData = new String[7];
    System.arraycopy(data, 0, recordData, 0, 6);
    // get cost from service
    recordData[6] = service.getCost();
    Record record = new Record(recordData);

    try {
      ReadWrite.fileWrite("records/" + currentMember.get(1) + ".csv", data, true);
      addTreeData(4, currentMember.get(1), record);
      addTreeData(5, currentUser.get(1), record);
      return 1;
    }
    catch (IOException a) {
      return -1;
    }
  }

  public int requestDirectory() {
    if (currentUser == null)
      return -2;
    try{
      Provider currentProvider = (Provider) currentUser;
      String PID = currentProvider.get(1);
      viewDirectory(PID);
    }
    catch(ClassCastException e){
      return -1;
    }
    return 1;
  }

  public int generateBill() {
    if (currentUser == null)
      return -2;
    try{
      Provider currentProvider = (Provider) currentUser;
      String PID = currentProvider.get(1);
      genProviderReport(PID);
    }
    catch(ClassCastException e){
      return -1;
    }
    return 0;
  }


}
