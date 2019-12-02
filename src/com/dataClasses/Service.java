import java.util.Vector;

public class Service {

    public Service(){
        code = null;
        name = null;
        cost = 0;
    }

    public int build(String [] components){
        //Builds the class form ana array of Strings
        //1 - code
        //2 - name
        //3 - cost
        if(components == null){
            return -1;
        }
        if(components.length != 3){
            return -1;
        }
        for(int i = 0; i < 3; ++i){
            if(components[i] == null){
                return -1;
            }
        }

        try{
            cost = Float.parseFloat(components[2]);
        }
        catch(NumberFormatException e){
            System.err.println("Wrong format of fee");
            return -1;
        }

        code = new String(components[0]);
        name = new String(components[1]);

        return 0;
    }

    //Compares the stored code, to a given code
    public int compCode(String toCompare){
        if(code.equals(toCompare) == true){
            return 0;
        }
        else{
            return -1;
        }
    }

    //changes a given field
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
    public int changeCode(String toChange){
        if(toChange == null){
            return -1;
        }
        if(code == null){
            return -1;
        }
        code = toChange;
        return 0;
    }
    public int changeCost(String toChange){
        if(toChange == null){
            return -1;
        }
        try{
            cost = Float.parseFloat(toChange);
        }
        catch(NumberFormatException e){
            System.err.println("Wrong format of fee");
            return -1;
        }
        return 0;
    }

    //Gets the data as an array of Strings
    //They are in the same order as built
    public String [] getAll(){
        if(code == null){
            return null;
        }
        else{
            String [] components = new String[3];
            components[0] = new String(code);
            components[1] = new String(name);
            components[2] = new String(Float.toString(cost));
            return components;
        }
    }
    public int display(){
        if(code == null){
            return -1;
        }
        System.out.println(code);
        System.out.println(name);
        System.out.println(cost);
        return 0;
    }


    private String code;
    private String name;
    private float cost;
}
