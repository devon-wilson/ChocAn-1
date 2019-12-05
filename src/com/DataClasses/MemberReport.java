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

    public String [] getAll(){
        //1 - Provider info
        //2 - number of services provided
        //3 - total fee of all services
        //4 - each cell contains a comma seperated list of services
        String [] components = new String [3 + services.size()];
        components[0] = super.name + "," + super.number + "," + super.address + "," + super.city + "," + super.state + "," + super.zip;
        for(int i = 0; i < services.size(); ++i){
            String [] temp = services.get(i);
            components[i + 1] = temp[0];
            for(int j = 1; j < temp.length; ++j){
                components[i + 1] += "," + temp[i];
            }
        }
        return components;
    }

    //gets the data in the report.
    public Vector<String[]> getServices(){
        return services;
    }

    private Vector<String []> services;
}
