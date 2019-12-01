package com.Overlord;

import com.DataBaseManager.DataBaseManager;
import com.DataClasses.*;

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
public class Overlord extends DataBaseManager {
    public static void main(String[] args) {
        //Overlord_Tests.main(null);
    }

    private User currentUser;
    private Member currentMember;

    /**
     * Default Constructor
     */
    public Overlord() {
       // first argument is filepath to
       //super(arguments);
        super();
       // second argument is..

        this.currentUser = null;
        this.currentMember = null;
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
        currentUser = null;
        currentMember = null;
        return -1;
    }

    // Member functions

    /**
     * Member Check In
     * @param memberID contains id of member
     * @return returns 1 for sucess, -1 for failure.
     */
    public int memberCheckIn(String memberID) {
        // match string to member directory

        try {
            this.currentMember = (Member) findData(2, memberID);
            if (currentMember == null)
                return -1;
            return 1;
        }
        catch (ClassCastException a) {
            this.currentMember = null;
            return -1;
        }
    }

    public void viewMember() {
        if (currentMember == null)
            System.out.println("No member current checked in.");
        else
            currentMember.display();
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
        Member toAdd = new Member(memberData);

        if(addTreeData(2, toAdd.get(1), toAdd) == null)
            return -1;
        return 1;
    }

    /**
     * RemoveMember
     *
     * @param memberID member ID to remove
     * @return
     */
    public int removeMember(String memberID) {
        Object removed = removeTreeData(2, memberID);
        if (removed == null)
            return -1;
        return 1;
    }

    public int suspendMember(String memberID) {
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

    // should return Member object
    public Member searchMember(String query) {
        try {
            return (Member) findData(2, query);
        }
        catch (ClassCastException a) {
            return null;
        }
    }

    // Manage providers
    public int addProvider(String[] providerData) {
        Provider toAdd = new Provider(providerData);
        if(addTreeData(1, providerData[1], toAdd) == null)
            return -1;
        return 1;
    }

    public int removeProvider(String providerID) {
        if(removeTreeData(1,providerID) == null)
            return -1;
        return 1;
    }

    // should return Provider object
    public Provider searchProvider(String query) {
        try {
            return (Provider) findData(1, query);
        }
        catch (ClassCastException a) {
            return null;
        }
    }

    public void displayCurrentServices() {
        try {
            Provider current = (Provider) currentUser;
            current.displayServices();
        }
        catch (ClassCastException a) {
            System.out.println("Current user is not a Provider. Cannot display services");
        }
    }

    // Manage services
    public int addService(String[] serviceData) {
        Service toAdd = new Service(serviceData);
        if(addTreeData(3, serviceData[1], toAdd) == null)
            return -1;
        return 1;
    }

    public int removeService(String serviceID) {
        if(removeTreeData(3, serviceID) == null)
            return -1;
        return 1;
    }


    public Service searchService(String query) {
        try {
            return (Service) findData(2, query);
        }
        catch (ClassCastException a)
        {
            return null;
        }
    }

    public int genMemberReport(int memberID) {
        return 0;
    }

    public int genProviderReport(int memberID) {
        return 0;
    }

    public int genAllMemberReports() {
        return 0;
    }

    public int genAllProvidersReports() {
        return 0;
    }

    public int sendReports(String input) {
        return 0;
    }



    public int generateServiceRecord() {
        return 0;
    }

    public int requestDirectory() {
        return 0;
    }

    public int generateBill() {
        return 0;
    }


}
