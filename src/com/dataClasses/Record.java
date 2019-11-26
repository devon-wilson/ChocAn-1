public class Record {

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
        currDateTime = new String(components[0]);
        dateProvided = new String(components[1]);
        providerID = new String(components[2]);
        memberID = new String(components[3]);
        serviceCode = new String(components[4]);
        comment = new String(components[5]);
        return 0;
    }

    //Changes a given field based on the string
    //passed in. Must be built fist.
    public int changeCurrDT(String toChange){
        if(toChange == null){
            return -1;
        }
        if(currDateTime == null){
            return -1;
        }
        currDateTime = toChange;
        return 0;
    }
    public int changeDateP(String toChange){
        if(toChange == null){
            return -1;
        }
        if(dateProvided == null){
            return -1;
        }
        dateProvided = toChange;
        return 0;
    }
    public int changePID(String toChange){
        if(toChange == null){
            return -1;
        }
        if(providerID == null){
            return -1;
        }
        providerID = toChange;
        return 0;
    }
    public int changeMID(String toChange){
        if(toChange == null){
            return -1;
        }
        if(memberID == null){
            return -1;
        }
        memberID = toChange;
        return 0;
    }
    public int changeCode(String toChange) {
        if(toChange == null){
            return -1;
        }
        if (serviceCode == null) {
            return -1;
        }
        serviceCode = toChange;
        return 0;
    }
    public int changeComment(String toChange){
        if(toChange == null){
            return -1;
        }
        if(comment == null){
            return -1;
        }
        comment = toChange;
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
        components[0] = new String(currDateTime);
        components[1] = new String(dateProvided);
        components[2] = new String(providerID);
        components[3] = new String(memberID);
        components[4] = new String(serviceCode);
        components[5] = new String(comment);
        return components;
    }



    private String currDateTime;
    private String dateProvided;
    private String providerID;
    private String memberID;
    private String serviceCode;
    private String comment;
}
