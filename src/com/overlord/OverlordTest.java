import static org.junit.jupiter.api.Assertions.*;

class OverlordTest {

    @org.junit.jupiter.api.Test
    void login() {
        Overlord overlord = new Overlord();


        assertEquals(-1, overlord.login(null), "login with null reports failure");
        assertEquals(1, overlord.login(null), "login with null reports failure");
    }

    @org.junit.jupiter.api.Test
    void logout() {
    }

    @org.junit.jupiter.api.Test
    void memberCheckIn() {
    }

    @org.junit.jupiter.api.Test
    void memberCheckOut() {
    }

    @org.junit.jupiter.api.Test
    void addMember() {
    }

    @org.junit.jupiter.api.Test
    void removeMember() {
    }

    @org.junit.jupiter.api.Test
    void suspendMember() {
    }

    @org.junit.jupiter.api.Test
    void renewMember() {
    }

    @org.junit.jupiter.api.Test
    void searchMember() {
    }

    @org.junit.jupiter.api.Test
    void addProvider() {
        Overlord overlord = new Overlord();

        assertEquals(-1, overlord.addProvider(null), "add member fails on null");
        assertEquals(1, overlord.addProvider(null), "add member fails on null");
    }

    @org.junit.jupiter.api.Test
    void removeProvider() {
    }

    @org.junit.jupiter.api.Test
    void searchProvider() {
    }

    @org.junit.jupiter.api.Test
    void addService() {
    }

    @org.junit.jupiter.api.Test
    void removeService() {
    }

    @org.junit.jupiter.api.Test
    void searchService() {
    }

    @org.junit.jupiter.api.Test
    void genMemberReport() {
    }

    @org.junit.jupiter.api.Test
    void genProviderReport() {
    }

    @org.junit.jupiter.api.Test
    void genAllMemberReports() {
    }

    @org.junit.jupiter.api.Test
    void genAllProvidersReports() {
    }

    @org.junit.jupiter.api.Test
    void sendReports() {
    }

    @org.junit.jupiter.api.Test
    void generateServiceRecord() {
    }

    @org.junit.jupiter.api.Test
    void requestDirectory() {
    }

    @org.junit.jupiter.api.Test
    void generateBill() {
    }
}