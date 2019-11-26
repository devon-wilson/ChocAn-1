import java.util.Vector;

public class providerReport extends Report{

    public providerReport(){
        services = null;
        number = 0;
        totalFee = 0;
    }

    public int addService(String [] service){
        //The order of elements in the array matches
        //The requirements.
        /*
        1 - Date of Service
        2 - Date and time data was recieved
        3 - Member name
        4 - Member number
        5 - Service code
        6 - Fee to be Paid (NO DOLLAR SIGN)
        I need to convert the fee to a float
        To calculate total
         */
        if(service == null){
            return -1;
        }
        if(service.length != 6){
            return -1;
        }
        for(int i = 0; i < 6; ++i){
            if(service[i] == null){
                return -1;
            }
        }


        try{
            totalFee += Float.parseFloat(service[5]);
        }
        catch(NumberFormatException e){
            System.err.println("Wrong format of fee");
            return -1;
        }
        number += 1;
        services.add(service);
        return 0;
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
        System.out.println("The total fee for this period is " + totalFee + ".");
        System.out.println("The number of services provided was " + number + ".");
        return 1;
    }
    public int emptyServices(){
        services = null;
        return 1;
    }

    private Vector<String []> services;
    private int number;
    private float totalFee;
}
