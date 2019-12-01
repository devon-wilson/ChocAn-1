package com.DataClasses;

public class Provider extends User{

    private String[] services;

    public Provider(){
        super();
        services = null;
    }

    public Provider(String[] userData) {
        super(userData);

        int serviceCount = (userData.length)-6;
        this.services = new String[serviceCount];
        System.arraycopy(userData,6 , services, 0, serviceCount);
    }

    public String[] displayServices() {
        if (services == null)
            return null;
        return services;
    }

    public void display(){
        System.out.println("Provider Information:");
        super.display();
    }
}
