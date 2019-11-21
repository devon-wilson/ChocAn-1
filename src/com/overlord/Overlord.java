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
        System.out.println("Overlord tests");
        Overlord overlord = new Overlord();
        int rc = overlord.loginUser("0", "0");
    }

    // should return User object
    public int loginUser(String providerID, String otherID) {
        return 0;
    }

    // Member functions
    public int memberCheckIn() { return 0;}

    public int generateServiceRecord() {
        return 0;
    }

    public int requestDirectory() { return 0; }

    public int generateBill() {return 0;}

    public int exit() {return 0;}

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
