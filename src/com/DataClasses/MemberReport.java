package com.DataClasses;

import java.util.Vector;

public class MemberReport extends Report{

    public MemberReport(){
        services = null;
    }

    public int addService(String [] service){
        //Adds a service in this order
        /*
        1 - Date of Service
        2 - Provider Name
        3 - Service Name
         */
        if(service == null){
            return -1;
        }
        if(service.length != 3){
            return -1;
        }
        for(int i =0; i < 3; ++i){
            if(service[i] == null){
                return -1;
            }
        }
        if(services == null){
            services = new Vector<String []>();
        }
        services.add(service);
        return 0;
    }

    public int display(){
        System.out.println(super.name);
        System.out.println(super.number);
        System.out.println(super.address);
        System.out.println(super.city);
        System.out.println(super.state);
        System.out.println(super.zip);

        System.out.println("These are the services that were provided.");
        int size = services.size();
        for(int i = 0; i < size; ++i){
            String [] service = services.get(i);
            for(int j = 0; j < 3; ++j){
                System.out.println(service[j]);
            }
        }
        return 0;
    }
    public int emptyServices(){
        services = null;
        return 0;
    }

    //gets the data in the report.
    public Vector<String[]> getServices(){
        return services;
    }

    private Vector<String []> services;
}
