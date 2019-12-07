package com.DataClasses;

import java.util.ArrayList;

public class Provider extends User{

    private ArrayList<String> serviceCodes;

    public Provider(){
        super();
        serviceCodes = new ArrayList<>();
    }

    public Provider(String[] userData) {
        super(userData);

        //Builds a vector of initial serviceCodes.
        serviceCodes = new ArrayList<>();
        for(int i = 6; i < userData.length; ++i){
            serviceCodes.add(userData[i]);
        }
    }

    public String[] getServices() {
        //Gets an array of all serviceCodes
        if (serviceCodes == null)
            return null;
        return serviceCodes.toArray(new String[serviceCodes.size()]);
    }

    public int removeService(String toRemove){
        for(int i = 0; i < serviceCodes.size(); ++i){
            if(toRemove.equals(serviceCodes.get(i))){
                serviceCodes.remove(i);
                return 0;
            }
        }
        return -1;
    }

    public int addService(String serviceName){
        if(serviceName == null){
            return -1;
        }
        serviceCodes.add(serviceName);
        return 0;
    }

    public void display(){
        System.out.println("Provider Information:");
        super.display();
    }

    public String[] toStringArray() {
        String[] all = new String[6+serviceCodes.size()];
        System.arraycopy(super.getAll(), 0, all, 0, 6);
        for (int i = 0; i < serviceCodes.size(); i++) {
            all[6+i] = serviceCodes.get(i);
        }
        return all;
    }
}