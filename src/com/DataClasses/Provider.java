package com.DataClasses;

import java.util.Vector;

public class Provider extends User{

    private Vector<String> services;

    public Provider(){
        super();
        services = new Vector<>();
    }

    public Provider(String[] userData) {
        super(userData);

        //Builds a vector of initial services.
        services = new Vector<>();
        for(int i = 6; i < userData.length; ++i){
            services.add(userData[i]);
        }
    }

    public String[] getServices() {
        //Gets an array of all services
        if (services == null)
            return null;
        String [] components = new String[services.size()];
        for(int i = 0; i < components.length; ++i){
            components[i] = new String(services.get(i));
        }
        return components;
    }

    public int removeService(String toRemove){
        //removes a service
        for(int i = 0; i < services.size(); ++i){
            if(toRemove.equals(services.get(i)) == true){
                services.remove(i);
                return 0;
            }
        }
        return -1;
    }

    public int addService(String toChange){
        //adds a service
        if(toChange == null){
            return -1;
        }
        services.add(toChange);
        return 0;
    }

    public void display(){
        System.out.println("Provider Information:");
        super.display();
    }
}
