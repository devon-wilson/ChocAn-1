public class Manager extends User{

    public Manager(){
        super();
    }

    public int display(){
        System.out.println("Manager Information:");
        System.out.println(super.name);
        System.out.println(super.number);
        System.out.println(super.address);
        System.out.println(super.city);
        System.out.println(super.state);
        System.out.println(super.zip);
        return 1;
    }


}
