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

    int currentProvider;
    int currentMember;

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
     * @param otherID Some mystery string
     * @return returns int; 1 for success; -1 could not log in
     */
    public int login(String providerID, String otherID) {
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
     * @param memberID
     * @return
     */
    public int memberCheckIn(String memberID) {
        // match string to member directory
        if (true)
        return -1;

        return 1;
    }


    /**
     * Member Check Out
     * @return
     */
    public int memberCheckOut() {
        // match string to member directory
        if (true)
            return -1;

        return 1;
    }

    public int generateServiceRecord() {
        return 0;
    }

    public int requestDirectory() { return 0; }

    public int generateBill() {return 0;}


    // Manage members
    public int addMember(int MEMBER_PLACEHOLDER) { return 0; }

    public int removeMember(int memberID) {return 0;}

    public int suspendMember(int memberID) {return 0;}

    public int renewMember(int memberID) {return 0;}

    // should return Member object
    public void searchMember(String query) {}


    // Manage providers
    public int addProvider(int PROVIDER_PLACEHOLDER) { return 0; }

    public int removeProvider(int providerID) {return 0;}

    // should return Provider object
    public void searchProvider(String query) {}

    // Manage services
    public int addService(int SERVICE_PLACEHOLDER) { return 0; }

    public int removeService(int serviceID) {return 0;}

    // should return Service object
    public int searchService(String query) {return 0;}

    public int genMemberReport(int memberID) {return 0;}

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
}
