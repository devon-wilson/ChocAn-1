package com.Overlord;

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
public class Overlord {
    public static void main(String[] args) {
        Overlord_Tests.main(null);
    }

    private int currentProvider;
    private int currentMember;

    /**
     * Default Constructor
     */
    public Overlord() {
       // first argument is filepath to
       //super(arguments);
        super();
       // second argument is..
    }

    /**
     * Login
     *
     * @param providerID Provider identification number
     * @return returns int: 1 for provider; 2 for manager; -1 could not log in
     */
    public int login(String providerID) {
        // matches string to provider directory
        // if no match return -1
        if (true)
        return -1;

        // sets currentProvider
        currentProvider = 1;
        return 1;
    }

    /**
     * Logout
     *
     * @return 1 and cannot fail.
     */
    public int logout() {
        //currentProvider = 0;
        //currentMember = 0;
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
        if (true)
        return -1;

        currentMember = 1;
        return 1;
    }


    /**
     * Member Check Out
     * @return 1 for success, -1 for failure.
     */
    public int memberCheckOut() {
        // match string to member directory
        if (true)
            return -1;

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
        return 0;
    }

    /**
     * RemoveMember
     *
     * @param memberID member ID to remove
     * @return
     */
    public int removeMember(String memberID) {
        return 0;
    }

    public int suspendMember(String memberID) {
        return 0;
    }

    public int renewMember(String memberID) {
        return 0;
    }

    // should return Member object
    public void searchMember(String query) {

    }


    // Manage providers
    public int addProvider(String[] providerData) {
        return 0;
    }

    public int removeProvider(int providerID) {
        return 0;
    }

    // should return Provider object
    public String[] searchProvider(String query) {
        return null;
    }


    // Manage services
    public int addService(String[] serviceData) {
        return 0;
    }

    public int removeService(int serviceID) {
        return 0;
    }


    public String[] searchService(String query) {
        return null;
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
