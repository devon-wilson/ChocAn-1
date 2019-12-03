import java.util.Vector;

public class Provider extends User{

    public Provider(){
        super();
    }

    public int display(){
        System.out.println("Provider Information:");
        System.out.println(super.name);
        System.out.println(super.number);
        System.out.println(super.address);
        System.out.println(super.city);
        System.out.println(super.state);
        System.out.println(super.zip);
        return 0;
    }

    public int showDirectory(){
        if(directory == null){
            return -1;
        }
        int size = directory.size();
        for (int i = 0; i < size; ++i) {
            directory.get(i).display();
        }

        return 0;
    }

    public int addService(String [] components){
        Service service = new Service();
        int success = service.build(components);
        if(success < 0){
            return success;
        }
        directory.add(service);
        return 0;
    }

    private Vector<Service> directory;
}
