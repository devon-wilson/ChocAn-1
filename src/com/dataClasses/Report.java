public abstract class Report {

    public Report(){
        name = null;
        number = null;
        address = null;
        city = null;
        state = null;
        zip = null;
    }

    public int build(User toFill){
        //Builds the report based on the order of
        //private data members below.
        if(toFill == null){
            return -1;
        }
        String [] toGet;
        toGet = toFill.getAll();
        if(toGet == null){
            return -1;
        }
        name = new String(toGet[0]);
        number = new String(toGet[1]);
        address = new String(toGet[2]);
        city = new String(toGet[3]);
        state = new String(toGet[4]);
        zip = new String(toGet[5]);
        return 0;
    }

    //change a given field in the report.
    public int changeName(String toChange){
        if(toChange == null){
            return -1;
        }
        if(name == null){
            return -1;
        }
        name = toChange;
        return 0;
    }
    public int changeID(String toChange){
        if(toChange == null){
            return -1;
        }
        if(number == null){
            return -1;
        }
        number = toChange;
        return 0;
    }
    public int changeAddress(String toChange){
        if(toChange == null){
            return -1;
        }
        if(address == null){
            return -1;
        }
        address = toChange;
        return 0;
    }
    public int changeCity(String toChange){
        if(toChange == null){
            return -1;
        }
        if(city == null){
            return -1;
        }
        city = toChange;
        return 0;
    }
    public int changeState(String toChange) {
        if(toChange == null){
            return -1;
        }
        if (state == null) {
            return -1;
        }
        state = toChange;
        return 0;
    }
    public int changeZip(String toChange){
        if(toChange == null){
            return -1;
        }
        if(zip == null){
            return -1;
        }
        zip = toChange;
        return 0;
    }

    public String [] getAll(){
        //Returns an array of all the strings
        //they are in the order of the private data
        //members
        String [] data = new String [6];
        if(name == null){
            return null;
        }
        data[0] = new String(name);
        data[1] = new String(number);
        data[2] = new String(address);
        data[3] = new String(city);
        data[4] = new String(state);
        data[5] = new String(zip);
        return data;
    }
    public String get(int toGet){
        //Return one field.
        //The number passed in is
        //0 - name
        //1 - number
        //2 - address
        //3 - city
        //4 - state
        //5 - zip
        String toReturn;
        if(toGet == 0 && name != null){
            toReturn = new String(name);
            return toReturn;
        }
        if(toGet == 1 && number != null){
            toReturn = new String(number);
            return toReturn;
        }
        if(toGet == 2 && address != null){
            toReturn = new String(address);
            return toReturn;
        }
        if(toGet == 3 && city != null){
            toReturn = new String(city);
            return toReturn;
        }
        if(toGet == 4 && state != null){
            toReturn = new String(state);
            return toReturn;
        }
        if(toGet == 5 && zip != null){
            toReturn = new String(zip);
            return toReturn;
        }
        else{
            return null;
        }
    }

    public abstract int display();
    public abstract int emptyServices();

    protected String name;
    protected String number;
    protected String address;
    protected String city;
    protected String state;
    protected String zip;
}
