public abstract class User{


    public User(){
        name = null;
        number = null;
        address = null;
        city = null;
        state = null;
        zip = null;
    }

    public abstract int  display();

    public int build(String [] components){
        //pass in an array of six strings to build
        //the user information. Make sure they are
        //shorter than the length specified on d2l
        if(components.length != 6){
            return 0;
        }

        if(components[0].length() > 25){
            return 0;
        }
        else{
            name = new String(components[0]);
        }

        if(components[1].length() > 9){
            return 0;
        }
        else{
            number = new String(components[1]);
        }

        if(components[2].length() > 25){
            return 0;
        }
        else{
            address = new String(components[2]);
        }

        if(components[3].length() > 14){
            return 0;
        }
        else{
            city = new String(components[3]);
        }

        if(components[4].length() > 2){
            return 0;
        }
        else{
            state = new String(components[4]);
        }

        if(components[5].length() > 5){
            return 0;
        }
        else{
            zip = new String(components[5]);
        }
        return 1;
    }

    public int changeName(String toChange){
        if(toChange.length() > 25){
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



    protected String name;
    protected String number;
    protected String address;
    protected String city;
    protected String state;
    protected String zip;

}
