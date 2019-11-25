public class Record {

    public Record(){
        currDateTime = null;
        dateProvided = null;
        providerID = null;
        memberID = null;
        serviceCode = null;
        comment = null;
    }

    public int build(String [] components){
        if(components.length != 6){
            return 0;
        }
        if(components[0] != null){
            currDateTime = new String(components[0]);
        }
        else{
            return 0;
        }
        if(components[1] != null){
            dateProvided = new String(components[1]);
        }
        else{
            return 0;
        }
        if(components[2] != null){
            providerID = new String(components[2]);
        }
        else{
            return 0;
        }
        if(components[3] != null){
            memberID = new String(components[3]);
        }
        else{
            return 0;
        }
        if(components[4] != null){
            serviceCode = new String(components[4]);
        }
        else{
            return 0;
        }
        if(components[5] != null){
            comment = new String(components[5]);
        }
        else{
            return 0;
        }
        return 1;
    }

    public int display(){
        System.out.println(currDateTime);
        System.out.println(dateProvided);
        System.out.println(providerID);
        System.out.println(memberID);
        System.out.println(serviceCode);
        System.out.println(comment);
        return 1;
    }

    public String [] getAll(){
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
