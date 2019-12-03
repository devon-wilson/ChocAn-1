package com.DataClasses;

public class Record {

    private String currDateTime;
    private String dateProvided;
    private String providerID;
    private String memberID;
    private String serviceCode;
    private String comment;

    public Record(){
        currDateTime = null;
        dateProvided = null;
        providerID = null;
        memberID = null;
        serviceCode = null;
        comment = null;
    }

    //THIS FUNCTION MUST BE CALLED FIRST
    public int build(String [] components){
        //Builds a record from ana array of Strings.
        //The order is the order of Strings in
        //The private data section.
        if(components == null){
            return -1;
        }
        if(components.length != 6){
            return -1;
        }
        for(int i = 0; i < 6; ++i){
            if(components[i] == null){
                return -1;
            }
        }
        this.currDateTime = components[0];
        this.dateProvided = components[1];
        this.providerID = components[2];
        this.memberID = components[3];
        this.serviceCode = components[4];
        this.comment = components[5];
        return 0;
    }

    //Changes a given field based on the string
    //passed in. Must be built fist.
    public int changeCurrDT(String toChange){
        if(toChange == null){
            return -1;
        }
        this.currDateTime = toChange;
        return 0;
    }
    public int changeDateP(String toChange){
        if(toChange == null){
            return -1;
        }
        this.dateProvided = toChange;
        return 0;
    }
    public int changePID(String toChange){
        if(toChange == null){
            return -1;
        }
        this.providerID = toChange;
        return 0;
    }
    public int changeMID(String toChange){
        if(toChange == null){
            return -1;
        }
        this.memberID = toChange;
        return 0;
    }
    public int changeCode(String toChange) {
        if(toChange == null){
            return -1;
        }
        this.serviceCode = toChange;
        return 0;
    }
    public int changeComment(String toChange){
        if(toChange == null){
            return -1;
        }
        this.comment = toChange;
        return 0;
    }

    public int display(){
        System.out.println(currDateTime);
        System.out.println(dateProvided);
        System.out.println(providerID);
        System.out.println(memberID);
        System.out.println(serviceCode);
        System.out.println(comment);
        return 0;
    }


    public String [] getAll(){
        //Returns all the fields in an array of
        //Strings. The order is the order of the
        //private data members
        if(currDateTime == null){
            return null;
        }
        String [] components = new String [6];
        components[0] = currDateTime;
        components[1] = dateProvided;
        components[2] = providerID;
        components[3] = memberID;
        components[4] = serviceCode;
        components[5] = comment;
        return components;
    }
}
