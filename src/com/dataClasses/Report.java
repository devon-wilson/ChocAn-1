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
        String [] toGet;
        toGet = toFill.getAll();
        name = toGet[0];
        number = toGet[1];
        address = toGet[2];
        city = toGet[3];
        state = toGet[4];
        zip = toGet[5];
        return 1;
    }
    public int changeName(String toChange){
        if(toChange == null){
            return 0;
        }
        if(name == null){
            name = new String(toChange);
        }
        else{
            name = toChange;
        }
        return 1;
    }
    public int changeID(String toChange){
        if(toChange == null){
            return 0;
        }
        if(number == null){
            number = new String(toChange);
        }
        else{
            number = toChange;
        }
        return 1;
    }
    public int changeAddress(String toChange){
        if(toChange == null){
            return 0;
        }
        if(address == null){
            address = new String(toChange);
        }
        else{
            address = toChange;
        }
        return 1;
    }
    public int changeCity(String toChange){
        if(toChange == null){
            return 0;
        }
        if(city == null){
            city = new String(toChange);
        }
        else{
            city = toChange;
        }
        return 1;
    }
    public int changeState(String toChange) {
        if(toChange == null){
            return 0;
        }
        if (state == null) {
            state = new String(toChange);
        } else {
            state = toChange;
        }
        return 1;
    }
    public int changeZip(String toChange){
        if(toChange == null){
            return 0;
        }
        if(zip == null){
            zip = new String(toChange);
        }
        else{
            zip = toChange;
        }
        return 1;
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
