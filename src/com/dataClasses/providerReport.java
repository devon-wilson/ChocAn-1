import java.util.Vector;

public class providerReport extends Report{

    public providerReport(){
        services = null;
    }

    public int addService(String [] service){
        if(service.length != 6){
            return 0;
        }
        services.add(service);
        return 1;
    }

    public int display() {
        System.out.println(super.name);
        System.out.println(super.number);
        System.out.println(super.address);
        System.out.println(super.city);
        System.out.println(super.state);
        System.out.println(super.zip);

        System.out.println("These are the services that were provided.");
        int size = services.size();
        for (int i = 0; i < size; ++i) {
            String[] service = services.get(i);
            for (int j = 0; j < 6; ++j) {
                System.out.println(service[j]);
            }
        }
        return 1;
    }
    public int emptyServices(){
        services.clear();
        return 1;
    }

    private Vector<String []> services;
}
