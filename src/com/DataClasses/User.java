package com.DataClasses;

public abstract class User{

    protected String name;
    protected String ID;
    protected String address;
    protected String city;
    protected String state;
    protected String zip;

    public User(){
        name = null;
        ID = null;
        address = null;
        city = null;
        state = null;
        zip = null;
    }

    protected User(String[] userData) {
        this.name = userData[0];
        this.ID = userData[1];;
        this.address = userData[2];;
        this.city = userData[3];;
        this.state = userData[4];;
        this.zip = userData[5];;
    }

    //displays are found in the derived classes
    public abstract int  display();

    //THIS FUNCTION MUST BE CALLED FIRST
    public int build(String [] components){
        //pass in an array of six strings to build
        //the user information. The order of the strings
        //should be the same as the protected data members.
        //0 - name
        //1 - ID
        //2 - address
        //3 - city
        //4 - state
        //5 - zip
        if(components == null){
            return -1;
        }
        if(components.length != 6) {
            return -1;
        }
        for(int i = 0; i < 6; ++i){
            if(components[i] == null){
                return -1;
            }
        }

        name = components[0];
        ID = components[1];
        address = components[2];
        city = components[3];
        state = components[4];
        zip = components[5];

        return 0;
    }

    //These functions change a given field.
    //Make sure the class is built.
    public int changeName(String toChange){
        if(toChange == null){
            return -1;
        }
        this.name = toChange;
        return 0;
    }
    public int changeID(String toChange){
        if(toChange == null){
            return -1;
        }
        this.ID = toChange;
        return 0;
    }
    public int changeAddress(String toChange){
        if(toChange == null){
            return -1;
        }
        this.address = toChange;
        return 0;
    }
    public int changeCity(String toChange){
        if(toChange == null){
            return -1;
        }
        this.city = toChange;
        return 0;
    }
    public int changeState(String toChange) {
        if(toChange == null){
            return -1;
        }
        this.state = toChange;
        return 0;
    }
    public int changeZip(String toChange){
        if(toChange == null){
            return -1;
        }
        this.zip = toChange;
        return 0;
    }

    public String [] getAll(){
        //Returns an array of all the strings
        //they are in the order of the protected data
        //members
        String [] data = new String[6];
        if(name == null){
            return null;
        }
        data[0] = name;
        data[1] = ID;
        data[2] = address;
        data[3] = city;
        data[4] = state;
        data[5] = zip;
        return data;
    }

    public String get(int toGet){
        //Return one field.
        //The ID passed in is
        //0 - name
        //1 - ID
        //2 - address
        //3 - city
        //4 - state
        //5 - zip
        String toReturn;
        if(toGet == 0 && name != null){
            return name;
        }
        if(toGet == 1 && ID != null){
            return ID;
        }
        if(toGet == 2 && address != null){
            return address;
        }
        if(toGet == 3 && city != null){
            return city;
        }
        if(toGet == 4 && state != null){
            return state;
        }
        if(toGet == 5 && zip != null){
            return zip;
        }
        else{
            return null;
        }
    }
}
